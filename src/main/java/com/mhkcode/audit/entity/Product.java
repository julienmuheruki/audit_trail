package com.mhkcode.audit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Audited
@EntityListeners(EntityListeners.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ProductId;
    @Audited
    private String ProductName;
    @Audited
    private Double productPrice;
    @Audited
    private String description;
    @Audited
    private Date fabricationDate;
    @Audited
    private Date expirationDate;
    @Audited
    private String producedBy;
}
