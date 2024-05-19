package com.helmes.helmesbackend.appdomain.person;

import lombok.Value;

@FunctionalInterface
public interface SavePerson {
    Long execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        String personName;
    }
}
