package com.helmes.helmesbackend.appdomain.personworksector;

import lombok.Value;

import java.util.Set;

@FunctionalInterface
public interface GetPersonWorkSectors {
    Set<PersonWorkSector> execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Long personId;
    }
}
