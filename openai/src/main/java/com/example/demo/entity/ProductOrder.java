package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product_order")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="order_number")
    private String orderNumber;
    @Column(name="product_name")
    private String productName;
    @Column(name="shipping_address")
    private String shippingAddress;
    @Column(name="shipping_status")
    private String shippingStatus;
    @Column(name="member_name")
    private String memberName;
}
