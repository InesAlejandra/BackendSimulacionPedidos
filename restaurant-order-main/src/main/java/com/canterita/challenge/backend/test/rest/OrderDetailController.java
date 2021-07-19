package com.canterita.challenge.backend.test.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.canterita.challenge.backend.test.dto.OrderDetailDto;
import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.model.OrderDetailEntity;
import com.canterita.challenge.backend.test.service.IOrderDetailService;
import com.canterita.challenge.backend.test.service.IOrderService;

@RestController
@RequestMapping("/ordersDetail")
public class OrderDetailController {

	
		@Autowired
		private IOrderDetailService dao;

		
	    @GetMapping
	    public ResponseEntity<List<OrderDetailDto>> getAll() {
	        List<OrderDetailDto> response = dao.getAll();
	        if (response != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    }

	    @GetMapping(value = "/{id}")
	    public ResponseEntity<OrderDetailDto> get(@PathVariable("id") Long id) {
	    	OrderDetailDto response = dao.get(id);
	        if (response != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    }

	    @PostMapping
	    public ResponseEntity<OrderDetailDto> post(@RequestBody OrderDetailDto entity) {
	    	OrderDetailDto response = dao.post(entity);
	        if (response != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }
	    }


}
