package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.TableModificateur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TableModificateur} entity.
 */
public interface TableModificateurSearchRepository extends ElasticsearchRepository<TableModificateur, Long> {
}
