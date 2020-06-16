package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.*;

public interface BioskopRepository extends JpaRepository<Bioskop, Long>{
	List<Bioskop> findAllByMenadzerId(Long id);
}

