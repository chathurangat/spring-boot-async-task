package com.springbootdev.samples.springbootasynctask;

import com.springbootdev.samples.springbootasynctask.worker.AsyncWorker;
import com.springbootdev.samples.springbootasynctask.worker.AsyncWorkerFuture;
import com.springbootdev.samples.springbootasynctask.worker.NonAsyncWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class WorkerService
{
    @Autowired
    private AsyncWorker asyncWorker;

    @Autowired
    private NonAsyncWorker nonAsyncWorker;

    @Autowired
    private AsyncWorkerFuture asyncWorkerReturn;

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerService.class);

    public void runNonAsyncWorkers()
    {
        long startTime = System.currentTimeMillis();
        LOGGER.info(" start of executing all non async workers ");
        for (int round = 0; round < 5; round++) {
            nonAsyncWorker.execute();
        }
        LOGGER.info(" Non Async workers: total execution time [" + ((System.currentTimeMillis() - startTime))/1000 + "] seconds ");
    }

    public void runAsyncWorkers()
    {
        long startTime = System.currentTimeMillis();
        LOGGER.info(" start of executing all async workers ");
        for (int round = 0; round < 5; round++) {
            asyncWorker.execute();
        }
        LOGGER.info(" Async workers: total execution time [" + ((System.currentTimeMillis() - startTime))/1000 + "] seconds ");
    }


    public void runAsyncWorkersWithFutureReturnType() throws Exception
    {
        LOGGER.info(" start of  returning async worker");
        Future<String> future = asyncWorkerReturn.execute();
        String result = (String) this.getResultOfFuture(future);

        LOGGER.info(" returned result is [" + result + "] ");
    }


    private Object getResultOfFuture(Future future) throws Exception {
        if (future.isDone()) {
            return future.get();
        }
        LOGGER.info(" waiting for result ");
        Thread.sleep(1000);
        return getResultOfFuture(future);
    }
}
