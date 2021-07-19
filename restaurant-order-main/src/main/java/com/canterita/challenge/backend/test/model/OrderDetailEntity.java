package com.canterita.challenge.backend.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.canterita.challenge.backend.test.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity(name = "ORDER_DETAILS_ENTITY")
public class OrderDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "detail")
	private String detail;

	@Column(name = "cantidad")
	private Double cantidad;

	@Column(name = "preciounitario")
	private Double precioUnitario;

	@Column(name = "totaldetail")
	private Double totalDetail;
	
	@ManyToOne
	@JsonIgnoreProperties("dbdetail")
	@JoinColumn(referencedColumnName = "id")
	private OrderEntity dborden;

	@PrePersist
	public void prePersist() {
		this.totalDetail = this.cantidad * this.getPrecioUnitario();
	}

	public OrderDetailEntity() {

	}

	public OrderDetailEntity(Long id, String detail, Double cantidad, Double precioUnitario, Double totalDetail, OrderEntity orden) {
		this.id=id;
		
		this.detail=detail;
		this.cantidad= cantidad;
		this.precioUnitario=precioUnitario;
		this.totalDetail=totalDetail;
		this.dborden=orden;

	}

}
