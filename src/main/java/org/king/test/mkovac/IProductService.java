package org.king.test.mkovac;

import java.util.List;

public interface IProductService {
  List<Product> getAllProducts();

  Product getProductById(int id);

  List<Product> getProductsByFilter(String category, Float price);

  List<Product> getProductsByName(String name);
}
