package dandyu.im;

import lombok.Getter;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Getter
public class SmpteTimecode {
    private static final int TIMEZONE_OFFSET = 9;
    private int hours = -1; // -1은 초기화 되지 않음을 나타냄
    private int minutes;
    private int seconds;
    private long frames;
    private long frameCount;
    private double frameRate;
    private boolean dropFrame;

    public SmpteTimecode(Object timecode, Double frameRate, Boolean dropFrame) throws Error {
        // Get frame rate
        if (frameRate == null)
            this.frameRate = 29.97;
        else if (frameRate.doubleValue() > 0)
            this.frameRate = frameRate.doubleValue();
        else
            throw new Error("Number expected as framerate");

        if (this.frameRate != 23.976 && this.frameRate != 24 &&
                this.frameRate != 25 && this.frameRate != 29.97 &&
                this.frameRate != 30 && this.frameRate != 50 &&
                this.frameRate != 59.94 && this.frameRate != 60)
            throw new Error("Unsupported frame rate");

        // If we are passed dropFrame, we need to use it
        if (dropFrame instanceof Boolean)
            this.dropFrame = dropFrame.booleanValue();
        else
            this.dropFrame = (this.frameRate == 29.97 || this.frameRate == 59.94); // by default, assume DF for 29.97 and 59.94, NDF otherwise

        // Now either get the frame count, string or datetime
        if (timecode instanceof Float) {
            Float floatTimeCode = (Float) timecode;
            this.frameCount = Math.round(floatTimeCode.floatValue());
            this.frameCountToTimeCode();
        } else if (timecode instanceof String) {
            final String regex = "^([012][0-9]):([0-5][0-9]):([0-5][0-9])([.;:])([0-9][0-9])$";
            String stringTimeCode = ((String) timecode);
            // pick it apart
            boolean match = stringTimeCode.matches(regex);
            if (!match)
                throw new Error("Timecode string expected as HH:MM:SS:FF or HH:MM:SS;FF");

            String[] parts = stringTimeCode.split("[:;.]");
            this.hours = Integer.parseInt(parts[0]);
            this.minutes = Integer.parseInt(parts[1]);
            this.seconds = Integer.parseInt(parts[2]);
            // do not override input parameters
            if (!(dropFrame instanceof Boolean)) {
                final String delimiter = stringTimeCode.substring(8, 9);
                this.dropFrame = delimiter.equals(":") ? false : true;
            }
            this.frames = Integer.parseInt(parts[3]);
            timeCodeToFrameCount();
        } else if (timecode instanceof Date) {
            Date dateTimecode = (Date) timecode;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date midnight = new Date(calendar.getTime().getTime());
            int midnight_tz = TIMEZONE_OFFSET * 60 * 1000;
            int timecode_tz = TIMEZONE_OFFSET * 60 * 1000;
            this.frameCount = Math.round(((dateTimecode.getTime() - midnight.getTime() + (midnight_tz - timecode_tz)) * this.frameRate) / 1000);
            this.frameCountToTimeCode();
        } else if (timecode instanceof SmpteTimecode && ((SmpteTimecode) timecode).hours != -1) {
            SmpteTimecode smpteTimecode = (SmpteTimecode) timecode;
            this.hours = smpteTimecode.hours;
            this.minutes = smpteTimecode.minutes;
            this.seconds = smpteTimecode.seconds;
            this.frames = smpteTimecode.frames;
            this.timeCodeToFrameCount();
        } else if (timecode == null) {
            this.frameCount = 0;
        } else {
            throw new Error("Timecode() constructor expects a number, timecode string, or Date()");
        }
        validate();
    }

    /**
     * Validates timecode
     *
     * @param {number|String|Date|Object} timeCode for the reference
     * @private
     */
    private void validate() throws Error {
        // Make sure dropFrame is only for 29.97 & 59.94
        if (this.dropFrame && this.frameRate != 29.97 && this.frameRate != 59.94) {
            throw new Error("Drop frame is only supported for 29.97 and 59.94 fps");
        }

        // make sure the numbers make sense
        if (this.hours > 23 || this.minutes > 59 ||
                this.seconds > 59 || this.frames >= this.frameRate ||
                (this.dropFrame && this.seconds == 0 && (this.minutes % 10 > 0) && (this.frames < 2 * (this.frameRate / 29.97)))) {
            throw new Error("Invalid timecode");
        }
    }

    /**
     * Calculate timecode based on frame count
     *
     * @private
     */
    private void frameCountToTimeCode() {
        long fc = this.frameCount;
        // adjust for dropFrame
        if (this.dropFrame) {
            int df = (this.frameRate == 29.97) ? 2 : 4; // 59.94 skips 4 frames
            double d = Math.floor(this.frameCount / (17982 * df / 2));
            double m = this.frameCount % (17982 * df / 2);
            if (m < df)
                m = m + df;
            fc += 9 * df * d + df * Math.floor((m - df) / (1798 * df / 2));
        }
        long fps = Math.round(this.frameRate);
        this.frames = fc % fps;
        this.seconds = (int) (Math.floor(fc / fps) % 60);
        this.minutes = (int) (Math.floor(fc / (fps * 60)) % 60);
        this.hours = (int) (Math.floor(fc / (fps * 3600)) % 24);
    }

    /**
     * Calculate frame count based on time Timecode
     *
     * @private
     */
    private void timeCodeToFrameCount() {
        this.frameCount = (this.hours * 3600 + this.minutes * 60 + this.seconds) * Math.round(this.frameRate) + this.frames;
        // adjust for dropFrame
        if (this.dropFrame) {
            int totalMinutes = this.hours * 60 + this.minutes;
            int df = (this.frameRate == 29.97) ? 2 : 4;
            this.frameCount -= df * (totalMinutes - Math.floor(totalMinutes / 10));
        }
    }

    /**
     * Convert Timecode to String
     *
     * @param {String} format output format
     * @returns {string} timecode
     */
    public String toString(String format) throws Error {
        long frames = this.frames;
        String field = "";
        if (format != null) {
            if (format.equals("field")) {
                if (this.frameRate <= 30) field = ".0";
                else {
                    frames = (long) Math.floor(frames / 2);
                    field = "." + (this.frameCount % 2);
                }
                ;
            } else
                throw new Error("Unsupported string format");
        }

        return ((this.hours < 10) ? "0" : "") + this.hours + ":" + ((this.minutes < 10) ? "0" : "") +
                this.minutes + ":" + (this.seconds < 10 ? "0" : "") + this.seconds + (this.dropFrame ? ';' : ':') + (frames < 10 ? "0" : "") + frames + field;
    }

    /**
     * @returns {Number} the frame count when Timecode() object is used as a number
     */
    public long valueOf() {
        return this.frameCount;
    }

    /**
     * Adds t to timecode, in-place (i.e. the object itself changes)
     *
     * @param {number|string|Date|Timecode} t How much to add
     * @param {boolean}                     [negative=false] Whether we are adding or subtracting
     * @param {Number}                      [rollOverMaxHours] allow rollovers
     * @returns {Timecode} timecode
     */
    public void add(Float timecode, boolean negative, int rollOverMaxHours) throws Error {
        long newFrameCount = this.frameCount + Math.round(timecode.floatValue()) * (negative ? -1 : 1);
        if (newFrameCount < 0 && rollOverMaxHours > 0) {
            newFrameCount = (Math.round(this.frameRate * 86400)) + newFrameCount;
            if (((newFrameCount / this.frameRate) / 3600) > rollOverMaxHours) {
                throw new Error("Rollover arithmetic exceeds max permitted");
            }
        }
        if (newFrameCount < 0) {
            throw new Error("Negative timecodes not supported");
        }
        this.frameCount = newFrameCount;
        this.frameCount = this.frameCount % (Math.round(this.frameRate * 86400)); // wraparound 24h
        frameCountToTimeCode();
    }

    public void subtract(Float timecode) {
        add(timecode, true, 0);
    }

    /**
     * Converts timecode to a Date() object
     *
     * @returns {Date} date
     */
    public Date toDate() {
        long ms = Double.valueOf(this.frameCount / this.frameRate * 1000).longValue();
        Calendar midnight = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        Date d = new Date(midnight.getTime().getTime() + ms);
        int midnight_tz = TIMEZONE_OFFSET * 60 * 1000;
        int timecode_tz = TIMEZONE_OFFSET * 60 * 1000;
        return new Date(midnight.getTime().getTime() + ms + (timecode_tz - midnight_tz));
    }

    /**
     * frameCount와 frameRate를 기반으로 밀리세컨드 시간을 구한다.
     * @return Milliseconds
     */
    public long getMillis() {
        return Double.valueOf(this.frameCount / this.frameRate * 1000).longValue();
    }

}
