package com.mhkcode.audit.dto;

import com.mhkcode.audit.entity.Product;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class ProductRegistrationReq {
    private String ProductName;
    private Double productPrice;
    private String description;
    private Date fabricationDate;
    private Date expirationDate;
    private String producedBy;

    // Convert Entity to DTO
    public static ProductRegistrationReq convertToDTO(Product product) {
        ProductRegistrationReq dto = new ProductRegistrationReq();
        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());
        dto.setDescription(product.getDescription());
        dto.setFabricationDate(product.getFabricationDate());
        dto.setExpirationDate(product.getExpirationDate());
        dto.setProducedBy(product.getProducedBy());
        return dto;
    }
}
