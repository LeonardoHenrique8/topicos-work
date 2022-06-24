package br.edu.univas.si7.leohenrique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.univas.si7.leohenrique.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Transactional(readOnly = true)
    Customer findByEmail(String email);
}
