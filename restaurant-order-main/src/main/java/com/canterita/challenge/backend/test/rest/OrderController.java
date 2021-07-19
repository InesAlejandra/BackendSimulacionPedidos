package com.canterita.challenge.backend.test.rest;

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

import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.service.IOrderService;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;

	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderDto getOrder(@PathVariable Long id) {
		return orderService.get(id);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDto>> getAll() {
		List<OrderDto> response = orderService.getAll();
		if (response != null ) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

    @PostMapping
    public ResponseEntity<OrderDto> post(@RequestBody OrderDto entity) {
    	OrderDto response = orderService.post(entity);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
	//@GetMapping(value = "/{id}")
    //public ResponseEntity<OrderDto> get(@PathVariable("id") Long id) {
	//	OrderDto response = orderService.get(id);
    //    if (response != null) {
   //         return ResponseEntity.status(HttpStatus.OK).body(response);
    //    } else {
    //        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    //    }
   // }

	
	

}
