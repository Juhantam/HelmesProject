package com.helmes.helmesbackend.web.personworksector;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
class PersonWorkSectorsCreationResponseDto {
    Long personWorkSectorsId;
}
