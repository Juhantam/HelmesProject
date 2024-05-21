package com.helmes.helmesbackend.web.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.creation.PersonWorkSectorsInfoCreationDetails;
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


    public PersonWorkSectorsInfoCreationDetails toDomain(PersonWorkSectorsCreationDto personWorkSectorsCreationDto) {
        return PersonWorkSectorsInfoCreationDetails.builder()
                                                   .personName(personWorkSectorsCreationDto.getPersonName())
                                                   .workSectorIds(personWorkSectorsCreationDto.getSelectedWorkSectorIds())
                                                   .isAcceptTermsOfService(personWorkSectorsCreationDto.getIsAcceptTermsOfService())
                                                   .build();
    }
}
