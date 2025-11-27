package com.primosjoyeria.backend.repository;

import com.primosjoyeria.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
