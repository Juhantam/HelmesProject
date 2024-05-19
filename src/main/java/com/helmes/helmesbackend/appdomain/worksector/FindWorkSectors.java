package com.helmes.helmesbackend.appdomain.worksector;

import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSector;
import lombok.Value;

import java.util.Set;

@FunctionalInterface
public interface FindWorkSectors {
    Set<WorkSector> execute();
}
