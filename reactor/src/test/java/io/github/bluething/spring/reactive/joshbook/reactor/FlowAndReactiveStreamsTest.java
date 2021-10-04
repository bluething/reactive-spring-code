package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.FlowAdapters;
import org.reactivestreams.Publisher;
import reactor.adapter.JdkFlowAdapter;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.Flow;

public class FlowAndReactiveStreamsTest {

    @Test
    public void convert() {
        Flux<Integer> original = Flux.range(0, 10);

        Flow.Publisher<Integer> rangeOfIntegerAsJdk9Flow = FlowAdapters
                .toFlowPublisher(original);
        Publisher<Integer> rangeOfIntegerAsReactiveStream = FlowAdapters
                .toPublisher(rangeOfIntegerAsJdk9Flow);

        StepVerifier.create(original)
                .expectNextCount(10)
                .verifyComplete();
        StepVerifier.create(rangeOfIntegerAsReactiveStream)
                .expectNextCount(10)
                .verifyComplete();

        Flux<Integer> rangeIntegerAsReactorFluxAgain = JdkFlowAdapter
                .flowPublisherToFlux(rangeOfIntegerAsJdk9Flow);
        StepVerifier.create(rangeIntegerAsReactorFluxAgain)
                .expectNextCount(10)
                .verifyComplete();
    }
}
