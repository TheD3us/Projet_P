package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Personnage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Personnage} entity.
 */
public interface PersonnageSearchRepository extends ElasticsearchRepository<Personnage, Long> {
}
