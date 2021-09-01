package io.github.bluething.spring.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class SimpleFluxFactoriesTest {
    @Test
    public void simple() {

        Publisher<Integer> rangeOfInteger = Flux.range(0, 10);
        StepVerifier.create(rangeOfInteger).expectNextCount(10).verifyComplete();

        Flux<String> letters = Flux.just("A", "B", "C");
        StepVerifier.create(letters).expectNext("A", "B", "C").verifyComplete();

    }
}
