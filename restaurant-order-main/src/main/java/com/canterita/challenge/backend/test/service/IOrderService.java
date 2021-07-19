package com.canterita.challenge.backend.test.service;


import java.util.List;

import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.model.OrderEntity;

public interface IOrderService {
	
	public OrderDto getOrder(Long id);
	
	public List<OrderDto> getAll();
	public OrderDto get(Long id);
	public OrderDto post(OrderDto order);
	public OrderDto toOrder(OrderEntity dbOrden);
	public void delete (Long id);
	

}
