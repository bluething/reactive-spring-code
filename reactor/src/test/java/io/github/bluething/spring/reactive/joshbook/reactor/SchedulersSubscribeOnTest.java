package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SchedulersSubscribeOnTest {
    @Test
    public void subscribeOn() {
        var rsbThreadName = SchedulersSubscribeOnTest.class.getName();
        var map = new ConcurrentHashMap<String, AtomicInteger>();
        // Each thread created ends up wrapped in a custom Runnable that notes the name of the current thread and increments the reference count
        var executor = Executors.newFixedThreadPool(5, runnable -> {
            Runnable wrapper = () -> {
                var key = Thread.currentThread().getName();
                var result = map.computeIfAbsent(key, s -> new AtomicInteger());
                result.incrementAndGet();
                runnable.run();
            };
            return new Thread(wrapper, rsbThreadName);
        });
        // Create Scheduler using a custom Executor
        Scheduler scheduler = Schedulers.fromExecutor(executor);
        // subscribeOn will move the subscription to our custom Scheduler
        Mono<Integer> integerMono = Mono.just(1).subscribeOn(scheduler)
                .doFinally(signal -> map.forEach((k, v) -> System.out.println(k + '=' + v)));
        StepVerifier.create(integerMono).expectNextCount(1).verifyComplete();
        var atomicInteger = map.get(rsbThreadName);
        Assertions.assertEquals(1, atomicInteger.get());
    }
}
