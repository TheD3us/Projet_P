package com.mycompany.myapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A TableModificateur.
 */
@Entity
@Table(name = "table_modificateur")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tablemodificateur")
public class TableModificateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 30)
    @Column(name = "valeur", nullable = false, unique = true)
    private Integer valeur;

    @NotNull
    @Min(value = -5)
    @Max(value = 10)
    @Column(name = "modificateur", nullable = false, unique = true)
    private Integer modificateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValeur() {
        return valeur;
    }

    public TableModificateur valeur(Integer valeur) {
        this.valeur = valeur;
        return this;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public Integer getModificateur() {
        return modificateur;
    }

    public TableModificateur modificateur(Integer modificateur) {
        this.modificateur = modificateur;
        return this;
    }

    public void setModificateur(Integer modificateur) {
        this.modificateur = modificateur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableModificateur)) {
            return false;
        }
        return id != null && id.equals(((TableModificateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TableModificateur{" +
            "id=" + getId() +
            ", valeur=" + getValeur() +
            ", modificateur=" + getModificateur() +
            "}";
    }
}
