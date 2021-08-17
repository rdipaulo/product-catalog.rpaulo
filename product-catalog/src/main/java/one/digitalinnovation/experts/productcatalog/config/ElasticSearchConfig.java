package one.digitalinnovation.experts.productcatalog.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import java.lang.Override;


@Configuration
@EnableElasticsearchRepositories(basePackages = "one.digitainnovation.experts.productcatalog.repository")
public class ElasticSearchConfig extends ElasticsearchConfigurationSupport {

    @Override
    public RestHighLevelClient elasticsearchClient(){

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200", "localhost:9300")
                .build();
        return RestClients.create(clientConfiguration).rest();

    }

    @Bean
    @Override
    public EntityMapper entityMapper(){

        ElasticsearchEntityMapper entityMapper =new ElasticsearchEntityMapper(elastsearchMappingContext(),
                new DefaultConversionService());
        entityMapper.setConversions(elasticsearchCustomConversions());


    }
}
