package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ProjetPApp;
import com.mycompany.myapp.domain.TableModificateur;
import com.mycompany.myapp.repository.TableModificateurRepository;
import com.mycompany.myapp.repository.search.TableModificateurSearchRepository;
import com.mycompany.myapp.service.TableModificateurService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TableModificateurResource} REST controller.
 */
@SpringBootTest(classes = ProjetPApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TableModificateurResourceIT {

    private static final Integer DEFAULT_VALEUR = 1;
    private static final Integer UPDATED_VALEUR = 2;

    private static final Integer DEFAULT_MODIFICATEUR = -5;
    private static final Integer UPDATED_MODIFICATEUR = -4;

    @Autowired
    private TableModificateurRepository tableModificateurRepository;

    @Autowired
    private TableModificateurService tableModificateurService;

    /**
     * This repository is mocked in the com.mycompany.myapp.repository.search test package.
     *
     * @see com.mycompany.myapp.repository.search.TableModificateurSearchRepositoryMockConfiguration
     */
    @Autowired
    private TableModificateurSearchRepository mockTableModificateurSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTableModificateurMockMvc;

    private TableModificateur tableModificateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableModificateur createEntity(EntityManager em) {
        TableModificateur tableModificateur = new TableModificateur()
            .valeur(DEFAULT_VALEUR)
            .modificateur(DEFAULT_MODIFICATEUR);
        return tableModificateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableModificateur createUpdatedEntity(EntityManager em) {
        TableModificateur tableModificateur = new TableModificateur()
            .valeur(UPDATED_VALEUR)
            .modificateur(UPDATED_MODIFICATEUR);
        return tableModificateur;
    }

    @BeforeEach
    public void initTest() {
        tableModificateur = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllTableModificateurs() throws Exception {
        // Initialize the database
        tableModificateurRepository.saveAndFlush(tableModificateur);

        // Get all the tableModificateurList
        restTableModificateurMockMvc.perform(get("/api/table-modificateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tableModificateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR)))
            .andExpect(jsonPath("$.[*].modificateur").value(hasItem(DEFAULT_MODIFICATEUR)));
    }
    
    @Test
    @Transactional
    public void getTableModificateur() throws Exception {
        // Initialize the database
        tableModificateurRepository.saveAndFlush(tableModificateur);

        // Get the tableModificateur
        restTableModificateurMockMvc.perform(get("/api/table-modificateurs/{id}", tableModificateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tableModificateur.getId().intValue()))
            .andExpect(jsonPath("$.valeur").value(DEFAULT_VALEUR))
            .andExpect(jsonPath("$.modificateur").value(DEFAULT_MODIFICATEUR));
    }
    @Test
    @Transactional
    public void getNonExistingTableModificateur() throws Exception {
        // Get the tableModificateur
        restTableModificateurMockMvc.perform(get("/api/table-modificateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void searchTableModificateur() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        tableModificateurService.save(tableModificateur);
        when(mockTableModificateurSearchRepository.search(queryStringQuery("id:" + tableModificateur.getId())))
            .thenReturn(Collections.singletonList(tableModificateur));

        // Search the tableModificateur
        restTableModificateurMockMvc.perform(get("/api/_search/table-modificateurs?query=id:" + tableModificateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tableModificateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR)))
            .andExpect(jsonPath("$.[*].modificateur").value(hasItem(DEFAULT_MODIFICATEUR)));
    }
}
