package com.springbootdev.samples.springbootasynctask.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncWorkerFuture
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncWorkerFuture.class);

    @Async
    public Future<String> execute()
    {

        LOGGER.info("AsyncWorkerFuture: current thread [" + Thread.currentThread().getName() + "]");
        try {
            Thread.sleep(10000);
            LOGGER.info("AsyncWorkerFuture returns after sleep");
        } catch (InterruptedException ex) {
            LOGGER.info(" sleeping thread interrupted ");
        }

        return new AsyncResult<String>(Long.toString(System.currentTimeMillis()));
    }
}
