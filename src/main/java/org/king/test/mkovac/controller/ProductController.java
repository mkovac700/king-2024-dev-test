package org.king.test.mkovac.controller;

import java.util.List;
import org.king.test.mkovac.entity.Product;
import org.king.test.mkovac.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable int id) {
    return productService.getProductById(id);
  }

  @GetMapping("/filter")
  public List<Product> getProductsByFilter(@RequestParam(required = false) String category,
      @RequestParam(required = false) Float price) {
    logger.info("Category: " + category);
    logger.info("Price: " + price);

    return productService.getProductsByFilter(category, price);
  }

  @GetMapping("/search")
  public List<Product> getProductsByName(@RequestParam(required = true) String name) {
    logger.info("Name: " + name);

    return productService.getProductsByName(name);
  }
}
