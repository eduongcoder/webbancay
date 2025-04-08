package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orderdatas")
public class OrderDatas {
	@Id
	@Column(name = "Id", nullable = false, length = 255)
	private String id;

	@Lob
	@Column(name = "order_code", nullable = false, columnDefinition = "LONGTEXT")
	private String orderCode;

	@Column(name = "total_fee", nullable = false, precision = 65, scale = 30)
	private BigDecimal totalFee;
}
