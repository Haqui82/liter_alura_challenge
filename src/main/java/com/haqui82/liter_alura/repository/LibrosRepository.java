package com.haqui82.liter_alura.repository;

import com.haqui82.liter_alura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository <Libros, Long>{
}
