package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

// Schedulers.onScheduleHook allow us to modify the Runnable that ultimately gets executed by the Reactor Scheduler
// We don't need to change the global Scheduler to influence how (and where) a single stream is executed
public class SchedulersHookTest {
    @Test
    public void onScheduleHook() {
        var counter = new AtomicInteger();
        Schedulers.onScheduleHook("my hook", runnable -> () -> {
            var threadName = Thread.currentThread().getName();
            counter.incrementAndGet();
            System.out.println("before execution: " + threadName);
            runnable.run();
            System.out.println("after execution: " + threadName);
        });
        Flux<Integer> integerFlux = Flux.just(1, 2, 3).delayElements(Duration.ofMillis(1))
                .subscribeOn(Schedulers.immediate());
        StepVerifier.create(integerFlux).expectNext(1, 2, 3).verifyComplete();
        Assertions.assertEquals(3, counter.get());
    }
}
