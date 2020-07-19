package com.example.Bioskop.entity.dto;

import javax.persistence.Column;

public class SalaDTO {
	private Long id;
	private int kapacitet;
	private String oznakaSale;
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
	public String getOznakaSale() {
		return oznakaSale;
	}
	public void setOznakaSale(String oznaka_sale) {
		this.oznakaSale = oznaka_sale;
	}
	public SalaDTO() {}
	public SalaDTO(Long id, int kapacitet, String oznaka_sale) {
		super();
		this.id = id;
		this.kapacitet = kapacitet;
		this.oznakaSale = oznaka_sale;
	}
	
}
