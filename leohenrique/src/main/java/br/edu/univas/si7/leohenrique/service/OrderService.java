package br.edu.univas.si7.leohenrique.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.leohenrique.model.Customer;
import br.edu.univas.si7.leohenrique.model.Order;
import br.edu.univas.si7.leohenrique.model.dto.OrderDTO;
import br.edu.univas.si7.leohenrique.repositories.CustomerRepository;
import br.edu.univas.si7.leohenrique.repositories.OrderRepository;
import br.edu.univas.si7.leohenrique.support.exceptions.InvalidDataException;
import br.edu.univas.si7.leohenrique.support.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
    @Autowired
	private OrderRepository repo;
    @Autowired
    private CustomerRepository custRepo;
    
    public List<Order> findAll(){
    	return repo.findAll();
    }
    
    public Order findById(Integer id) {
    	Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Order " + id + " not found."));
    }
    
    public List<Order> findByCustomer(Customer customerId) {
		return repo.findByCustomer(customerId);
	}
    public Order createOrder(Order newOrder) {
    	Optional<Order> existorder = repo.findById(newOrder.getId());
    	if (existorder.isPresent()) {
			throw new InvalidDataException("Order already exist with this id");
		}
    	Order order = repo.save(newOrder);
    	
    	return order;	
    }
    
    public Order toOrder(OrderDTO dto) {
		return new Order();
    }

}
