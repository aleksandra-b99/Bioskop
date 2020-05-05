package com.example.Bioskop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Bioskop.entity.Gledaoc;
import com.example.Bioskop.repository.GledaocRepository;


@SpringBootApplication
public class BioskopApplication implements CommandLineRunner{
	@Autowired
	private GledaocRepository gledaocRepository;
	@Override
	public void run(String... args) {
		// kreiram novi objekat klase gledaoc
		Gledaoc gledaoc=new Gledaoc();
		gledaoc.setKorisnicko_ime("daca432");
		gledaoc.setAktivan(true);
		
		this.gledaocRepository.save(gledaoc);
		List<Gledaoc> gledaoci=this.gledaocRepository.findAll();
		for(Gledaoc g:gledaoci) {
			System.out.println(g);
		}
		
	}
	public static void main(String[] args) {
		SpringApplication.run(BioskopApplication.class, args);
	}

}
