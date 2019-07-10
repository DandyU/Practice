package dandyu.im;

import org.junit.Test;

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
        assertEquals(true, timecode.isDropFrame());
        assertEquals(29.97, timecode.getFrameRate(), 29.97);
    }



        /*

        it ('initializing from an object',function(){
            var t = new Timecode( {hours:12, minutes:34, seconds:56, frames:2 } );
            expect(t.toString()).to.be('12:34:56;02');
        });

        it ('initialization defaults', function() {
            var t = Timecode();
            expect(t.frameCount).to.be(0);
            expect(t.frameRate).to.be(29.97);
            expect(t.dropFrame).to.be(true);
            expect(Timecode(1).dropFrame).to.be(true);
            expect(Timecode(1).frameRate).to.be(29.97);
            expect(Timecode(1,29.97).dropFrame).to.be(true);
            expect(Timecode(1,59.94).dropFrame).to.be(true);
            expect(Timecode(1,25).dropFrame).to.be(false);
        });

        it ('drop-frame only for 29.97 and 59.94', function() {
            expect(function(){Timecode(0,30,true)}).to.throwException();
            expect(function(){Timecode(0,59.94,true)}).to.not.throwException();
        });

        it ('drop-frame counts', function() {
            expect(Timecode('00:10:00;00').frameCount).to.be(17982);
            expect(Timecode('00:10:00;00',59.94).frameCount).to.be(17982*2);
            expect(Timecode('10:00:00;00').frameCount).to.be(1078920);
            expect(Timecode('10:00:00;00',59.94).frameCount).to.be(1078920*2);
            expect(function(){Timecode('00:02:00;00')}).to.throwError();
            expect(function(){Timecode('00:02:00;02')}).to.not.throwError();
            expect(function(){Timecode('00:02:00;00',59.94)}).to.throwError();
            expect(function(){Timecode('00:02:00;02',59.94)}).to.throwError();
            expect(function(){Timecode('00:02:00;04',59.94)}).to.not.throwError();
            expect(Timecode('00:01:59;29').frameCount).to.be(3597);
            expect(Timecode('00:01:59;59',59.94).frameCount).to.be(3597*2+1);
            expect(Timecode(17982,29.97,true).toString()).to.be('00:10:00;00');
            expect(Timecode(1078920,29.97,true).toString()).to.be('10:00:00;00');
            expect(Timecode(3597,29.97,true).toString()).to.be('00:01:59;29');
            expect(Timecode(17982*2,59.94,true).toString()).to.be('00:10:00;00');
            expect(Timecode(1078920*2,59.94,true).toString()).to.be('10:00:00;00');
            expect(Timecode(3597*2+1,59.94,true).toString()).to.be('00:01:59;59');
        });
        it ('non-drop-frame counts', function() {
            expect(Timecode('00:10:00:00',25).frameCount).to.be(15000);
            expect(Timecode('10:00:00:00',25).frameCount).to.be(900000);
            expect(Timecode('00:02:00:00',25).frameCount).to.be(3000);
            expect(Timecode('00:01:59:24',25).frameCount).to.be(2999);
            expect(Timecode(15000,25).toString()).to.be('00:10:00:00');
            expect(Timecode(900000,25).toString()).to.be('10:00:00:00');
            expect(Timecode(2999,25).toString()).to.be('00:01:59:24');
        });
    }*/

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
}