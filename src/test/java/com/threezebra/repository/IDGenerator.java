package com.threezebra.repository;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author vikas.sharma
 *
 */
public class IDGenerator {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static String getNextId() {
        return String.valueOf(atomicInteger.incrementAndGet());
    }

}
