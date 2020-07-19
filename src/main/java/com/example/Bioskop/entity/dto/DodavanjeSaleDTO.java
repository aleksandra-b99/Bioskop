package com.example.Bioskop.entity.dto;

public class DodavanjeSaleDTO {
	
	private String oznakaSale;
	private int kapacitet;
	//private Long bioskopId;
	
	public String getOznakaSale() {
		return oznakaSale;
	}
	public void setOznakaSale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public DodavanjeSaleDTO(String oznakaSale, int kapacitet) {
		super();
		this.oznakaSale = oznakaSale;
		this.kapacitet = kapacitet;
	}
	public DodavanjeSaleDTO () {}
}
