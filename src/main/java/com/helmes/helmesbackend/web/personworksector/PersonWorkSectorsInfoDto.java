package com.helmes.helmesbackend.web.personworksector;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonWorkSectorsInfoDto {
    private Long id;
    private Person person;
    private Set<Long> selectedWorkSectorIds;
    private Boolean isAcceptTermsOfService;


    public static PersonWorkSectorsInfoDto of(PersonWorkSectorsInfo personWorkSectorsInfo) {
        return PersonWorkSectorsInfoDto.builder()
                                       .id(personWorkSectorsInfo.getId()
                                                                .getValue())
                                       .person(personWorkSectorsInfo.getPerson())
                                       .selectedWorkSectorIds(personWorkSectorsInfo.getSelectedWorkSectorIds()
                                                                                   .stream()
                                                                                   .map(WorkSector.Id::getValue)
                                                                                   .collect(Collectors.toSet()))
                                       .isAcceptTermsOfService(personWorkSectorsInfo.getIsAcceptTermsOfService())
                                       .build();
    }
}
