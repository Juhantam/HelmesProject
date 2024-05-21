package com.helmes.helmesbackend.appdomain.personworksector;

import com.helmes.helmesbackend.appdomain.person.Person;
import lombok.Value;

import java.util.Set;

@FunctionalInterface
public interface SavePersonWorkSectorsInfo {
    Long execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Person.Id personId;
        Boolean isAcceptTermsOfService;
    }
}
