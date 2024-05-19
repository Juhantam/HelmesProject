package com.helmes.helmesbackend.web.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.creation.PersonWorkSectorsCreationDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonWorkSectorsCreationDto {
    private String personName;
    private Set<Long> selectedWorkSectorIds;
    private Boolean isAcceptTermsOfService;


    public PersonWorkSectorsCreationDetails toDomain(PersonWorkSectorsCreationDto personWorkSectorsCreationDto) {
        return PersonWorkSectorsCreationDetails.builder()
                                               .personName(personWorkSectorsCreationDto.getPersonName())
                                               .workSectorIds(personWorkSectorsCreationDto.getSelectedWorkSectorIds())
                                               .isAcceptTermsOfService(personWorkSectorsCreationDto.getIsAcceptTermsOfService())
                                               .build();
    }
}
