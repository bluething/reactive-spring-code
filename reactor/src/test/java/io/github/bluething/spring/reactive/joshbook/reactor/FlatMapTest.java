package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FlatMapTest {
    @Test
    public void flatMap() {
        Flux<Integer> data = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(i -> this.delayReplyFor(i.id, i.delay));

        StepVerifier.create(data)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }

    private Flux<Integer> delayReplyFor(Integer i, long delay) {
        return Flux.just(i).delayElements(Duration.ofMillis(delay));
    }

    static class Pair {
        private int id;
        private long delay;

        Pair(int id, long delay) {
            this.id = id;
            this.delay = delay;
        }
    }
}
