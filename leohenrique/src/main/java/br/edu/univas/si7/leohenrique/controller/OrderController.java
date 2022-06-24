package br.edu.univas.si7.leohenrique.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.leohenrique.model.Customer;
import br.edu.univas.si7.leohenrique.model.Order;
import br.edu.univas.si7.leohenrique.model.dto.OrderDTO;
import br.edu.univas.si7.leohenrique.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
    private OrderService service;
	
	@GetMapping("")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> list = service.findAll()
            .stream()
            .map(c -> new OrderDTO(c))
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<OrderDTO> find(@PathVariable Integer id) {
        Order ord = service.findById(id);
        return ResponseEntity.ok().body(new OrderDTO(ord));
    }
	
	@GetMapping("/{customerId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Order>> listOrderByCustomer(@PathVariable Customer customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByCustomer(customerId));
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody @Valid Order order) {
		Order orderclass = new Order();
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(orderclass));
	}

}
