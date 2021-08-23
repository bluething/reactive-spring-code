package io.github.bluething.spring.io;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class IOTest {
    private static final Logger log = LoggerFactory.getLogger(IOTest.class);

    private final AtomicLong count = new AtomicLong();

    private final Consumer<Bytes> bytesConsumer = bytes -> this.count.getAndAccumulate(
            bytes.getLength(),
            (previousValue, updateValue) -> previousValue + updateValue);

    private final Resource resource = new ClassPathResource("/data.txt");

    private final Io io = new Io();

    private final File file = Files.createTempFile("io-test-data", ".txt").toFile();

    private final CountDownLatch latch = new CountDownLatch(1);

    private final Runnable onceDone = () -> {
        log.info("counted " + this.count.get() + " bytes");
        this.latch.countDown();
    };

    public IOTest() throws IOException {

    }

    @BeforeEach
    public void before() throws IOException {
        this.count.set(0);
        try (InputStream in = this.resource.getInputStream();
             OutputStream out = new FileOutputStream(this.file)) {
            FileCopyUtils.copy(in, out);
        }
    }

    @AfterEach
    public void tearDown() {
        if (this.file.exists()) {
            Assertions.assertTrue(this.file.delete());
        }
    }
    @Test
    public void synchronousRead() {
        test(() -> this.io.synchronousRead(this.file, this.bytesConsumer, this.onceDone));
    }

    private void test(Runnable r) {
        try {
            r.run();
            this.latch.await();
            Assertions.assertEquals(this.count.get(), this.file.length());
        }
        catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

    }
}
