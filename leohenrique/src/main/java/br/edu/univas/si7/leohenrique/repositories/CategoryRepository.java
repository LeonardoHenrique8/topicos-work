package br.edu.univas.si7.leohenrique.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.univas.si7.leohenrique.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Optional<Category> findByName(String categoryName);

	public List<Category> findByNameIn(List<String> categoryNames);
}
