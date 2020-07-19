package com.example.Bioskop.entity.dto;

public class OdgledaniFilmoviSaOcenomDTO {
	
	private String naziv;
	private int ocena;
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
	public OdgledaniFilmoviSaOcenomDTO(String naziv, int ocena) {
		super();
		this.naziv = naziv;
		this.ocena = ocena;
	}
	public OdgledaniFilmoviSaOcenomDTO() {}
	
}
