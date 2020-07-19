package com.example.Bioskop.entity.dto;

public class UlogovaniKorisnik {
    private String korisnickoIme;
    private String uloga;

    public UlogovaniKorisnik(){}

    public UlogovaniKorisnik(String korisnickoIme, String uloga){
        this.korisnickoIme = korisnickoIme;
        this.uloga = uloga;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }
    public void setKorisnickoIme(String korisnickoIme){
        this.korisnickoIme = korisnickoIme;
    }
    public String getUloga(){
        return uloga;
    }
    public void setUloga(String uloga){
        this.uloga = uloga;
    }
}