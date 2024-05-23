package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.BaseAdapterTest;
import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.SavePersonWorkSectorsInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CreatePersonWorkSectorsInfoRepositoryAdapterTest extends BaseAdapterTest {
    @Autowired
    private CreatePersonWorkSectorsInfoRepositoryAdapter
            createPersonWorkSectorsInfoRepositoryAdapter;

    @Test
    void execute_save_personWorkSectorsInfo_returnsPersonId() {
        Long personWorkSectorsInfoId =
                createPersonWorkSectorsInfoRepositoryAdapter
                        .execute(SavePersonWorkSectorsInfo.Request.of(
                                PersonWorkSectorsInfo.Id.of(null), Person.Id.of(1L), true));

        assertThat(personWorkSectorsInfoId).isNotNull();
    }

}