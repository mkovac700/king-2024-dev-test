package org.king.test.mkovac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.king.test.mkovac.entity.Product;
import org.king.test.mkovac.entity.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {

  @Value("${products.rest.service.uri}")
  private String baseUri;

  @Value("${pagination.default.limit}")
  private int defaultLimit;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<Product> getAllProducts() {
    List<Product> products = fetchAllProducts();

    for (Product p : products)
      p.setDescription(p.getDescription().substring(0, Math.min(p.getDescription().length(), 100)));

    return products;
  }

  @Override
  public Product getProductById(int id) {
    String uri = this.baseUri + id;
    Product product = restTemplate.getForObject(uri, Product.class);
    product.setDescription(
        product.getDescription().substring(0, Math.min(product.getDescription().length(), 100)));
    return product;
  }

  @Override
  @Cacheable(value = "filterCache", key = "#category + '-' + #price")
  public List<Product> getProductsByFilter(String category, Float price) {
    String uri = this.baseUri;

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

    List<Product> products = fetchAllProducts();

    for (Product p : products)
      p.setDescription(p.getDescription().substring(0, Math.min(p.getDescription().length(), 100)));

    if (name != null) {
      products =
          products.stream().filter(p -> p.getTitle().toLowerCase().contains(name.toLowerCase()))
              .collect(Collectors.toList());
    }

    return products;
  }

  private List<Product> fetchAllProducts() {
    int skip = 0;

    List<Product> products = new ArrayList<>();

    while (true) {
      String finalUri = this.baseUri + "?skip=" + skip + "&limit=" + defaultLimit;

      ResponseEntity<ProductResponse> response = restTemplate.exchange(finalUri, HttpMethod.GET,
          null, new ParameterizedTypeReference<ProductResponse>() {});

      List<Product> tmp = response.getBody().getProducts();

      if (tmp.isEmpty() || tmp == null)
        break;

      products.addAll(tmp);

      skip += defaultLimit;

    }

    return products;
  }
}
