package io.github.bluething.spring.io;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Log4j2
public class Asynchronous implements Reader, CompletionHandler<Integer, ByteBuffer> {
    private Consumer<Bytes> consumer;
    private Runnable finished;
    private AsynchronousFileChannel fileChannel;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private long position;

    @Override
    public void read(File file, Consumer<Bytes> consumer, Runnable finished) throws IOException {
        this.consumer = consumer;
        this.finished = finished;
        Path path = file.toPath();
        this.fileChannel = AsynchronousFileChannel.open(
                path, Collections.singleton(StandardOpenOption.READ), this.executorService);
        ByteBuffer buffer = ByteBuffer.allocate(FileCopyUtils.BUFFER_SIZE);
        this.fileChannel.read(buffer, position, buffer, this);
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        log.error(exc);
    }
}
