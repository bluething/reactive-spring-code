package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TakeTest {
    @Test
    public void take() {
        var count = 10;
        Flux<Integer> take = range().take(count);

        StepVerifier.create(take)
                .expectNextCount(count)
                .verifyComplete();
    }

    @Test
    public void takeUntil() {
        var count = 50;
        Flux<Integer> takeUntil50 = range().takeUntil(i -> i == count - 1);

        StepVerifier.create(takeUntil50)
                .expectNextCount(count)
                .verifyComplete();
    }

    private Flux<Integer> range() {
        return Flux.range(0, 1000);
    }
}
