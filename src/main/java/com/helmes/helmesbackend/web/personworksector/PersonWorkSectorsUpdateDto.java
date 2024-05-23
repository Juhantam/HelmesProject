package com.helmes.helmesbackend.web.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfoUpdateDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonWorkSectorsUpdateDto {
    private String personName;
    private Long personWorkSectorsInfoId;
    private Set<Long> selectedWorkSectorIds;
    private Boolean isAcceptTermsOfService;


    public PersonWorkSectorsInfoUpdateDetails toDomain(PersonWorkSectorsUpdateDto personWorkSectorsCreationDto) {
        return PersonWorkSectorsInfoUpdateDetails.builder()
                                                 .personName(personWorkSectorsCreationDto.getPersonName())
                                                 .personWorkSectorsInfoId(PersonWorkSectorsInfo.Id.of(personWorkSectorsCreationDto.getPersonWorkSectorsInfoId()))
                                                 .workSectorIds(personWorkSectorsCreationDto.getSelectedWorkSectorIds())
                                                 .isAcceptTermsOfService(personWorkSectorsCreationDto.getIsAcceptTermsOfService())
                                                 .build();
    }
}
