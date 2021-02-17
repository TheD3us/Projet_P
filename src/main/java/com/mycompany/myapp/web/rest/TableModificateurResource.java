package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TableModificateur;
import com.mycompany.myapp.service.TableModificateurService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TableModificateur}.
 */
@RestController
@RequestMapping("/api")
public class TableModificateurResource {

    private final Logger log = LoggerFactory.getLogger(TableModificateurResource.class);

    private final TableModificateurService tableModificateurService;

    public TableModificateurResource(TableModificateurService tableModificateurService) {
        this.tableModificateurService = tableModificateurService;
    }

    /**
     * {@code GET  /table-modificateurs} : get all the tableModificateurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableModificateurs in body.
     */
    @GetMapping("/table-modificateurs")
    public List<TableModificateur> getAllTableModificateurs() {
        log.debug("REST request to get all TableModificateurs");
        return tableModificateurService.findAll();
    }

    /**
     * {@code GET  /table-modificateurs/:id} : get the "id" tableModificateur.
     *
     * @param id the id of the tableModificateur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableModificateur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-modificateurs/{id}")
    public ResponseEntity<TableModificateur> getTableModificateur(@PathVariable Long id) {
        log.debug("REST request to get TableModificateur : {}", id);
        Optional<TableModificateur> tableModificateur = tableModificateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableModificateur);
    }

    /**
     * {@code SEARCH  /_search/table-modificateurs?query=:query} : search for the tableModificateur corresponding
     * to the query.
     *
     * @param query the query of the tableModificateur search.
     * @return the result of the search.
     */
    @GetMapping("/_search/table-modificateurs")
    public List<TableModificateur> searchTableModificateurs(@RequestParam String query) {
        log.debug("REST request to search TableModificateurs for query {}", query);
        return tableModificateurService.search(query);
    }
}
