package com.example.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.*;

public interface FilmRepository extends JpaRepository<Film, Long>{
}
