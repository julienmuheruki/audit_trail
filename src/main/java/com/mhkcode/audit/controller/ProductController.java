package com.mhkcode.audit.controller;

import com.mhkcode.audit.dto.ProductRegistrationReq;
import com.mhkcode.audit.dto.ProductResponse;
import com.mhkcode.audit.entity.Product;
import com.mhkcode.audit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRegistrationReq req) {
        ProductResponse response = productService.createProduct(req);

        // Parse the HTTP status from the response
        HttpStatus httpStatus;
        if (response.getHttpResponse().startsWith("201")) {
            httpStatus = HttpStatus.CREATED;
        } else if (response.getHttpResponse().startsWith("500")) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST; // Default case
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID id,
            @RequestBody ProductRegistrationReq req) {
        Product updatedProduct = productService.updateProduct(id, req);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
