package org.ea;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class TestEncoder {
    private DataEncoder de;

    @Before
    public void before() {
        de = new DataEncoder();
    }

    @Test
    public void testWriteBytes() {
        for (int i = 0; i < 256; i++) {
            String text = CharacterEncoding.BYTE.writeIntToBits(i, 8);
            Assert.assertTrue(text.length() == 8);
            byte[] bytes = de.strToBytes(text);

            if ((byte)i != bytes[0]) {
                System.out.println(text);
                System.out.println((byte)i);
                System.out.println(bytes[0]);
            }
        }
    }
}
