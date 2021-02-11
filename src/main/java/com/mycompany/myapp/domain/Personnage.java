package com.mycompany.myapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Personnage.
 */
@Entity
@Table(name = "personnage")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "personnage")
public class Personnage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @NotNull
    @Min(value = 1)
    @Column(name = "vie", nullable = false)
    private Integer vie;

    @NotNull
    @Column(name = "classe_armure", nullable = false)
    private Integer classeArmure;

    @NotNull
    @Column(name = "perception_passive", nullable = false)
    private Integer perceptionPassive;

    @NotNull
    @Column(name = "bonus_maitrise", nullable = false)
    private Integer bonusMaitrise;

    @NotNull
    @Column(name = "initiative", nullable = false)
    private Integer initiative;

    @NotNull
    @Column(name = "bonus_attaque_cac", nullable = false)
    private Integer bonusAttaqueCAC;

    @NotNull
    @Column(name = "bonus_attaque_distance", nullable = false)
    private Integer bonusAttaqueDistance;

    @NotNull
    @Column(name = "force", nullable = false)
    private Integer force;

    @NotNull
    @Column(name = "dexterite", nullable = false)
    private Integer dexterite;

    @NotNull
    @Column(name = "constitution", nullable = false)
    private Integer constitution;

    @NotNull
    @Column(name = "intelligence", nullable = false)
    private Integer intelligence;

    @NotNull
    @Column(name = "sagesse", nullable = false)
    private Integer sagesse;

    @NotNull
    @Column(name = "charisme", nullable = false)
    private Integer charisme;

    @NotNull
    @Column(name = "modificateur_force", nullable = false)
    private Integer modificateurForce;

    @NotNull
    @Column(name = "modificateur_dexterite", nullable = false)
    private Integer modificateurDexterite;

    @NotNull
    @Column(name = "modificateur_constitution", nullable = false)
    private Integer modificateurConstitution;

    @NotNull
    @Column(name = "modificateur_charisme", nullable = false)
    private Integer modificateurCharisme;

    @NotNull
    @Column(name = "modificateur_intelligence", nullable = false)
    private Integer modificateurIntelligence;

    @NotNull
    @Column(name = "modificateur_sagesse", nullable = false)
    private Integer modificateurSagesse;

    @NotNull
    @Column(name = "de_de_vie", nullable = false)
    private Integer deDeVie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Personnage nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getVie() {
        return vie;
    }

    public Personnage vie(Integer vie) {
        this.vie = vie;
        return this;
    }

    public void setVie(Integer vie) {
        this.vie = this.deDeVie + modificateurConstitution;
    }

    public Integer getClasseArmure() {
        return classeArmure;
    }

    public Personnage classeArmure(Integer classeArmure) {
        this.classeArmure = classeArmure;
        return this;
    }

    public void setClasseArmure(Integer classeArmure) {
        this.classeArmure = classeArmure;
    }

    public Integer getPerceptionPassive() {
        return perceptionPassive;
    }

    public Personnage perceptionPassive(Integer perceptionPassive) {
        this.perceptionPassive = perceptionPassive;
        return this;
    }

    public void setPerceptionPassive(Integer perceptionPassive) {
        this.perceptionPassive = 10 + modificateurSagesse;
    }

    public Integer getBonusMaitrise() {
        return bonusMaitrise;
    }

    public Personnage bonusMaitrise(Integer bonusMaitrise) {
        this.bonusMaitrise = bonusMaitrise;
        return this;
    }

    public void setBonusMaitrise(Integer bonusMaitrise) {
        this.bonusMaitrise = bonusMaitrise;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public Personnage initiative(Integer initiative) {
        this.initiative = initiative;
        return this;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = modificateurDexterite;
    }

    public Integer getBonusAttaqueCAC() {
        return bonusAttaqueCAC;
    }

    public Personnage bonusAttaqueCAC(Integer bonusAttaqueCAC) {
        this.bonusAttaqueCAC = bonusAttaqueCAC;
        return this;
    }

    public void setBonusAttaqueCAC(Integer bonusAttaqueCAC) {
        this.bonusAttaqueCAC = bonusMaitrise + modificateurForce;
    }

    public Integer getBonusAttaqueDistance() {
        return bonusAttaqueDistance;
    }

    public Personnage bonusAttaqueDistance(Integer bonusAttaqueDistance) {
        this.bonusAttaqueDistance = bonusAttaqueDistance;
        return this;
    }

    public void setBonusAttaqueDistance(Integer bonusAttaqueDistance) {
        this.bonusAttaqueDistance = bonusMaitrise + modificateurDexterite;
    }

    public Integer getForce() {
        return force;
    }

    public Personnage force(Integer force) {
        this.force = force;
        return this;
    }

    public void setForce(Integer force) {
        this.force = force;
    }

    public Integer getDexterite() {
        return dexterite;
    }

    public Personnage dexterite(Integer dexterite) {
        this.dexterite = dexterite;
        return this;
    }

    public void setDexterite(Integer dexterite) {
        this.dexterite = dexterite;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public Personnage constitution(Integer constitution) {
        this.constitution = constitution;
        return this;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Personnage intelligence(Integer intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getSagesse() {
        return sagesse;
    }

    public Personnage sagesse(Integer sagesse) {
        this.sagesse = sagesse;
        return this;
    }

    public void setSagesse(Integer sagesse) {
        this.sagesse = sagesse;
    }

    public Integer getCharisme() {
        return charisme;
    }

    public Personnage charisme(Integer charisme) {
        this.charisme = charisme;
        return this;
    }

    public void setCharisme(Integer charisme) {
        this.charisme = charisme;
    }

    public Integer getModificateurForce() {
        return modificateurForce;
    }

    public Personnage modificateurForce(Integer modificateurForce) {
        this.modificateurForce = modificateurForce;
        return this;
    }

    public void setModificateurForce(Integer modificateurForce) {
        this.modificateurForce = modificateurForce;
    }

    public Integer getModificateurDexterite() {
        return modificateurDexterite;
    }

    public Personnage modificateurDexterite(Integer modificateurDexterite) {
        this.modificateurDexterite = modificateurDexterite;
        return this;
    }

    public void setModificateurDexterite(Integer modificateurDexterite) {
        this.modificateurDexterite = modificateurDexterite;
    }

    public Integer getModificateurConstitution() {
        return modificateurConstitution;
    }

    public Personnage modificateurConstitution(Integer modificateurConstitution) {
        this.modificateurConstitution = modificateurConstitution;
        return this;
    }

    public void setModificateurConstitution(Integer modificateurConstitution) {
        this.modificateurConstitution = modificateurConstitution;
    }

    public Integer getModificateurCharisme() {
        return modificateurCharisme;
    }

    public Personnage modificateurCharisme(Integer modificateurCharisme) {
        this.modificateurCharisme = modificateurCharisme;
        return this;
    }

    public void setModificateurCharisme(Integer modificateurCharisme) {
        this.modificateurCharisme = modificateurCharisme;
    }

    public Integer getModificateurIntelligence() {
        return modificateurIntelligence;
    }

    public Personnage modificateurIntelligence(Integer modificateurIntelligence) {
        this.modificateurIntelligence = modificateurIntelligence;
        return this;
    }

    public void setModificateurIntelligence(Integer modificateurIntelligence) {
        this.modificateurIntelligence = modificateurIntelligence;
    }

    public Integer getModificateurSagesse() {
        return modificateurSagesse;
    }

    public Personnage modificateurSagesse(Integer modificateurSagesse) {
        this.modificateurSagesse = modificateurSagesse;
        return this;
    }

    public void setModificateurSagesse(Integer modificateurSagesse) {
        this.modificateurSagesse = modificateurSagesse;
    }

    public Integer getDeDeVie() {
        return deDeVie;
    }

    public Personnage deDeVie(Integer deDeVie) {
        this.deDeVie = deDeVie;
        return this;
    }

    public void setDeDeVie(Integer deDeVie) {
        this.deDeVie = deDeVie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personnage)) {
            return false;
        }
        return id != null && id.equals(((Personnage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personnage{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", vie=" + getVie() +
            ", classeArmure=" + getClasseArmure() +
            ", perceptionPassive=" + getPerceptionPassive() +
            ", bonusMaitrise=" + getBonusMaitrise() +
            ", initiative=" + getInitiative() +
            ", bonusAttaqueCAC=" + getBonusAttaqueCAC() +
            ", bonusAttaqueDistance=" + getBonusAttaqueDistance() +
            ", force=" + getForce() +
            ", dexterite=" + getDexterite() +
            ", constitution=" + getConstitution() +
            ", intelligence=" + getIntelligence() +
            ", sagesse=" + getSagesse() +
            ", charisme=" + getCharisme() +
            ", modificateurForce=" + getModificateurForce() +
            ", modificateurDexterite=" + getModificateurDexterite() +
            ", modificateurConstitution=" + getModificateurConstitution() +
            ", modificateurCharisme=" + getModificateurCharisme() +
            ", modificateurIntelligence=" + getModificateurIntelligence() +
            ", modificateurSagesse=" + getModificateurSagesse() +
            ", deDeVie=" + getDeDeVie() +
            "}";
    }
}
