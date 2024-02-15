package databaseSpring.databaseSpringBoot.repository;

import databaseSpring.databaseSpringBoot.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface databaserepository extends JpaRepository<product,Integer> {

    List<product> findByName(String name);
}
