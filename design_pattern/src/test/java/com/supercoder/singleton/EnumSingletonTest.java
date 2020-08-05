package com.supercoder.singleton;

import org.junit.jupiter.api.Test;

class EnumSingletonTest {
    @Test
    public void testEnumSingleton() {
        EnumSingleton.INSTANCE.singletonOperation();
    }
}