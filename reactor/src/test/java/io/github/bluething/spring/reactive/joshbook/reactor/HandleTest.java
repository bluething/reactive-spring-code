package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandleTest {
    @Test
    public void handle() {
        // emit an exception, when reach 4
        // never emits completion signal
        StepVerifier.create(this.handle(5, 4))
                .expectNext(0, 1, 2, 3)
                .expectError(IllegalArgumentException.class)
                .verify();

        // never emit an error signal
        // completes and emits a completion signal
        StepVerifier.create(this.handle(3, 3))
                .expectNext(0, 1, 2)
                .verifyComplete();
    }

    Flux<Integer> handle(int max, int numberToError) {
        return Flux
                .range(0, max)
                .handle((value, sink) -> {
                    var upTo = Stream.iterate(0, i -> i < numberToError, i -> i + 1)
                            .collect(Collectors.toList());

                    if (upTo.contains(value)) {
                        sink.next(value);
                        return;
                    }

                    if (value == numberToError) {
                        sink.error(new IllegalArgumentException("No 4 you!"));
                        return;
                    }

                    sink.complete();
                });
    }
}
