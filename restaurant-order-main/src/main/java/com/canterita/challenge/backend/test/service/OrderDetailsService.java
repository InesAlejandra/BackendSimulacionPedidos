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
import com.canterita.challenge.backend.test.repository.OrderDetailRepository;
import com.canterita.challenge.backend.test.repository.OrderRepository;

@Service
public class OrderDetailsService implements IOrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<OrderDetailDto> getAll() {
		List<OrderDetailDto> list = new ArrayList<>();
		try {
			List<OrderDetailEntity> list_dbDetail =  orderDetailRepository.findAll();
			if(list_dbDetail != null) {
				for(OrderDetailEntity dbOrderDetail : list_dbDetail) {										
					list.add(toOrderDetail(dbOrderDetail));
				}
			} 
			}catch (Exception e) {
				list = new ArrayList<>();
			
		}
		return list;		
	}

	@Override
	public OrderDetailDto get(Long id) {
		OrderDetailDto detail = null;
        try {
            Optional<OrderDetailEntity> dbOrderDetail = orderDetailRepository.findById(id);
            if(dbOrderDetail != null){
            	 detail  = toOrderDetail(dbOrderDetail.get());
            }
        } catch (Exception e) {
        	 detail  = null;
        }
        return detail ;

	}

	@Override
	public OrderDetailDto post(OrderDetailDto orderdetail) {
		OrderDetailDto response;
        try{
            Optional<OrderEntity> dbOrder = orderRepository.findById(orderdetail.getIdorder());
            OrderDetailEntity dbOrderDetail= new OrderDetailEntity(orderdetail.getId(),orderdetail.getDetail(),orderdetail.getCantidad(),orderdetail.getPrecioUnitario(),orderdetail.getTotalDetail(),dbOrder.get());
            orderDetailRepository.save(dbOrderDetail);
            orderDetailRepository.flush();
            response = toOrderDetail(dbOrderDetail);
        }
        catch(Exception e){
            response = null;
        }
        return response;

	}

	@Override
	public OrderDetailDto toOrderDetail(OrderDetailEntity dbOrdenDetail) {
		return new OrderDetailDto(dbOrdenDetail.getId(), dbOrdenDetail.getDborden().getId(),dbOrdenDetail.getDetail(),dbOrdenDetail.getCantidad(),dbOrdenDetail.getPrecioUnitario(),dbOrdenDetail.getTotalDetail());
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}




}
