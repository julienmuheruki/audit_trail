package com.mhkcode.audit.serviceImpl;

import com.mhkcode.audit.dto.ProductRegistrationReq;
import com.mhkcode.audit.dto.ProductResponse;
import com.mhkcode.audit.entity.Product;
import com.mhkcode.audit.repository.ProductRepository;
import com.mhkcode.audit.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRegistrationReq req) {
        Product product = new Product();
        ProductResponse response = new ProductResponse();

        try {
            // Map request to entity
            product.setProductName(req.getProductName());
            product.setProductPrice(req.getProductPrice());
            product.setDescription(req.getDescription());
            product.setFabricationDate(req.getFabricationDate());
            product.setExpirationDate(req.getExpirationDate());
            product.setProducedBy(req.getProducedBy());

            // Save product - no need to check for null as save() never returns null
            Product savedProduct = productRepository.save(product);

            response.setMessage("Product saved successfully with name: " + savedProduct.getProductName());
            response.setHttpResponse("201 Created");

        } catch (Exception e) {
            response.setMessage("Error occurred while saving product: " + e.getMessage());
            response.setHttpResponse("500 Internal Server Error");
        }

        return response;
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(UUID id, ProductRegistrationReq req) {
        Product existingProduct = getProductById(id);

        existingProduct.setProductName(req.getProductName());
        existingProduct.setProductPrice(req.getProductPrice());
        existingProduct.setDescription(req.getDescription());
        existingProduct.setFabricationDate(req.getFabricationDate());
        existingProduct.setExpirationDate(req.getExpirationDate());
        existingProduct.setProducedBy(req.getProducedBy());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}