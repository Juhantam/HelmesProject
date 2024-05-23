package com.helmes.helmesbackend.appdomain.personworksector;

import com.helmes.helmesbackend.appdomain.person.Person;
import lombok.Value;

@FunctionalInterface
public interface SavePersonWorkSectorsInfo {
    Long execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        PersonWorkSectorsInfo.Id id;
        Person.Id personId;
        Boolean isAcceptTermsOfService;
    }
}
