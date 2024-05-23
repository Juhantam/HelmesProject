package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSector;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "person_work_sector")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonWorkSectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "work_sector_id", nullable = false)
    private Long workSectorId;
    @Column(name = "person_id", nullable = false)
    private Long personId;

    public PersonWorkSector toDomain() {
        return PersonWorkSector.builder()
                               .id(PersonWorkSector.Id.of(getId()))
                               .workSectorId(WorkSector.Id.of(getWorkSectorId()))
                               .personId(Person.Id.of(getPersonId()))
                               .build();
    }
}
