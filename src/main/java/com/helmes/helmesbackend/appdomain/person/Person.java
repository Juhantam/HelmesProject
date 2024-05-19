package com.helmes.helmesbackend.appdomain.person;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Person {
    Id id;
    String name;

    @Value(staticConstructor = "of")
    public static class Id {
        Long value;
    }

}
