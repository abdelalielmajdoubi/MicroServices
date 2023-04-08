package com.ecommerce.microcommerce.DAO;



import com.ecommerce.microcommerce.Model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
  Product findById(int id);
  List<Product> findByPrixLessThan(int prixLimit);
}