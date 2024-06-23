package org.king.test.mkovac.service;

import java.util.List;
import org.king.test.mkovac.entity.Product;

public interface ProductService {
  List<Product> getAllProducts(Integer page, Integer size);

  Product getProductById(int id);

  List<Product> getProductsByFilter(String category, Float priceMin, Float priceMax);

  List<Product> getProductsByName(String name);
}
