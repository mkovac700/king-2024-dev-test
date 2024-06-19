package org.king.test.mkovac;

import java.util.List;

public interface IProductService {
  List<Product> getAllProducts();

  Product getProductById(int id);
}
