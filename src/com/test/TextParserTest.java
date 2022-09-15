package com.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {

    @Test
    public short read() {
        String[] test = {"test",};
        Assertions.assertEquals("test",read() );
        return 0;
    }
}