package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ProjetPApp;
import com.mycompany.myapp.domain.Personnage;
import com.mycompany.myapp.repository.PersonnageRepository;
import com.mycompany.myapp.repository.search.PersonnageSearchRepository;

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
 * Integration tests for the {@link PersonnageResource} REST controller.
 */
@SpringBootTest(classes = ProjetPApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonnageResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Integer DEFAULT_CLASSE_ARMURE = 1;
    private static final Integer UPDATED_CLASSE_ARMURE = 2;

    private static final Integer DEFAULT_BONUS_MAITRISE = 1;
    private static final Integer UPDATED_BONUS_MAITRISE = 2;

    private static final Integer DEFAULT_FORCE = 1;
    private static final Integer UPDATED_FORCE = 2;

    private static final Integer DEFAULT_DEXTERITE = 1;
    private static final Integer UPDATED_DEXTERITE = 2;

    private static final Integer DEFAULT_CONSTITUTION = 1;
    private static final Integer UPDATED_CONSTITUTION = 2;

    private static final Integer DEFAULT_INTELLIGENCE = 1;
    private static final Integer UPDATED_INTELLIGENCE = 2;

    private static final Integer DEFAULT_SAGESSE = 1;
    private static final Integer UPDATED_SAGESSE = 2;

    private static final Integer DEFAULT_CHARISME = 1;
    private static final Integer UPDATED_CHARISME = 2;

    private static final Integer DEFAULT_VIE = 1;
    private static final Integer UPDATED_VIE = 2;

    private static final Integer DEFAULT_PERCEPTION_PASSIVE = 1;
    private static final Integer UPDATED_PERCEPTION_PASSIVE = 2;

    private static final Integer DEFAULT_INITIATIVE = 1;
    private static final Integer UPDATED_INITIATIVE = 2;

    private static final Integer DEFAULT_BONUS_ATTAQUE_CAC = 1;
    private static final Integer UPDATED_BONUS_ATTAQUE_CAC = 2;

    private static final Integer DEFAULT_BONUS_ATTAQUE_DISTANCE = 1;
    private static final Integer UPDATED_BONUS_ATTAQUE_DISTANCE = 2;

    private static final Integer DEFAULT_MODIFICATEUR_FORCE = 1;
    private static final Integer UPDATED_MODIFICATEUR_FORCE = 2;

    private static final Integer DEFAULT_MODIFICATEUR_DEXTERITE = 1;
    private static final Integer UPDATED_MODIFICATEUR_DEXTERITE = 2;

    private static final Integer DEFAULT_MODIFICATEUR_CONSTITUTION = 1;
    private static final Integer UPDATED_MODIFICATEUR_CONSTITUTION = 2;

    private static final Integer DEFAULT_MODIFICATEUR_INTELLIGENCE = 1;
    private static final Integer UPDATED_MODIFICATEUR_INTELLIGENCE = 2;

    private static final Integer DEFAULT_MODIFICATEUR_SAGESSE = 1;
    private static final Integer UPDATED_MODIFICATEUR_SAGESSE = 2;

    private static final Integer DEFAULT_MODIFICATEUR_CHARISME = 1;
    private static final Integer UPDATED_MODIFICATEUR_CHARISME = 2;

    private static final Integer DEFAULT_NIVEAU = 1;
    private static final Integer UPDATED_NIVEAU = 2;

    private static final Integer DEFAULT_DE_DE_VIE = 1;
    private static final Integer UPDATED_DE_DE_VIE = 2;

    @Autowired
    private PersonnageRepository personnageRepository;

    /**
     * This repository is mocked in the com.mycompany.myapp.repository.search test package.
     *
     * @see com.mycompany.myapp.repository.search.PersonnageSearchRepositoryMockConfiguration
     */
    @Autowired
    private PersonnageSearchRepository mockPersonnageSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonnageMockMvc;

    private Personnage personnage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personnage createEntity(EntityManager em) {
        Personnage personnage = new Personnage()
            .nom(DEFAULT_NOM)
            .classeArmure(DEFAULT_CLASSE_ARMURE)
            .bonusMaitrise(DEFAULT_BONUS_MAITRISE)
            .force(DEFAULT_FORCE)
            .dexterite(DEFAULT_DEXTERITE)
            .constitution(DEFAULT_CONSTITUTION)
            .intelligence(DEFAULT_INTELLIGENCE)
            .sagesse(DEFAULT_SAGESSE)
            .charisme(DEFAULT_CHARISME)
            .vie(DEFAULT_VIE)
            .perceptionPassive(DEFAULT_PERCEPTION_PASSIVE)
            .initiative(DEFAULT_INITIATIVE)
            .bonusAttaqueCAC(DEFAULT_BONUS_ATTAQUE_CAC)
            .bonusAttaqueDistance(DEFAULT_BONUS_ATTAQUE_DISTANCE)
            .modificateurForce(DEFAULT_MODIFICATEUR_FORCE)
            .modificateurDexterite(DEFAULT_MODIFICATEUR_DEXTERITE)
            .modificateurConstitution(DEFAULT_MODIFICATEUR_CONSTITUTION)
            .modificateurIntelligence(DEFAULT_MODIFICATEUR_INTELLIGENCE)
            .modificateurSagesse(DEFAULT_MODIFICATEUR_SAGESSE)
            .modificateurCharisme(DEFAULT_MODIFICATEUR_CHARISME)
            .niveau(DEFAULT_NIVEAU)
            .deDeVie(DEFAULT_DE_DE_VIE);
        return personnage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personnage createUpdatedEntity(EntityManager em) {
        Personnage personnage = new Personnage()
            .nom(UPDATED_NOM)
            .classeArmure(UPDATED_CLASSE_ARMURE)
            .bonusMaitrise(UPDATED_BONUS_MAITRISE)
            .force(UPDATED_FORCE)
            .dexterite(UPDATED_DEXTERITE)
            .constitution(UPDATED_CONSTITUTION)
            .intelligence(UPDATED_INTELLIGENCE)
            .sagesse(UPDATED_SAGESSE)
            .charisme(UPDATED_CHARISME)
            .vie(UPDATED_VIE)
            .perceptionPassive(UPDATED_PERCEPTION_PASSIVE)
            .initiative(UPDATED_INITIATIVE)
            .bonusAttaqueCAC(UPDATED_BONUS_ATTAQUE_CAC)
            .bonusAttaqueDistance(UPDATED_BONUS_ATTAQUE_DISTANCE)
            .modificateurForce(UPDATED_MODIFICATEUR_FORCE)
            .modificateurDexterite(UPDATED_MODIFICATEUR_DEXTERITE)
            .modificateurConstitution(UPDATED_MODIFICATEUR_CONSTITUTION)
            .modificateurIntelligence(UPDATED_MODIFICATEUR_INTELLIGENCE)
            .modificateurSagesse(UPDATED_MODIFICATEUR_SAGESSE)
            .modificateurCharisme(UPDATED_MODIFICATEUR_CHARISME)
            .niveau(UPDATED_NIVEAU)
            .deDeVie(UPDATED_DE_DE_VIE);
        return personnage;
    }

    @BeforeEach
    public void initTest() {
        personnage = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonnage() throws Exception {
        int databaseSizeBeforeCreate = personnageRepository.findAll().size();
        // Create the Personnage
        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isCreated());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeCreate + 1);
        Personnage testPersonnage = personnageList.get(personnageList.size() - 1);
        assertThat(testPersonnage.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPersonnage.getClasseArmure()).isEqualTo(DEFAULT_CLASSE_ARMURE);
        assertThat(testPersonnage.getBonusMaitrise()).isEqualTo(DEFAULT_BONUS_MAITRISE);
        assertThat(testPersonnage.getForce()).isEqualTo(DEFAULT_FORCE);
        assertThat(testPersonnage.getDexterite()).isEqualTo(DEFAULT_DEXTERITE);
        assertThat(testPersonnage.getConstitution()).isEqualTo(DEFAULT_CONSTITUTION);
        assertThat(testPersonnage.getIntelligence()).isEqualTo(DEFAULT_INTELLIGENCE);
        assertThat(testPersonnage.getSagesse()).isEqualTo(DEFAULT_SAGESSE);
        assertThat(testPersonnage.getCharisme()).isEqualTo(DEFAULT_CHARISME);
        assertThat(testPersonnage.getVie()).isEqualTo(DEFAULT_VIE);
        assertThat(testPersonnage.getPerceptionPassive()).isEqualTo(DEFAULT_PERCEPTION_PASSIVE);
        assertThat(testPersonnage.getInitiative()).isEqualTo(DEFAULT_INITIATIVE);
        assertThat(testPersonnage.getBonusAttaqueCAC()).isEqualTo(DEFAULT_BONUS_ATTAQUE_CAC);
        assertThat(testPersonnage.getBonusAttaqueDistance()).isEqualTo(DEFAULT_BONUS_ATTAQUE_DISTANCE);
        assertThat(testPersonnage.getModificateurForce()).isEqualTo(DEFAULT_MODIFICATEUR_FORCE);
        assertThat(testPersonnage.getModificateurDexterite()).isEqualTo(DEFAULT_MODIFICATEUR_DEXTERITE);
        assertThat(testPersonnage.getModificateurConstitution()).isEqualTo(DEFAULT_MODIFICATEUR_CONSTITUTION);
        assertThat(testPersonnage.getModificateurIntelligence()).isEqualTo(DEFAULT_MODIFICATEUR_INTELLIGENCE);
        assertThat(testPersonnage.getModificateurSagesse()).isEqualTo(DEFAULT_MODIFICATEUR_SAGESSE);
        assertThat(testPersonnage.getModificateurCharisme()).isEqualTo(DEFAULT_MODIFICATEUR_CHARISME);
        assertThat(testPersonnage.getNiveau()).isEqualTo(DEFAULT_NIVEAU);
        assertThat(testPersonnage.getDeDeVie()).isEqualTo(DEFAULT_DE_DE_VIE);

        // Validate the Personnage in Elasticsearch
        verify(mockPersonnageSearchRepository, times(1)).save(testPersonnage);
    }

    @Test
    @Transactional
    public void createPersonnageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personnageRepository.findAll().size();

        // Create the Personnage with an existing ID
        personnage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeCreate);

        // Validate the Personnage in Elasticsearch
        verify(mockPersonnageSearchRepository, times(0)).save(personnage);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setNom(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClasseArmureIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setClasseArmure(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBonusMaitriseIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setBonusMaitrise(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkForceIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setForce(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDexteriteIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setDexterite(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConstitutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setConstitution(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntelligenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setIntelligence(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSagesseIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setSagesse(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCharismeIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setCharisme(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNiveauIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setNiveau(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeDeVieIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnageRepository.findAll().size();
        // set the field null
        personnage.setDeDeVie(null);

        // Create the Personnage, which fails.


        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonnages() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        // Get all the personnageList
        restPersonnageMockMvc.perform(get("/api/personnages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personnage.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].classeArmure").value(hasItem(DEFAULT_CLASSE_ARMURE)))
            .andExpect(jsonPath("$.[*].bonusMaitrise").value(hasItem(DEFAULT_BONUS_MAITRISE)))
            .andExpect(jsonPath("$.[*].force").value(hasItem(DEFAULT_FORCE)))
            .andExpect(jsonPath("$.[*].dexterite").value(hasItem(DEFAULT_DEXTERITE)))
            .andExpect(jsonPath("$.[*].constitution").value(hasItem(DEFAULT_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].intelligence").value(hasItem(DEFAULT_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].sagesse").value(hasItem(DEFAULT_SAGESSE)))
            .andExpect(jsonPath("$.[*].charisme").value(hasItem(DEFAULT_CHARISME)))
            .andExpect(jsonPath("$.[*].vie").value(hasItem(DEFAULT_VIE)))
            .andExpect(jsonPath("$.[*].perceptionPassive").value(hasItem(DEFAULT_PERCEPTION_PASSIVE)))
            .andExpect(jsonPath("$.[*].initiative").value(hasItem(DEFAULT_INITIATIVE)))
            .andExpect(jsonPath("$.[*].bonusAttaqueCAC").value(hasItem(DEFAULT_BONUS_ATTAQUE_CAC)))
            .andExpect(jsonPath("$.[*].bonusAttaqueDistance").value(hasItem(DEFAULT_BONUS_ATTAQUE_DISTANCE)))
            .andExpect(jsonPath("$.[*].modificateurForce").value(hasItem(DEFAULT_MODIFICATEUR_FORCE)))
            .andExpect(jsonPath("$.[*].modificateurDexterite").value(hasItem(DEFAULT_MODIFICATEUR_DEXTERITE)))
            .andExpect(jsonPath("$.[*].modificateurConstitution").value(hasItem(DEFAULT_MODIFICATEUR_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].modificateurIntelligence").value(hasItem(DEFAULT_MODIFICATEUR_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].modificateurSagesse").value(hasItem(DEFAULT_MODIFICATEUR_SAGESSE)))
            .andExpect(jsonPath("$.[*].modificateurCharisme").value(hasItem(DEFAULT_MODIFICATEUR_CHARISME)))
            .andExpect(jsonPath("$.[*].niveau").value(hasItem(DEFAULT_NIVEAU)))
            .andExpect(jsonPath("$.[*].deDeVie").value(hasItem(DEFAULT_DE_DE_VIE)));
    }
    
    @Test
    @Transactional
    public void getPersonnage() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        // Get the personnage
        restPersonnageMockMvc.perform(get("/api/personnages/{id}", personnage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personnage.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.classeArmure").value(DEFAULT_CLASSE_ARMURE))
            .andExpect(jsonPath("$.bonusMaitrise").value(DEFAULT_BONUS_MAITRISE))
            .andExpect(jsonPath("$.force").value(DEFAULT_FORCE))
            .andExpect(jsonPath("$.dexterite").value(DEFAULT_DEXTERITE))
            .andExpect(jsonPath("$.constitution").value(DEFAULT_CONSTITUTION))
            .andExpect(jsonPath("$.intelligence").value(DEFAULT_INTELLIGENCE))
            .andExpect(jsonPath("$.sagesse").value(DEFAULT_SAGESSE))
            .andExpect(jsonPath("$.charisme").value(DEFAULT_CHARISME))
            .andExpect(jsonPath("$.vie").value(DEFAULT_VIE))
            .andExpect(jsonPath("$.perceptionPassive").value(DEFAULT_PERCEPTION_PASSIVE))
            .andExpect(jsonPath("$.initiative").value(DEFAULT_INITIATIVE))
            .andExpect(jsonPath("$.bonusAttaqueCAC").value(DEFAULT_BONUS_ATTAQUE_CAC))
            .andExpect(jsonPath("$.bonusAttaqueDistance").value(DEFAULT_BONUS_ATTAQUE_DISTANCE))
            .andExpect(jsonPath("$.modificateurForce").value(DEFAULT_MODIFICATEUR_FORCE))
            .andExpect(jsonPath("$.modificateurDexterite").value(DEFAULT_MODIFICATEUR_DEXTERITE))
            .andExpect(jsonPath("$.modificateurConstitution").value(DEFAULT_MODIFICATEUR_CONSTITUTION))
            .andExpect(jsonPath("$.modificateurIntelligence").value(DEFAULT_MODIFICATEUR_INTELLIGENCE))
            .andExpect(jsonPath("$.modificateurSagesse").value(DEFAULT_MODIFICATEUR_SAGESSE))
            .andExpect(jsonPath("$.modificateurCharisme").value(DEFAULT_MODIFICATEUR_CHARISME))
            .andExpect(jsonPath("$.niveau").value(DEFAULT_NIVEAU))
            .andExpect(jsonPath("$.deDeVie").value(DEFAULT_DE_DE_VIE));
    }
    @Test
    @Transactional
    public void getNonExistingPersonnage() throws Exception {
        // Get the personnage
        restPersonnageMockMvc.perform(get("/api/personnages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonnage() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        int databaseSizeBeforeUpdate = personnageRepository.findAll().size();

        // Update the personnage
        Personnage updatedPersonnage = personnageRepository.findById(personnage.getId()).get();
        // Disconnect from session so that the updates on updatedPersonnage are not directly saved in db
        em.detach(updatedPersonnage);
        updatedPersonnage
            .nom(UPDATED_NOM)
            .classeArmure(UPDATED_CLASSE_ARMURE)
            .bonusMaitrise(UPDATED_BONUS_MAITRISE)
            .force(UPDATED_FORCE)
            .dexterite(UPDATED_DEXTERITE)
            .constitution(UPDATED_CONSTITUTION)
            .intelligence(UPDATED_INTELLIGENCE)
            .sagesse(UPDATED_SAGESSE)
            .charisme(UPDATED_CHARISME)
            .vie(UPDATED_VIE)
            .perceptionPassive(UPDATED_PERCEPTION_PASSIVE)
            .initiative(UPDATED_INITIATIVE)
            .bonusAttaqueCAC(UPDATED_BONUS_ATTAQUE_CAC)
            .bonusAttaqueDistance(UPDATED_BONUS_ATTAQUE_DISTANCE)
            .modificateurForce(UPDATED_MODIFICATEUR_FORCE)
            .modificateurDexterite(UPDATED_MODIFICATEUR_DEXTERITE)
            .modificateurConstitution(UPDATED_MODIFICATEUR_CONSTITUTION)
            .modificateurIntelligence(UPDATED_MODIFICATEUR_INTELLIGENCE)
            .modificateurSagesse(UPDATED_MODIFICATEUR_SAGESSE)
            .modificateurCharisme(UPDATED_MODIFICATEUR_CHARISME)
            .niveau(UPDATED_NIVEAU)
            .deDeVie(UPDATED_DE_DE_VIE);

        restPersonnageMockMvc.perform(put("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonnage)))
            .andExpect(status().isOk());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeUpdate);
        Personnage testPersonnage = personnageList.get(personnageList.size() - 1);
        assertThat(testPersonnage.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPersonnage.getClasseArmure()).isEqualTo(UPDATED_CLASSE_ARMURE);
        assertThat(testPersonnage.getBonusMaitrise()).isEqualTo(UPDATED_BONUS_MAITRISE);
        assertThat(testPersonnage.getForce()).isEqualTo(UPDATED_FORCE);
        assertThat(testPersonnage.getDexterite()).isEqualTo(UPDATED_DEXTERITE);
        assertThat(testPersonnage.getConstitution()).isEqualTo(UPDATED_CONSTITUTION);
        assertThat(testPersonnage.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testPersonnage.getSagesse()).isEqualTo(UPDATED_SAGESSE);
        assertThat(testPersonnage.getCharisme()).isEqualTo(UPDATED_CHARISME);
        assertThat(testPersonnage.getVie()).isEqualTo(UPDATED_VIE);
        assertThat(testPersonnage.getPerceptionPassive()).isEqualTo(UPDATED_PERCEPTION_PASSIVE);
        assertThat(testPersonnage.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testPersonnage.getBonusAttaqueCAC()).isEqualTo(UPDATED_BONUS_ATTAQUE_CAC);
        assertThat(testPersonnage.getBonusAttaqueDistance()).isEqualTo(UPDATED_BONUS_ATTAQUE_DISTANCE);
        assertThat(testPersonnage.getModificateurForce()).isEqualTo(UPDATED_MODIFICATEUR_FORCE);
        assertThat(testPersonnage.getModificateurDexterite()).isEqualTo(UPDATED_MODIFICATEUR_DEXTERITE);
        assertThat(testPersonnage.getModificateurConstitution()).isEqualTo(UPDATED_MODIFICATEUR_CONSTITUTION);
        assertThat(testPersonnage.getModificateurIntelligence()).isEqualTo(UPDATED_MODIFICATEUR_INTELLIGENCE);
        assertThat(testPersonnage.getModificateurSagesse()).isEqualTo(UPDATED_MODIFICATEUR_SAGESSE);
        assertThat(testPersonnage.getModificateurCharisme()).isEqualTo(UPDATED_MODIFICATEUR_CHARISME);
        assertThat(testPersonnage.getNiveau()).isEqualTo(UPDATED_NIVEAU);
        assertThat(testPersonnage.getDeDeVie()).isEqualTo(UPDATED_DE_DE_VIE);

        // Validate the Personnage in Elasticsearch
        verify(mockPersonnageSearchRepository, times(1)).save(testPersonnage);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonnage() throws Exception {
        int databaseSizeBeforeUpdate = personnageRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonnageMockMvc.perform(put("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Personnage in Elasticsearch
        verify(mockPersonnageSearchRepository, times(0)).save(personnage);
    }

    @Test
    @Transactional
    public void deletePersonnage() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        int databaseSizeBeforeDelete = personnageRepository.findAll().size();

        // Delete the personnage
        restPersonnageMockMvc.perform(delete("/api/personnages/{id}", personnage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Personnage in Elasticsearch
        verify(mockPersonnageSearchRepository, times(1)).deleteById(personnage.getId());
    }

    @Test
    @Transactional
    public void searchPersonnage() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);
        when(mockPersonnageSearchRepository.search(queryStringQuery("id:" + personnage.getId())))
            .thenReturn(Collections.singletonList(personnage));

        // Search the personnage
        restPersonnageMockMvc.perform(get("/api/_search/personnages?query=id:" + personnage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personnage.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].classeArmure").value(hasItem(DEFAULT_CLASSE_ARMURE)))
            .andExpect(jsonPath("$.[*].bonusMaitrise").value(hasItem(DEFAULT_BONUS_MAITRISE)))
            .andExpect(jsonPath("$.[*].force").value(hasItem(DEFAULT_FORCE)))
            .andExpect(jsonPath("$.[*].dexterite").value(hasItem(DEFAULT_DEXTERITE)))
            .andExpect(jsonPath("$.[*].constitution").value(hasItem(DEFAULT_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].intelligence").value(hasItem(DEFAULT_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].sagesse").value(hasItem(DEFAULT_SAGESSE)))
            .andExpect(jsonPath("$.[*].charisme").value(hasItem(DEFAULT_CHARISME)))
            .andExpect(jsonPath("$.[*].vie").value(hasItem(DEFAULT_VIE)))
            .andExpect(jsonPath("$.[*].perceptionPassive").value(hasItem(DEFAULT_PERCEPTION_PASSIVE)))
            .andExpect(jsonPath("$.[*].initiative").value(hasItem(DEFAULT_INITIATIVE)))
            .andExpect(jsonPath("$.[*].bonusAttaqueCAC").value(hasItem(DEFAULT_BONUS_ATTAQUE_CAC)))
            .andExpect(jsonPath("$.[*].bonusAttaqueDistance").value(hasItem(DEFAULT_BONUS_ATTAQUE_DISTANCE)))
            .andExpect(jsonPath("$.[*].modificateurForce").value(hasItem(DEFAULT_MODIFICATEUR_FORCE)))
            .andExpect(jsonPath("$.[*].modificateurDexterite").value(hasItem(DEFAULT_MODIFICATEUR_DEXTERITE)))
            .andExpect(jsonPath("$.[*].modificateurConstitution").value(hasItem(DEFAULT_MODIFICATEUR_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].modificateurIntelligence").value(hasItem(DEFAULT_MODIFICATEUR_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].modificateurSagesse").value(hasItem(DEFAULT_MODIFICATEUR_SAGESSE)))
            .andExpect(jsonPath("$.[*].modificateurCharisme").value(hasItem(DEFAULT_MODIFICATEUR_CHARISME)))
            .andExpect(jsonPath("$.[*].niveau").value(hasItem(DEFAULT_NIVEAU)))
            .andExpect(jsonPath("$.[*].deDeVie").value(hasItem(DEFAULT_DE_DE_VIE)));
    }
}
