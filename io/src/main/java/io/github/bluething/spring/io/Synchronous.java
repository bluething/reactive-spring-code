package io.github.bluething.spring.io;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Consumer;

@Log4j2
public class Synchronous implements Reader {
    @Override
    public void read(File file, Consumer<Bytes> consumer, Runnable finished) throws IOException {
        try (FileInputStream fin = new FileInputStream(file)) {

        }
    }
}
