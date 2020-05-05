package com.example.Bioskop.entity;

import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Ocene_odgledanih_filmova implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="gledaoc_id")
	private Gledaoc gledaoc;
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;
	@Column (nullable=true)//ne mora svaki film koji je pogledan biti ocenjen
	private int ocena;
}
