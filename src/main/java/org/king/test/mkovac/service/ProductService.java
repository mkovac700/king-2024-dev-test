package org.king.test.mkovac.service;

import java.util.List;
import org.king.test.mkovac.entity.Product;

public interface ProductService {
  List<Product> getAllProducts();

  Product getProductById(int id);

  List<Product> getProductsByFilter(String category, Float price);

  List<Product> getProductsByName(String name);
}
