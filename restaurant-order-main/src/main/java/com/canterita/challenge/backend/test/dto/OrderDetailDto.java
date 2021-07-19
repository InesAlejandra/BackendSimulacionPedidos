package com.canterita.challenge.backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDto {

	private Long id;

	private Long idorder;

	private String detail;

	private Double cantidad;

	private Double precioUnitario;

	private Double totalDetail;

	public OrderDetailDto() {}

}
