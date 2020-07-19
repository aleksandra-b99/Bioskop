package com.example.Bioskop.data;

import com.example.Bioskop.entity.Administrator;
import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledaoc;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.repository.AdministratorRepository;
import com.example.Bioskop.repository.BioskopRepository;
import com.example.Bioskop.repository.FilmRepository;
import com.example.Bioskop.repository.GledaocRepository;
import com.example.Bioskop.repository.KorisnikRepository;
import com.example.Bioskop.repository.MenadzerRepository;
import com.example.Bioskop.repository.SalaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    FilmRepository filmRepository;
    
    @Autowired
    GledaocRepository gledaocRepository; 
    
    @Autowired
    MenadzerRepository menadzerRepository;
    
    @Autowired
    SalaRepository salaRepository;
    
    @Autowired 
    BioskopRepository bioskopRepository;
    
    @Autowired
    AdministratorRepository administratorRepository;

    @Override
    public void run(String... args) throws Exception {

    	Gledaoc gledaoc3 = new Gledaoc("Alex", "Aleksandra", "Borisavljevic", encoder.encode("123"), "123456", "aleks@mail.com", Date.valueOf(LocalDate.now()), "gledaoc", true);
        Administrator administrator1 = new Administrator("Cici", "Natasa", "Borisavljevic", encoder.encode("abc"), "123456", "cici@mail.com", Date.valueOf(LocalDate.now()), "admin", true);
        Gledaoc gledaoc1 = new Gledaoc("Edit", "Edit", "Berenyi", encoder.encode("e"), "123", "edit@mail.com", Date.valueOf(LocalDate.now()), "gledaoc", true);
        Gledaoc gledaoc2 = new Gledaoc("Jovan", "Djordje", "Janjic", encoder.encode("j"), "1", "JUl@mail.com", Date.valueOf(LocalDate.now()), "gledaoc", true);
        Menadzer menadzer1=new Menadzer("draga","Dragica","Daric",encoder.encode("d"),"064567888","dragica.gmail.com",Date.valueOf(LocalDate.now()), "menadzer", true);
        Menadzer menadzer2=new Menadzer("draga2","Dragica2","Daric2",encoder.encode("d2"),"0645678882","dragica2.gmail.com",Date.valueOf(LocalDate.now()), "menadzer", true);
        
        gledaocRepository.save(gledaoc2);
        gledaocRepository.save(gledaoc1);
        gledaocRepository.save(gledaoc3);
        
        menadzerRepository.save(menadzer1);
        menadzerRepository.save(menadzer2);
        
        administratorRepository.save(administrator1);

        //Sala sala1 = new Sala(36, "tirkizna",1);
        //salaRepository.save(sala1);
        //Bioskop bioskop1=new Bioskop("omladinska", "7.juli", "069306555", "0@gmail.com",menadzer1);
        Film film1 = new Film( "Neki tamo", "Opis", "zanr", "50 minuta", 4.5);
        //bioskopRepository.save(bioskop1);
        filmRepository.save(film1);
    }
}