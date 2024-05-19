package com.helmes.helmesbackend.appdomain.personworksector;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder
public class PersonWorkSector {
    Id id;
    Person.Id personId;
    WorkSector.Id workSectorId;

    @Value(staticConstructor = "of")
    public static class Id {
        Long value;
    }

    public static Set<PersonWorkSector> toPersonWorkSectors(Set<Long> workSectorsIds,
                                                            Person.Id personId) {
        return workSectorsIds.stream()
                             .map(workSectorId -> PersonWorkSector.builder()
                                                                  .personId(personId)
                                                                  .workSectorId(WorkSector.Id.of(workSectorId))
                                                                  .build())
                             .collect(Collectors.toSet());
    }
}
