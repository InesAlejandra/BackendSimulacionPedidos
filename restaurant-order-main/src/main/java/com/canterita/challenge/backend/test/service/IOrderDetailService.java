package com.canterita.challenge.backend.test.service;

import java.util.List;

import com.canterita.challenge.backend.test.dto.OrderDetailDto;
import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.model.OrderDetailEntity;
import com.canterita.challenge.backend.test.model.OrderEntity;

public interface IOrderDetailService {

		
	public List<OrderDetailDto> getAll();
	public OrderDetailDto get(Long id);
	public OrderDetailDto post(OrderDetailDto order);
	public OrderDetailDto toOrderDetail(OrderDetailEntity dbOrdenDetail);
	public void delete (Long id);
	
}
