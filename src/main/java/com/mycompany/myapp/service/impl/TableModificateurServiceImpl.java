package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TableModificateurService;
import com.mycompany.myapp.domain.TableModificateur;
import com.mycompany.myapp.repository.TableModificateurRepository;
import com.mycompany.myapp.repository.search.TableModificateurSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TableModificateur}.
 */
@Service
@Transactional
public class TableModificateurServiceImpl implements TableModificateurService {

    private final Logger log = LoggerFactory.getLogger(TableModificateurServiceImpl.class);

    private final TableModificateurRepository tableModificateurRepository;

    private final TableModificateurSearchRepository tableModificateurSearchRepository;

    public TableModificateurServiceImpl(TableModificateurRepository tableModificateurRepository, TableModificateurSearchRepository tableModificateurSearchRepository) {
        this.tableModificateurRepository = tableModificateurRepository;
        this.tableModificateurSearchRepository = tableModificateurSearchRepository;
    }

    @Override
    public TableModificateur save(TableModificateur tableModificateur) {
        log.debug("Request to save TableModificateur : {}", tableModificateur);
        TableModificateur result = tableModificateurRepository.save(tableModificateur);
        tableModificateurSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableModificateur> findAll() {
        log.debug("Request to get all TableModificateurs");
        return tableModificateurRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TableModificateur> findOne(Long id) {
        log.debug("Request to get TableModificateur : {}", id);
        return tableModificateurRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableModificateur : {}", id);
        tableModificateurRepository.deleteById(id);
        tableModificateurSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableModificateur> search(String query) {
        log.debug("Request to search TableModificateurs for query {}", query);
        return StreamSupport
            .stream(tableModificateurSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
