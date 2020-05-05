package com.example.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Gledaoc extends Korisnik{
	@ManyToMany
	@JoinTable(name = "rezervisani_filmovi",
    joinColumns = @JoinColumn(name = "gledaoc_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> rezervisani_filmovi=new HashSet<>();
}
