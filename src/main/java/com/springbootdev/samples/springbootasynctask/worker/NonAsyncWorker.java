package com.springbootdev.samples.springbootasynctask.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NonAsyncWorker
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NonAsyncWorker.class);

    public void execute()
    {
        LOGGER.info("NonAsyncWorker : current thread [" + Thread.currentThread().getName() + "]");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOGGER.info(" sleeping thread interrupted ");
        }
    }
}
