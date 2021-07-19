package com.canterita.challenge.backend.test.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.canterita.challenge.backend.test.dto.OrderDetailDto;
import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.model.OrderDetailEntity;
import com.canterita.challenge.backend.test.model.OrderEntity;
import com.canterita.challenge.backend.test.repository.OrderRepository;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderDetailsService orderDetailService;
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public OrderDto getOrder(Long id) {
	//	return orderRepository.findById(id).map(order -> new OrderDto(order.getId(), order.getNumber(), order.getClient(), order.getTotal(), order.getDateOrder())).orElse(null);
		return null;
	}

	@Override
	public List<OrderDto> getAll() {
        List<OrderDto> list = new ArrayList<>();
        try {
            List<OrderEntity> list_dbOrderEntity = orderRepository.findAll();
            if (list_dbOrderEntity != null) {
               for (OrderEntity dbOrder : list_dbOrderEntity) {                    
                    list.add(toOrder(dbOrder));
                }
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return list;
	}

	@Override
	public OrderDto get(Long id) {
		OrderDto author = null;
        try {
            Optional<OrderEntity> dbAuthor = orderRepository.findById(id);
            if(dbAuthor != null){
                author = toOrder(dbAuthor.get());
            }
        } catch (Exception e) {
            author = null;
        }
        return author;

	}

	@Override
	public OrderDto post(OrderDto order) {
		OrderDto response = null;
        try {
        	OrderEntity dbAuthor = new OrderEntity(order.getId(),order.getNumber(),order.getClient(),order.getTotal(),order.getDateOrder(), null);
        	List<OrderDetailEntity> orderDetailList= new ArrayList<OrderDetailEntity>();
        	
        	for(OrderDetailDto orderDetailDto: order.getDetail())
        	{
        		OrderDetailEntity orderDetailEntity= new OrderDetailEntity();
        		orderDetailEntity.setDetail(orderDetailDto.getDetail());        		
        		orderDetailEntity.setCantidad(orderDetailDto.getCantidad());
        		orderDetailEntity.setPrecioUnitario(orderDetailDto.getPrecioUnitario());
        		orderDetailEntity.setTotalDetail(orderDetailDto.getTotalDetail());
        		orderDetailEntity.setDborden(dbAuthor);
        		orderDetailList.add(orderDetailEntity);	
        	}
        	dbAuthor.setDbdetail(orderDetailList);
        	orderRepository.save(dbAuthor);
        	orderRepository.flush();
            response = toOrder(dbAuthor);
        } catch (Exception e) {
            response = null;
        }
        return response;

	}

	@Override
	public OrderDto toOrder(OrderEntity dbOrden) {
        List<OrderDetailDto> books = new ArrayList<>();
        if(dbOrden.getDbdetail() != null){
            for(OrderDetailEntity dbBook : dbOrden.getDbdetail()){
                books.add(orderDetailService.toOrderDetail(dbBook));
            }
        }
        return new OrderDto(dbOrden.getId(),dbOrden.getNumber(),dbOrden.getClient(),dbOrden.getTotal(),dbOrden.getDateOrder(),books);

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}


}
