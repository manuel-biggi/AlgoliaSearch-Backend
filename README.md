This SpringBoot application will use Algolia's official API client (Java API Client v3) to push the records from a Json file [resources/products.json] to Algolia.

For this to work you will need the following parameters:

spring.application.name=AlgoliaSearch 
algolia.app-id=XXXXXX 
algolia.api-key=XXXXXX 
algolia.index-name=products

Note that the current recommended version is Java API Client v4 .
