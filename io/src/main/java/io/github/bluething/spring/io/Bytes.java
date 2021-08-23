package io.github.bluething.spring.io;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bytes {
    private final byte[] bytes;
    private final int length;

    public static Bytes from(byte[] bytes, int length) {
        return new Bytes(bytes, length);
    }
}
