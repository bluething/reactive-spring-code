package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ConcatMapTest {
    @Test
    public void flatMap() {
        Flux<Integer> data = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .concatMap(i -> this.delayReplyFor(i.id, i.delay));

        StepVerifier.create(data)
                .expectNext(1, 2, 3)
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
