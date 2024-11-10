package com.mhkcode.audit.service;

import com.mhkcode.audit.dto.ProductRegistrationReq;
import com.mhkcode.audit.dto.ProductResponse;
import com.mhkcode.audit.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse createProduct(ProductRegistrationReq req);
    Product getProductById(UUID id);
    List<Product> getAllProducts();
    Product updateProduct(UUID id, ProductRegistrationReq req);
    void deleteProduct(UUID id);
}
