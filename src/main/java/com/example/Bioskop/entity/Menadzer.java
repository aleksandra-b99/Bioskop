package com.example.Bioskop.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Menadzer extends Korisnik{
	
	//jedan menadzer moze da kontolise vise bioskopa
	@OneToMany(mappedBy="menadzer",fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<Bioskop> bioskopi=new HashSet<>();
}
