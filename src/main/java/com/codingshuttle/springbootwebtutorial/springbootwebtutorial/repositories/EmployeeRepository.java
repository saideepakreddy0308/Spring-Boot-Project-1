package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Spring Data jpa defines more complex queries using jpql, and need to make implementations nowadays
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

//    List<EmployeeEntity> findByName(String name);
//      this automatically get defined by the JPA Repository


}
