package com.nekoscape.android.trafficchecker.util;

import junit.framework.TestCase;

import java.util.concurrent.TimeUnit;

public class ToolTest extends TestCase {

    public void testWaitingTime() throws Exception {

        int expect = 10;
        long result = Tool.waitingTime(expect);

        /* 指定した秒数以下の値が返却される。 */
        assertTrue(result <= TimeUnit.SECONDS.toMillis((long)expect));

    }
}