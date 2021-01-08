package com.app.repository;

import com.app.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Product, Integer> {
}
