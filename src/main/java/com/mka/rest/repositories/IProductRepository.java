package com.mka.rest.repositories;

import com.mka.rest.models.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
