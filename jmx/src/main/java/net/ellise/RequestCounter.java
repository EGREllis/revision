package net.ellise;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestCounter implements RequestCounterMBean {
    private final AtomicInteger requestCount = new AtomicInteger(0);

    @Override
    public int getRequestCount() {
        return requestCount.get();
    }

    public void increment() {
        requestCount.incrementAndGet();
    }
}
