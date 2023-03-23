package be.vdab.leerling.repositories;

import be.vdab.leerling.domain.Leerling;

import java.util.List;

public interface LeerlingRepository {
    List<Leerling> findAll();
}
