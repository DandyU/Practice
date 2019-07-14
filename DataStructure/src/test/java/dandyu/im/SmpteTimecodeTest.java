package dandyu.im;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SmpteTimecodeTest {
    @Test
    public void testConstructor() {
        SmpteTimecode timecode = new SmpteTimecode(new Float(100), null, null);

        timecode = new SmpteTimecode(new Float(15), null, null);
        assertEquals(timecode.getFrameCount(), 15);

        timecode = new SmpteTimecode(new Float(323.443), null, null);
        assertEquals(timecode.getFrameCount(), 323);

        timecode = new SmpteTimecode(new Float(323.443), null, null);
        assertEquals(timecode.getFrameCount(), 323);

        timecode = new SmpteTimecode(new Float(89881), null, null);
        System.out.println("05:00:00: " + timecode.toString(null));
        System.out.println("dropFrameCount: " + timecode.getFrames());
        timecode = new SmpteTimecode(new Float(10800), null, null);
        System.out.println("05:00:00: " + timecode.toString(null));
        System.out.println("dropFrameCount: " + timecode.getFrames());
        timecode = new SmpteTimecode(new Float(991), null, null);
        System.out.println("05:00:00: " + timecode.toString(null));
        System.out.println("dropFrameCount: " + timecode.getFrames());
        timecode = new SmpteTimecode(new Float(1), null, null);
        System.out.println("05:00:00: " + timecode.getMillis());

        timecode = new SmpteTimecode("12:33:44;12", null, null);
        assertEquals(12, timecode.getHours());
        assertEquals(33, timecode.getMinutes());
        assertEquals(44, timecode.getSeconds());
        assertEquals(12, timecode.getFrames());
        assertEquals(true, timecode.isDropFrame());
        assertEquals(29.97, timecode.getFrameRate(), 29.97);

        timecode = new SmpteTimecode("12:33:44:12", null, null);
        assertEquals(12, timecode.getHours());
        assertEquals(33, timecode.getMinutes());
        assertEquals(44, timecode.getSeconds());
        assertEquals(12, timecode.getFrames());
        assertEquals(false, timecode.isDropFrame());
        assertEquals(29.97, timecode.getFrameRate(), 29.97);

        timecode = new SmpteTimecode("12:34:56:02", null, null);
        SmpteTimecode tempTimecode = new SmpteTimecode(timecode, null, null);
        assertEquals("12:34:56;02", tempTimecode.toString(null));

        timecode = new SmpteTimecode(null, null, null);
        assertEquals(0, timecode.getFrameCount());
        assertEquals(29.97, timecode.getFrameRate(), 29.97);
        assertEquals(true, timecode.isDropFrame());

        timecode = new SmpteTimecode(new Float(1), null, null);
        assertEquals(true, timecode.isDropFrame());
        assertEquals(29.97, timecode.getFrameRate(), 29.97);
        timecode = new SmpteTimecode(new Float(1), new Double(29.97), null);
        assertEquals(true, timecode.isDropFrame());
        timecode = new SmpteTimecode(new Float(1), new Double(59.94), null);
        assertEquals(true, timecode.isDropFrame());
        timecode = new SmpteTimecode(new Float(1), new Double(25), null);
        assertEquals(false, timecode.isDropFrame());

        timecode = new SmpteTimecode("00:10:00;00", null, null);
        assertEquals(17982, timecode.getFrameCount());
        timecode = new SmpteTimecode("00:10:00;00", new Double(59.94), null);
        assertEquals(17982 * 2, timecode.getFrameCount());
        timecode = new SmpteTimecode("10:00:00;00", null, null);
        assertEquals(1078920, timecode.getFrameCount());
        timecode = new SmpteTimecode("10:00:00;00", new Double(59.94), null);
        assertEquals(1078920 * 2, timecode.getFrameCount());
        timecode = new SmpteTimecode("00:01:59;29", null, null);
        assertEquals(3597, timecode.getFrameCount());
        timecode = new SmpteTimecode("00:01:59;59", new Double(59.94), null);
        assertEquals(3597*2+1, timecode.getFrameCount());
        timecode = new SmpteTimecode(new Float(17982), new Double(29.97), new Boolean(true));
        assertEquals("00:10:00;00", timecode.toString(null));
        timecode = new SmpteTimecode(new Float(1078920), new Double(29.97), new Boolean(true));
        assertEquals("10:00:00;00", timecode.toString(null));
        timecode = new SmpteTimecode(new Float(3597), new Double(29.97), new Boolean(true));
        assertEquals("00:01:59;29", timecode.toString(null));
        timecode = new SmpteTimecode(new Float(17982*2), new Double(59.94), new Boolean(true));
        assertEquals("00:10:00;00", timecode.toString(null));
        timecode = new SmpteTimecode(new Float(1078920*2), new Double(59.94), new Boolean(true));
        assertEquals("10:00:00;00", timecode.toString(null));
        timecode = new SmpteTimecode(new Float(3597*2+1), new Double(59.94), new Boolean(true));
        assertEquals("00:01:59;59", timecode.toString(null));

        timecode = new SmpteTimecode("00:10:00:00", new Double(25), null);
        assertEquals(15000, timecode.getFrameCount());
        timecode = new SmpteTimecode("10:00:00:00", new Double(25), null);
        assertEquals(900000, timecode.getFrameCount());
        timecode = new SmpteTimecode("00:02:00:00", new Double(25), null);
        assertEquals(3000, timecode.getFrameCount());
        timecode = new SmpteTimecode("00:01:59:24", new Double(25), null);
        assertEquals(2999, timecode.getFrameCount());
        timecode = new SmpteTimecode(new Float(15000), new Double(25), null);
        assertEquals("00:10:00:00", timecode.toString(null));
        timecode = new SmpteTimecode(new Float(900000), new Double(25), null);
        assertEquals("10:00:00:00", timecode.toString(null));
        timecode = new SmpteTimecode(new Float(2999), new Double(25), null);
        assertEquals("00:01:59:24", timecode.toString(null));

        timecode = new SmpteTimecode("12:34:56;23", null, null);
        assertEquals("12:34:56;23", timecode.toString(null));
        timecode = new SmpteTimecode("01:02:03;04", null, null);
        assertEquals("01:02:03;04", timecode.toString(null));
        timecode = new SmpteTimecode("12:34:56;57", new Double(59.94), null);
        assertEquals("12:34:56;57", timecode.toString(null));
        timecode = new SmpteTimecode("01:02:03;04", new Double(59.94), null);
        assertEquals("01:02:03;04", timecode.toString(null));

        timecode = new SmpteTimecode("12:34:56;23", null, null);
        assertEquals("12:34:56;23.0", timecode.toString("field"));
        timecode = new SmpteTimecode("01:02:03;04", null, null);
        assertEquals("01:02:03;04.0", timecode.toString("field"));
        timecode = new SmpteTimecode("12:34:56;57", new Double(59.94), null);
        assertEquals("12:34:56;28.1", timecode.toString("field"));
        timecode = new SmpteTimecode("01:02:03;04", new Double(59.94), null);
        assertEquals("01:02:03;02.0", timecode.toString("field"));

        timecode = new SmpteTimecode("01:23:45;06", null, null);
        timecode.add(new Float(60), false, 0);
        assertEquals("01:23:47;06", timecode.toString(null));
        timecode = new SmpteTimecode("01:23:45;06", null, null);
        timecode.subtract(new Float(60));
        assertEquals("01:23:43;06", timecode.toString(null));
        timecode = new SmpteTimecode("00:01:15;00", null, null);
        tempTimecode = new SmpteTimecode("00:01:15;00", null, null);
        tempTimecode.add(new Float(0), false, 0);
        assertEquals(tempTimecode.getFrameCount(), timecode.getFrameCount());
        tempTimecode.add(new Float(12345), false, 0);
        assertEquals(tempTimecode.getFrameCount() - 12345, timecode.getFrameCount());

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 2);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 200);
        timecode = new SmpteTimecode(cal.getTime(), null, null);
        assertEquals(111884, timecode.getFrameCount());
        assertEquals("01:02:13;06", timecode.toString(null));

        cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 520);
        timecode = new SmpteTimecode(cal.getTime(), new Double(25), new Boolean(false));
        assertEquals(960388, timecode.getFrameCount());
        assertEquals("10:40:15:13", timecode.toString(null));

        timecode = new SmpteTimecode("01:23:45;10", null, null);
        Date date = timecode.toDate();
        cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        cal.setTime(date);
        assertEquals(1, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(23, cal.get(Calendar.MINUTE));
        assertEquals(45, cal.get(Calendar.SECOND));
        assertEquals(358, cal.get(Calendar.MILLISECOND));
    }

    @Test(expected = Error.class)
    public void testException1() {
        SmpteTimecode timecode = new SmpteTimecode(new Float(1), new Double(-1), null);
    }

    @Test(expected = Error.class)
    public void testException2() {
        SmpteTimecode timecode = new SmpteTimecode(new Float(1), new Double(66), null);
    }

    @Test(expected = Error.class)
    public void testException3() {
        SmpteTimecode timecode = new SmpteTimecode("dewdew", null, null);
    }

    @Test(expected = Error.class)
    public void testException4() {
        SmpteTimecode timecode = new SmpteTimecode("{w:3}", null, null);
    }

    @Test(expected = Error.class)
    public void testException5() {
        SmpteTimecode timecode = new SmpteTimecode("40:02:00;02", null, null);
    }

    @Test(expected = Error.class)
    public void testException6() {
        SmpteTimecode timecode = new SmpteTimecode("00:99:00;02", null, null);
    }

    @Test(expected = Error.class)
    public void testException7() {
        SmpteTimecode timecode = new SmpteTimecode("00:02:99;02", null, null);
    }

    @Test(expected = Error.class)
    public void testException8() {
        SmpteTimecode timecode = new SmpteTimecode("00:02:00;35", null, null);
    }

    @Test(expected = Error.class)
    public void testException9() {
        SmpteTimecode timecode = new SmpteTimecode(new Float(0), new Double(30), new Boolean((true)));
    }

    @Test
    public void testException10() {
        SmpteTimecode timecode = new SmpteTimecode(new Float(0), new Double(59.94), new Boolean((true)));
        assertNotEquals(null, timecode);
    }

    @Test(expected = Error.class)
    public void testException11() {
        SmpteTimecode timecode = new SmpteTimecode("00:02:00;00", null, null);
    }

    @Test
    public void testException12() {
        SmpteTimecode timecode = new SmpteTimecode("00:02:00;02", null, null);
        assertNotEquals(null, timecode);
    }

    @Test(expected = Error.class)
    public void testException13() {
        SmpteTimecode timecode = new SmpteTimecode("00:02:00;00", new Double(59.94), null);
    }

    @Test(expected = Error.class)
    public void testException14() {
        SmpteTimecode timecode = new SmpteTimecode("00:02:00;02", new Double(59.94), null);
    }

    @Test
    public void testException15() {
        SmpteTimecode timecode = new SmpteTimecode("00:02:00;04", new Double(59.94), null);
        assertNotEquals(null, timecode);
    }

    @Test(expected = Error.class)
    public void testException16() {
        SmpteTimecode timecode = new SmpteTimecode("01:02:03;04", new Double(59.94), null);
        timecode.toString("Uknown");
    }

    @Test(expected = Error.class)
    public void testExeception17() {
        SmpteTimecode timecode = new SmpteTimecode("00:00:10;00", null, null);
        timecode.add(new Float(-301), false, 0); // below zero
    }

    @Test(expected = Error.class)
    public void testExeception18() {
        SmpteTimecode timecode = new SmpteTimecode("00:00:10;00", null, null);
        timecode.subtract(new Float(301)); // below zero
    }

}
