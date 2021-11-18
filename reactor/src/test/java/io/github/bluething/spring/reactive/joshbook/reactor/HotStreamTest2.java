package io.github.bluething.spring.reactive.joshbook.reactor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class HotStreamTest2 {

    @Test
    public void hot() throws InterruptedException {
        int factor = 10;
        System.out.println("Start");
        var cdl = new CountDownLatch(2);
        Flux<Integer> live = Flux.range(0, 10).delayElements(Duration.ofMillis(factor)).share();
        var one = new ArrayList<Integer>();
        var two = new ArrayList<Integer>();
        // Because this subscriber subscribes immediately it will see all the values
        live.doFinally(signalTypeConsumer(cdl)).subscribe(collect(one));
        Thread.sleep(factor * 2);
        // Only see values that published when this subscriber subscribes
        live.doFinally(signalTypeConsumer(cdl)).subscribe(collect(two));
        cdl.await(5, TimeUnit.SECONDS);

        System.out.println("Size one: " + one.size());
        System.out.println("Size two: " + two.size());

        Assertions.assertTrue(one.size() > two.size());

        System.out.println("Stop");
    }

    private Consumer<SignalType> signalTypeConsumer(CountDownLatch cdl) {
        return signal -> {
            if (signal.equals(SignalType.ON_COMPLETE)) {
                try {
                    cdl.countDown();
                    System.out.println("await()...");
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private Consumer<Integer> collect(List<Integer> ints) {
        return ints::add;
    }
}
