package org.ea;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataEncoderTest extends TestCase {
    private CharacterEncoding enc = CharacterEncoding.BYTE;

    @Test
    public void testSingleBit() {
        Assert.assertEquals("1", enc.writeIntToBits(1, 1));
    }

    @Test
    public void testDoubleBit() {
        Assert.assertEquals("10", enc.writeIntToBits(2, 2));
    }

    @Test
    public void testDoubleBitOne() {
        Assert.assertEquals("01", enc.writeIntToBits(1, 2));
    }

    @Test
    public void testByteOne() {
        Assert.assertEquals("00000001", enc.writeIntToBits(1, 8));
    }

    @Test
    public void testByteMax() {
        Assert.assertEquals("11111111", enc.writeIntToBits(255, 8));
    }

    @Test
    public void testMoreThanByte() {
        Assert.assertEquals("0000000100000000", enc.writeIntToBits(256, 16));
    }

    @Test
    public void testNumericMessage() {
        Assert.assertEquals(
            "110110001110000100101001",
            CharacterEncoding.NUMERIC.encodeMessage("8675309")
        );
    }

    @Test
    public void testAlphaNumericMessage() {
        Assert.assertEquals(
                "0110000101101111000110100010111001011011100010011010100001101",
                CharacterEncoding.ALPLANUMERIC.encodeMessage("HELLO WORLD")
        );
    }

}