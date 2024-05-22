package com.helmes.helmesbackend.adapter.database.worksector;

import com.helmes.helmesbackend.BaseAdapterTest;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class FindWorkSectorsRepositoryAdapterTest extends BaseAdapterTest {
    @Autowired
    private WorkSectorEntityRepository workSectorEntityRepository;

    @Autowired
    private FindWorkSectorsRepositoryAdapter findWorkSectorsRepositoryAdapter;

    @Test
    void execute_personWorkSectorsInfoExists_returnsPersonWorkSectorsInfo() {
        workSectorEntityRepository.deleteAll();
        var workSectorEntities = workSectorEntityRepository.saveAll(buildWorkSectorEntities());

        var workSectors = findWorkSectorsRepositoryAdapter.execute();

        assertThat(workSectors).containsExactlyInAnyOrderElementsOf(WorkSector.toDomain(workSectorEntities));
    }

    private Set<WorkSectorEntity> buildWorkSectorEntities() {
        return Set.of(WorkSectorEntity.builder()
                                      .name("SECTOR1")
                                      .build(), WorkSectorEntity.builder()
                                                                .name("SECTOR2")
                                                                .build(), WorkSectorEntity.builder()
                                                                                          .name("SECTOR3")
                                                                                          .build());
    }

}