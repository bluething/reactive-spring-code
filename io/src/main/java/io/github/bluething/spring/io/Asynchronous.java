package io.github.bluething.spring.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

public class Asynchronous implements Reader {
    private Consumer<Bytes> consumer;
    private Runnable finished;

    @Override
    public void read(File file, Consumer<Bytes> consumer, Runnable finished) throws IOException {
        this.consumer = consumer;
        this.finished = finished;
        Path path = file.toPath();
    }
}
