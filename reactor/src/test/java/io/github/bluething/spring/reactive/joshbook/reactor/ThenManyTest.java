package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicInteger;

public class ThenManyTest {
    @Test
    public void thenMany() {
        var letters = new AtomicInteger();
        Flux<String> letterPublisher = Flux.just("a", "b", "c")
                .doOnNext(s -> letters.incrementAndGet());

        var numbers = new AtomicInteger();
        Flux<Integer> numberPublisher = Flux.just(1, 2, 3)
                .doOnNext(i -> numbers.incrementAndGet());

        Flux<Integer> thisBeforeThat = letterPublisher.thenMany(numberPublisher);

        StepVerifier.create(thisBeforeThat)
                .expectNext(1, 2, 3)
                .verifyComplete();

        Assertions.assertEquals(3, letters.get());
        Assertions.assertEquals(3, numbers.get());
    }
}
