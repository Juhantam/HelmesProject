package com.helmes.helmesbackend.appdomain.personworksector.creation;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class PersonWorkSectorsInfoCreationDetails {
    String personName;
    Set<Long> workSectorIds;
    Boolean isAcceptTermsOfService;
}
