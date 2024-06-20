package org.king.test.mkovac;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements IProductService {

  private String uri = "https://dummyjson.com/products/";

  @Override
  public List<Product> getAllProducts() {

    String uri = this.uri;
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<ProductResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null,
        new ParameterizedTypeReference<ProductResponse>() {});

    List<Product> products = response.getBody().getProducts();

    for (Product p : products)
      p.setDescription(p.getDescription().substring(0, Math.min(p.getDescription().length(), 100)));

    return products;
  }

  @Override
  public Product getProductById(int id) {
    String uri = this.uri + id;
    RestTemplate restTemplate = new RestTemplate();
    Product product = restTemplate.getForObject(uri, Product.class);
    product.setDescription(
        product.getDescription().substring(0, Math.min(product.getDescription().length(), 100)));
    return product;
  }

  @Override
  @Cacheable(value = "filterCache", key = "#category + '-' + #price")
  public List<Product> getProductsByFilter(String category, Float price) {
    String uri = this.uri;
    RestTemplate restTemplate = new RestTemplate();

    if (category != null)
      uri += "/category/" + category;

    ResponseEntity<ProductResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null,
        new ParameterizedTypeReference<ProductResponse>() {});

    List<Product> products = response.getBody().getProducts();

    for (Product p : products)
      p.setDescription(p.getDescription().substring(0, Math.min(p.getDescription().length(), 100)));

    if (price != null) {
      products = products.stream().filter(p -> p.getPrice() == price).collect(Collectors.toList());
    }

    return products;
  }

  @Override
  @Cacheable(value = "searchCache", key = "#name")
  public List<Product> getProductsByName(String name) {
    String uri = this.uri;
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<ProductResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null,
        new ParameterizedTypeReference<ProductResponse>() {});

    List<Product> products = response.getBody().getProducts();

    for (Product p : products)
      p.setDescription(p.getDescription().substring(0, Math.min(p.getDescription().length(), 100)));

    if (name != null) {
      products =
          products.stream().filter(p -> p.getTitle().toLowerCase().contains(name.toLowerCase()))
              .collect(Collectors.toList());
    }

    return products;
  }
}
