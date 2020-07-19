package com.example.Bioskop.entity.dto;


public class BioskopDTO {
	private Long id;
	private String naziv;
	private String adresa;
	private String brojCentrale;
	private String email;
	public BioskopDTO() {}
	public BioskopDTO(Long id, String naziv, String adresa, String broj_centrale, String e_mail) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.brojCentrale = broj_centrale;
		this.email = e_mail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getBrojCentrale() {
		return brojCentrale;
	}
	public void setBrojCentrale(String brojCentrale) {
		this.brojCentrale = brojCentrale;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
