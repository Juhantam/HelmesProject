package com.helmes.helmesbackend.appdomain.personworksector;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class PersonWorkSectorsInfo {
    Id id;
    Person person;
    Set<WorkSector.Id> selectedWorkSectorIds;
    Boolean isAcceptTermsOfService;

    @Value(staticConstructor = "of")
    public static class Id {
        Long value;
    }

}
