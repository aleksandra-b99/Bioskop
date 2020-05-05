package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Sala implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private int kapacitet;
	@Column
	private String oznaka_sale;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public String getOznaka_sale() {
		return oznaka_sale;
	}
	public void setOznaka_sale(String oznaka_sale) {
		this.oznaka_sale = oznaka_sale;
	}
	@Override
	public String toString() {
		return "Sala [id=" + id + ", kapacitet=" + kapacitet + ", oznaka_sale=" + oznaka_sale + "]";
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Bioskop bioskop;
	//ima vise sala u jednom bioskopu
	
	@ManyToMany ( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "filmovi_u_sali",
    joinColumns = @JoinColumn(name = "sala_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> filmovi_u_sali=new HashSet<Film>();//u jednoj sali ima vise filmova
	
	@OneToMany
	private Set<Terminska_lista> projekcije=new HashSet<>();
	
}
