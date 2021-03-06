package io.github.bluething.spring.io;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public interface Reader {
    void read(File file, Consumer<Bytes> consumer, Runnable finished) throws IOException;
}
