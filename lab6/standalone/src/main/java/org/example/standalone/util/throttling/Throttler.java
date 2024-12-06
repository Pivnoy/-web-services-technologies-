package org.example.standalone.util.throttling;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;


public final class Throttler {
    private static final Integer limit = 10; // 10 request per minute
    private final Timer timer = new Timer();
    private AtomicInteger count;

    private static Throttler throttler;
    private Throttler(){}

    public static Throttler getInstance(){
        if(throttler == null){
            throttler = new Throttler();
            throttler.count = new AtomicInteger(0);
            throttler.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    throttler.count.set(0);
                }
            }, 0, 1000 * 60);

        }
        return throttler;
    }

    public void throttle() throws ThrottlingException {
        if (this.count.get() > limit) {
            throw new ThrottlingException();
        }

        this.count.incrementAndGet();
    }

}
