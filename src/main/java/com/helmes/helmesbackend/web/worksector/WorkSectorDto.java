package com.helmes.helmesbackend.web.worksector;

import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkSectorDto {
    Long id;
    String name;
    Long parentSectorId;

    public static WorkSectorDto of(WorkSector workSector) {
        return WorkSectorDto.builder()
                            .id(workSector.getId()
                                          .getValue())
                            .name(workSector.getName())
                            .parentSectorId(workSector.getParentSectorId()
                                                      .getValue())
                            .build();
    }
}
