package com.example.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.*;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	Sala findByOznakaSale(String naziv);
}
