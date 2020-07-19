package com.example.Bioskop.entity.dto;

public class OdgledanFilmDTO {
	
	private String naziv;
	private int ocena;
	////get////////set
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public OdgledanFilmDTO(String naziv, int ocena) {
		super();
		this.naziv = naziv;
		this.ocena = ocena;
	}
	public OdgledanFilmDTO() {}
	
}
