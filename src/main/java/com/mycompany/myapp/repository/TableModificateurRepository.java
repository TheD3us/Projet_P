package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TableModificateur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TableModificateur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableModificateurRepository extends JpaRepository<TableModificateur, Long> {
}
