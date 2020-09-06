package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Images;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Images} entity.
 */
public interface ImagesSearchRepository extends ElasticsearchRepository<Images, Long> {
}
