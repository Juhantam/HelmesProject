package com.helmes.helmesbackend.appdomain.personworksector;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class PersonWorkSectorsInfoUpdateDetails {
    String personName;
    PersonWorkSectorsInfo.Id personWorkSectorsInfoId;
    Set<Long> workSectorIds;
    Boolean isAcceptTermsOfService;
}
