package com.example.practica2.repo;

import com.example.practica2.entidades.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
//    Optional<Endpoint> findByCodigoAndNombre(String codigo, String nombre);

}