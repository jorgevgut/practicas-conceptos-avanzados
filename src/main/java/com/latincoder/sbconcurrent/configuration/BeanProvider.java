package com.latincoder.sbconcurrent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class BeanProvider {

    /**
     * Scope is defined as 'singleton' intentionally even though it's Spring's default
     * the reason for this is that whenever a new developer looks at this code, she or he
     * does not need to know Spring to understand that this object is ment to be a Singleton
     *
     * By default access runtime's availableProcessors to use the numer of cores as threads.
     * This is common default value for multithreaded apps. Althought the "best" number of
     * threads can only be determined based on specific use case
     * @return ExecutorService
     */
    @Bean
    @Scope("singleton")
    public ExecutorService executorService() {
        return Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors());
    }

}
