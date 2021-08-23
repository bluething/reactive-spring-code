package io.github.bluething.spring.io;

import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class Io {
    public void synchronousRead(File source, Consumer<Bytes> onBytes, Runnable whenDone) {
        try {
            Synchronous synchronous = new Synchronous();
            synchronous.read(source, onBytes, whenDone);
        }
        catch (IOException e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
    }
}
