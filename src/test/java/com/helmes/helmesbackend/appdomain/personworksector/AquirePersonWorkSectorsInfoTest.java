package com.helmes.helmesbackend.appdomain.personworksector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AquirePersonWorkSectorsInfoTest {
    private static final long PERSON_WORK_SECTORS_INFO_ID = 1L;

    @Mock
    private GetPersonWorkSectorsInfo getPersonWorkSectorsInfo;

    @InjectMocks
    private AquirePersonWorkSectorsInfo aquirePersonWorkSectorsInfo;

    @Test
    void execute_personWorkSectorsInfoExists_returnsPersonWorkSectorsInfo() {
        PersonWorkSectorsInfo personWorkSectorsInfo = PersonWorkSectorsInfo.builder()
                                                                           .id(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID))
                                                                           .build();
        doReturn(personWorkSectorsInfo).when(getPersonWorkSectorsInfo)
                                       .execute(GetPersonWorkSectorsInfo.Request.of(PERSON_WORK_SECTORS_INFO_ID));

        PersonWorkSectorsInfo result =
                aquirePersonWorkSectorsInfo.execute(AquirePersonWorkSectorsInfo.Request.of(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID)));

        assertThat(result).isEqualTo(personWorkSectorsInfo);
    }

    @Test
    void execute_personWorkSectorsInfoDoesNotExist_returnsPersonWorkSectorsInfo() {
        doReturn(null).when(getPersonWorkSectorsInfo)
                                       .execute(GetPersonWorkSectorsInfo.Request.of(PERSON_WORK_SECTORS_INFO_ID));

        PersonWorkSectorsInfo result =
                aquirePersonWorkSectorsInfo.execute(AquirePersonWorkSectorsInfo.Request.of(PersonWorkSectorsInfo.Id.of(PERSON_WORK_SECTORS_INFO_ID)));

        assertThat(result).isNull();
    }

}