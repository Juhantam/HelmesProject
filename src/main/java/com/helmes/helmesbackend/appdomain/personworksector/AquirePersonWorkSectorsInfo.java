package com.helmes.helmesbackend.appdomain.personworksector;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AquirePersonWorkSectorsInfo {
    private final GetPersonWorkSectorsInfo getPersonWorkSectorsInfo;

    public PersonWorkSectorsInfo execute(Request request) {
        return getPersonWorkSectorsInfo.execute(GetPersonWorkSectorsInfo.Request.of(request.getPersonWorkSectorsInfoId()
                                                                                           .getValue()));
    }

    @Value(staticConstructor = "of")
    public static class Request {
        PersonWorkSectorsInfo.Id personWorkSectorsInfoId;
    }
}
