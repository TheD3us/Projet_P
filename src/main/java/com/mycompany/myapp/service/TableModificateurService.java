package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TableModificateur;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TableModificateur}.
 */
public interface TableModificateurService {

    /**
     * Save a tableModificateur.
     *
     * @param tableModificateur the entity to save.
     * @return the persisted entity.
     */
    TableModificateur save(TableModificateur tableModificateur);

    /**
     * Get all the tableModificateurs.
     *
     * @return the list of entities.
     */
    List<TableModificateur> findAll();


    /**
     * Get the "id" tableModificateur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableModificateur> findOne(Long id);

    /**
     * Delete the "id" tableModificateur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the tableModificateur corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<TableModificateur> search(String query);
}
