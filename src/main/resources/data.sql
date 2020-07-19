----
------INSERT INTO ADMINISTRATOR (korisnicko_ime, aktivan,uloga) VALUES ('Voja9',true ,'administrator');
----
----INSERT INTO MENADZER (ime,prezime,korisnicko_ime, e_adresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Lana','Ivic','lan@','ivic@gmail.com','123',true,'065-123-522','menadzer','1993-02-10');
----INSERT INTO MENADZER (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Srdjan','Drazic','srk@','sdrazic@gmail.com','sifra123',true,'065-654-112','menadzer','1992-12-10');
--INSERT INTO MENADZER (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Aleks','Ivcic','a@a','ale@gmail.com','123',false,'065-123-522','menadzer','1993-02-10');
--
INSERT INTO BIOSKOP (naziv,broj_centrale, adresa,email) VALUES ('Jadran','024-756-867','Maksima Gorkog 48c','jadran1911@gmail.com');
INSERT INTO BIOSKOP (naziv,broj_centrale, adresa, email) VALUES ('Lifka','024-756-666','Servo Mihalja 4','lifka@gmail.com');
INSERT INTO BIOSKOP (naziv,broj_centrale, adresa, email) VALUES ('Otvoreni univerzitet','024-744-666','Bele Bartoka 66','open@gmail.com');
----
INSERT INTO FILM (naziv,zanr,opis,trajanje) VALUES ('The best offer','drama','Film prati život Johna Nasha, onaj kakvim je zabilježen, a koji počinje njegovim uspjesima na fakultetu i nastavlja se visoko zapaženim znanstvenim radom.','2h i 30 min');
INSERT INTO FILM (naziv,zanr,opis,trajanje) VALUES ('Beautiful mind','drama','Glavni lik, pomenuti Virdžil Oldmen je bogat, ekscentričan i beskrupulozan čovjek koji je svoj život potčinio sakupljanju umjetničkih djela. On je poznati voditelj aukcija, prepoznat kao najbolji u svojoj branši. ','1h i 55 min');
INSERT INTO FILM (naziv,zanr,trajanje,opis) VALUES ('Notebook','romantican','2h i 30 min','ljubavna prica dvoje mladih puna preokreta ');
INSERT INTO FILM (naziv,zanr,opis,trajanje) VALUES ('La casa de papel','avanturistika','Obaranje rekorda gledanosti nije važno samo za Netflix, već najviše za špansku i uopšte internacionalnu kinematografiju, koje u poslednje vreme dokazuju da nisu samo filmovi i serije na engleskom jeziku primamljivi publici širom sveta.','3h');
----
------INSERT INTO FILMOVI_U_BIOSKOPU (bioskop_id,film_id) VALUES (1,1);
------INSERT INTO FILMOVI_U_BIOSKOPU (bioskop_id,film_id) VALUES (1,2);
------INSERT INTO FILMOVI_U_BIOSKOPU (bioskop_id,film_id) VALUES (2,1);
----
------INSERT INTO GLEDAOC (ime,prezime,korisnicko_ime,aktivan,uloga,lozinka,e_adresa,kontakt_telefon,datum_rodjenja) VALUES ('Aleksandra','Borisavljevic','aleksandr@99' ,false,'gledaoc','tajna','a@gmail.com','546-76-5432','1999-06-30');
------INSERT INTO GLEDAOC (ime,aktivan,uloga) VALUES ('Natasa', false,'gledaoc');
----
INSERT INTO SALA (oznaka_sale,kapacitet,bioskop_id) VALUES ('sala1',10,1);
INSERT INTO SALA (oznaka_sale,bioskop_id,kapacitet) VALUES ('sala2',1,23);
INSERT INTO SALA (oznaka_sale,bioskop_id,kapacitet) VALUES ('sala-crvena',2,43);
INSERT INTO SALA (oznaka_sale,bioskop_id,kapacitet) VALUES ('sala-plava',2,34);
----
------INSERT INTO FILMOVI_U_SALI (sala_id, film_id) VALUES (1,1);
------INSERT INTO FILMOVI_U_SALI (sala_id, film_id) VALUES (2,1);
------INSERT INTO FILMOVI_U_SALI (sala_id, film_id) VALUES (1,4);
----
----
--INSERT INTO TERMINSKA_LISTA (cena,sala_id,broj_rezervacija,pocetak,dan_projekcije,film_id) VALUES (250,1,3,'4h i 30 min','2020-06-30',1);
INSERT INTO TERMINSKA_LISTA (cena,pocetak,sala_id,broj_rezervacija,dan_projekcije,film_id) VALUES (300,'11h',1,5,'2020-07-30',1);
INSERT INTO TERMINSKA_LISTA (cena, pocetak,sala_id,broj_rezervacija,dan_projekcije,film_id) VALUES (350,'11h',2,10,'2020-08-30',2);
INSERT INTO TERMINSKA_LISTA (cena, pocetak,sala_id,broj_rezervacija,dan_projekcije,film_id) VALUES (200,'20h',3,11,'2020-09-30',4);
INSERT INTO TERMINSKA_LISTA (cena, pocetak,sala_id,broj_rezervacija,dan_projekcije,film_id) VALUES (350,'19h',4,34,'2020-10-30',3);
----
----
----INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (1,1,6);
----INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (1,2,10);
----INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (1,4,9);
----INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (2,3,9);
----INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (2,4,7);
----
----INSERT INTO REZERVISANI_FILMOVI (gledaoc_id,film_id) VALUES (2,4);
----INSERT INTO REZERVISANI_FILMOVI (gledaoc_id,film_id) VALUES (2,1);
----INSERT INTO REZERVISANI_FILMOVI (gledaoc_id,film_id) VALUES (1,4);
----
----INSERT INTO REPERTOAR (bioskop_id, terminska_lista_id) VALUES (2,1);
----INSERT INTO REPERTOAR (bioskop_id, terminska_lista_id) VALUES (2,2);
----INSERT INTO REPERTOAR (bioskop_id, terminska_lista_id) VALUES (2,3);
----
----
--INSERT INTO KORISNIK (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Lana','Ivic','lan@','ivic@gmail.com','123',false,'065-123-522','menadzer','1993-02-10');
----INSERT INTO KORISNIK (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Iva','Ivicic','aleksandra','ivic@gmail.com','12345',true,'065-123-522','menadzer','1993-02-10');
----INSERT INTO KORISNIK (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Sena','Orlic','aleks','ivic@gmail.com','3',true,'065-123-522','gledaoc','1993-02-10');
