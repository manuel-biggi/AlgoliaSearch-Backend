package com.webclient.algoliasearch.service;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.settings.IndexSettings;
import com.webclient.algoliasearch.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class AlgoliaService {

    // The credentials are not stored in the code, this is coming from the application.properties
    @Value("${algolia.app-id}")
    private String ALGOLIA_SEARCH_APP_ID;

    @Value("${algolia.api-key}")
    private String ALGOLIA_API_KEY;

    @Value("${algolia.index-name}")
    private String ALGOLIA_INDEX_NAME;

    @PostConstruct
    public void runAlgoliaIndexing() {
        try {
            // Reading the products.json from resources
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStreamObject  = getClass().getResourceAsStream("/products.json");
            List<Product> products = mapper.readValue(inputStreamObject , new TypeReference<>() {});

            // Conectando a Algolia
            SearchClient client = DefaultSearchClient.create(ALGOLIA_SEARCH_APP_ID, ALGOLIA_API_KEY);
            SearchIndex<Product> indexName  = client.initIndex(ALGOLIA_INDEX_NAME, Product.class);

            //Reading what I am pushing to Algolia, iterating now over the Product Pojo
            products.forEach(product -> {
                try {
                    String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);
                    // Displaying in the Console the Json file provided in /resources/products.json
                    System.out.println(jsonOutput);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // This piece of code pushes the records to Algolia
            indexName.saveObjects(products).waitTask();


            if (inputStreamObject  == null) {
                throw new IllegalStateException("Could not find products.json in classpath.");
            }

            // This is what it should apply the index relevancy settings.
            // According to this page => https://www.algolia.com/doc/guides/managing-results/relevance-overview/
            // https://www.algolia.com/doc/guides/managing-results/must-do/searchable-attributes/how-to/configuring-searchable-attributes-the-right-way/
            // https://www.algolia.com/doc/api-reference/api-parameters/searchableAttributes/#examples
            IndexSettings settings = new IndexSettings()
                    .setSearchableAttributes(List.of(
                            "unordered(name)",
                            "unordered(brand)",
                            "unordered(categories)",
                            "description"
                    ))
                    .setCustomRanking(List.of(
                            "desc(popularity)",
                            "desc(rating)",
                            "asc(price)"
                    ))
                    .setAttributesForFaceting(List.of(
                            "searchable(brand)",
                            "searchable(categories)",
                            "price_range",
                            "free_shipping"
                    ));

            indexName.setSettings(settings).waitTask();


            String outPut = System.lineSeparator();
            System.out.println("- All products have been indexed." + outPut + "- The relevancy has been configured.");
            System.out.println(outPut);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}