package org.king.test.mkovac.repository;

import org.king.test.mkovac.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
