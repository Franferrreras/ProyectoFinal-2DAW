package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.models.Comercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercialRepository extends JpaRepository<Comercial, Long> {

}
