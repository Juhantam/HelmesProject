package com.helmes.helmesbackend.appdomain.personworksector;

import lombok.Value;

@FunctionalInterface
public interface GetPersonWorkSectorsInfo {
    PersonWorkSectorsInfo execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Long personWorkSectorsInfoId;
    }
}
