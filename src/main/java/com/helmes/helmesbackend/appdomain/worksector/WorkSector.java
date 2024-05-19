package com.helmes.helmesbackend.appdomain.worksector;

import com.helmes.helmesbackend.adapter.database.worksector.WorkSectorEntity;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder
public class WorkSector {
    Id id;
    String name;
    Id parentSectorId;

    @Value(staticConstructor = "of")
    public static class Id {
        Long value;
    }

    public static Set<WorkSector> toDomain(Collection<WorkSectorEntity> workSectorEntities) {
        return workSectorEntities.stream()
                                 .map(workSectorEntity -> WorkSector.builder()
                                                                    .id(Id.of(workSectorEntity.getId()))
                                                                    .name(workSectorEntity.getName())
                                                                    .parentSectorId(Id.of(workSectorEntity.getParentSectorId()))
                                                                    .build())
                                 .collect(Collectors.toSet());
    }
}
