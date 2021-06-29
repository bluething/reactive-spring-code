package io.github.bluething.spring.bootstrap;

import lombok.*;

@Data
@AllArgsConstructor
public class Customer {
    @Setter(AccessLevel.NONE)
    private final Long id;
    @Setter(AccessLevel.NONE)
    private final String name;

}
