package com.app.controller;

import com.app.entity.Product;
import com.app.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private Repository repo;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getAProducts(@PathVariable Integer productId) {
        return ResponseEntity.ok(repo.findById(productId));
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveNewProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(repo.save(product), HttpStatus.CREATED);
        } catch(RuntimeException e) {
            return new ResponseEntity<>("Request cannot be processed!!!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
        try {
            if(productId == product.getProductId())
                return new ResponseEntity<>(repo.save(product), HttpStatus.OK);
            else
                return new ResponseEntity<>("Product updation failed!!!", HttpStatus.NOT_MODIFIED);
        } catch(RuntimeException e) {
            return new ResponseEntity<>("Request cannot be processed!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId, @RequestBody Product product) {
        try {
            if(productId == product.getProductId()) {
                repo.delete(product);
                return ResponseEntity.ok("Product Deleted.");
            }
            else
                return new ResponseEntity<>("Product deletion failed!!!", HttpStatus.NOT_MODIFIED);
        } catch(RuntimeException e) {
            System.out.println(e);
            return new ResponseEntity<>("Request cannot be processed!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
