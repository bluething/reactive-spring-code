package io.github.bluething.spring.io;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Consumer;

@Log4j2
public class Synchronous implements Reader {
    @Override
    public void read(File file, Consumer<Bytes> consumer, Runnable finished) throws IOException {
        try (FileInputStream fin = new FileInputStream(file)) {
            byte[] data = new byte[FileCopyUtils.BUFFER_SIZE];
            int res;
            while ((res = fin.read(data, 0, data.length)) != -1) {
                consumer.accept(Bytes.from(data, res));
            }
            finished.run();
        }
    }
}
