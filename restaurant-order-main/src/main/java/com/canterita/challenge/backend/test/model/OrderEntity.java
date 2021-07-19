package com.canterita.challenge.backend.test.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.canterita.challenge.backend.test.dto.OrderDetailDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@Entity(name = "ORDER_ENTITY")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "client")
	private String client;

	@Column(name = "total")
	private Double total;
	
	@Column(name = "date_order")
	private LocalDateTime dateOrder;
	
	@JsonIgnoreProperties("dborden")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dborden", orphanRemoval = true)
	private List<OrderDetailEntity> dbdetail;
	
	public OrderEntity (Long id, String number, String client, Double total , LocalDateTime dataOrder) {
		this.id= id;
		this.number=number;
		this.client=client;
		this.total=total;
		this.dateOrder=dataOrder;
	}
	public OrderEntity (Long id, String number, String client, Double total , LocalDateTime dataOrder ,List<OrderDetailEntity> dbdetail ) {
		this.id= id;
		this.number=number;
		this.client=client;
		this.total=total;
		this.dateOrder=dataOrder;
		this.dbdetail=dbdetail;
	}
	
	public OrderEntity() {
		
	}
	

}
