package com.example.Bioskop.entity;

import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Repertoar implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="bioskop_id")
	private Bioskop bioskop;
	@ManyToOne
	@JoinColumn(name="terminska_lista_id")
	private Terminska_lista terminska_lista;
}
