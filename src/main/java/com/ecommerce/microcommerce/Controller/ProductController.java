package com.ecommerce.microcommerce.Controller;



import com.ecommerce.microcommerce.DAO.ProductDao;
import com.ecommerce.microcommerce.Exceptions.ProduitIntrouvableException;
import com.ecommerce.microcommerce.Model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController

public class ProductController {

  @Autowired

private ProductDao productDao;

  //Récupérer la liste des produits
  @RequestMapping(value = "/Produits", method = RequestMethod.GET)

  public List<Product> listeProduits() {
	  List<Product>  productList = productDao.findAll();
return productList;
  }

  //Récupérer un produit par son Id
 @GetMapping(value = "/Produits/{id}")
  public Product afficherUnProduit(@PathVariable int id) {
    
    Product produit = productDao.findById(id);
    if(produit==null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE. Écran Bleu si je pouvais.");
 return produit;

  }

  //ajouter un produit
  @PostMapping(value = "/Produits")
  public  void ajouterProduit(@Valid @RequestBody Product product) {
    Product productAdded =  productDao.save(product);

    if (productAdded == null)
      throw new ProduitIntrouvableException("produit introuvable ");

    else {
    	productDao.save(product);
    }
  };
  
  @GetMapping("test/produits/{prixLimit}")
  public List<Product> getProductGreatherThan(@PathVariable int prixLimit){
	  return productDao.findByPrixLessThan(prixLimit);
  };
  
  @DeleteMapping (value = "/Produits/{id}")
  public void supprimerProduit(@PathVariable int id) {
     productDao.deleteById(id);
  };
  @PutMapping (value = "/Produits")
  public void updateProduit(@RequestBody Product product)
  {
     productDao.save(product);
  }
  
}