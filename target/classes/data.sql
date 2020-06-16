--
----INSERT INTO ADMINISTRATOR (korisnicko_ime, aktivan,uloga) VALUES ('Voja9',true ,'administrator');
--
----INSERT INTO MENADZER (ime,prezime,korisnicko_ime, e_adresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Lana','Ivic','lan@','ivic@gmail.com','123',true,'065-123-522','menadzer','1993-02-10');
----INSERT INTO MENADZER (ime,prezime,korisnicko_ime, e_adresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Srdjan','Drazic','srk@','sdrazic@gmail.com','sifra123',true,'065-654-112','menadzer','1992-12-10');
--
--INSERT INTO BIOSKOP (naziv,broj_centrale, adresa,e_mail,menadzer_id) VALUES ('Jadran','024-756-867','Maksima Gorkog 48c','jadran1911@gmail.com',2);
--INSERT INTO BIOSKOP (naziv,broj_centrale, adresa, e_mail,menadzer_id) VALUES ('Lifka','024-756-666','Servo Mihalja 4','lifka@gmail.com',1);
--INSERT INTO BIOSKOP (naziv,broj_centrale, adresa, e_mail,menadzer_id) VALUES ('Otvoreni univerzitet','024-744-666','Bele Bartoka 66','open@gmail.com',1);
--
INSERT INTO FILM (naziv,zanr,ocena,opis,trajanje) VALUES ('The best offer','drama',8,'Film prati život Johna Nasha, onaj kakvim je zabilježen, a koji počinje njegovim uspjesima na fakultetu i nastavlja se visoko zapaženim znanstvenim radom.','2h i 30 min');
INSERT INTO FILM (naziv,zanr,ocena,opis,trajanje) VALUES ('Beautiful mind','drama',6,'Glavni lik, pomenuti Virdžil Oldmen je bogat, ekscentričan i beskrupulozan čovjek koji je svoj život potčinio sakupljanju umjetničkih djela. On je poznati voditelj aukcija, prepoznat kao najbolji u svojoj branši. ','1h i 55 min');
INSERT INTO FILM (naziv,zanr,trajanje,ocena,opis) VALUES ('Notebook','romantican','2h i 30 min',5,'ljubavna prica dvoje mladih puna preokreta ');
INSERT INTO FILM (naziv,zanr,ocena,opis,trajanje) VALUES ('La casa de papel','avanturistika',9,'Obaranje rekorda gledanosti nije važno samo za Netflix, već najviše za špansku i uopšte internacionalnu kinematografiju, koje u poslednje vreme dokazuju da nisu samo filmovi i serije na engleskom jeziku primamljivi publici širom sveta.','3h');
--
----INSERT INTO FILMOVI_U_BIOSKOPU (bioskop_id,film_id) VALUES (1,1);
----INSERT INTO FILMOVI_U_BIOSKOPU (bioskop_id,film_id) VALUES (1,2);
----INSERT INTO FILMOVI_U_BIOSKOPU (bioskop_id,film_id) VALUES (2,1);
--
----INSERT INTO GLEDAOC (ime,prezime,korisnicko_ime,aktivan,uloga,lozinka,e_adresa,kontakt_telefon,datum_rodjenja) VALUES ('Aleksandra','Borisavljevic','aleksandr@99' ,false,'gledaoc','tajna','a@gmail.com','546-76-5432','1999-06-30');
----INSERT INTO GLEDAOC (ime,aktivan,uloga) VALUES ('Natasa', false,'gledaoc');
--
--INSERT INTO SALA (oznaka_sale,bioskop_id,kapacitet) VALUES ('sala1',2,10);
--INSERT INTO SALA (oznaka_sale,bioskop_id,kapacitet) VALUES ('sala2',2,23);
--INSERT INTO SALA (oznaka_sale,bioskop_id,kapacitet) VALUES ('sala-crvena',1,43);
--INSERT INTO SALA (oznaka_sale,bioskop_id,kapacitet) VALUES ('sala-plava',1,34);
--
----INSERT INTO FILMOVI_U_SALI (sala_id, film_id) VALUES (1,1);
----INSERT INTO FILMOVI_U_SALI (sala_id, film_id) VALUES (2,1);
----INSERT INTO FILMOVI_U_SALI (sala_id, film_id) VALUES (1,4);
--
--
--INSERT INTO TERMINSKA_LISTA (cena,naziv_filma,sala_id,broj_rezervacija) VALUES (250,'Beautiful mind',1,3);
--INSERT INTO TERMINSKA_LISTA (cena,naziv_filma,pocetak,sala_id,broj_rezervacija) VALUES (300,'La casa de papel','11h',2,5);
--INSERT INTO TERMINSKA_LISTA (cena,naziv_filma, pocetak,sala_id,broj_rezervacija) VALUES (350,'La casa de papel','11h',1,10);
--
--
--INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (1,1,6);
--INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (1,2,10);
--INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (1,4,9);
--INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (2,3,9);
--INSERT INTO OCENE_ODGLEDANIH_FILMOVA (gledaoc_id,film_id,ocena) VALUES (2,4,7);
--
--INSERT INTO REZERVISANI_FILMOVI (gledaoc_id,film_id) VALUES (2,4);
--INSERT INTO REZERVISANI_FILMOVI (gledaoc_id,film_id) VALUES (2,1);
--INSERT INTO REZERVISANI_FILMOVI (gledaoc_id,film_id) VALUES (1,4);
--
--INSERT INTO REPERTOAR (bioskop_id, terminska_lista_id) VALUES (2,1);
--INSERT INTO REPERTOAR (bioskop_id, terminska_lista_id) VALUES (2,2);
--INSERT INTO REPERTOAR (bioskop_id, terminska_lista_id) VALUES (2,3);
--
--
INSERT INTO KORISNIK (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Lana','Ivic','lan@','ivic@gmail.com','123',false,'065-123-522','menadzer','1993-02-10');
INSERT INTO KORISNIK (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Iva','Ivicic','aleksandra','ivic@gmail.com','12345',true,'065-123-522','menadzer','1993-02-10');
INSERT INTO KORISNIK (ime,prezime,korisnicko_ime, eadresa,lozinka, aktivan,kontakt_telefon,uloga,datum_rodjenja) VALUES ('Sena','Orlic','aleks','ivic@gmail.com','3',true,'065-123-522','gledaoc','1993-02-10');
