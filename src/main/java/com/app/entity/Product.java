package com.app.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Document("product")
public class Product {
    @Id
    private Integer productId;
    private String productName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufactureDate;
    private double productCost;
}
