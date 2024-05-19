package com.helmes.helmesbackend.appdomain.personworksector;

import lombok.Value;

import java.util.Set;

@FunctionalInterface
public interface SavePersonWorkSectors {
    void execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Set<PersonWorkSector> personWorkSectors;
    }
}
