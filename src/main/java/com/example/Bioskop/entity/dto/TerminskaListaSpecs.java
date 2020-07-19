package com.example.Bioskop.entity.dto;
import org.springframework.data.jpa.domain.Specification;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.TerminskaLista;

public class TerminskaListaSpecs {
	public static Specification<TerminskaLista> pocetakEquals(String pocetak) {
        return (root, query, builder) -> 
        pocetak == null ? 
                 builder.conjunction() :
                 builder.equal(root.get("pocetak"), pocetak);
      }
	public static Specification<TerminskaLista> cenaEquals(String cena) {
        return (root, query, builder) -> 
        cena == null ? 
                 builder.conjunction() :
                 builder.equal(root.get("cena"), cena);
      }
	public static Specification<TerminskaLista> danProjekcijeEquals(String danProjekcije) {
        return (root, query, builder) -> 
        danProjekcije == null ? 
                 builder.conjunction() :
                 builder.equal(root.get("danProjekcije"), danProjekcije);
      }
	public static Specification<TerminskaLista> filmEquals(Film film) {
        return (root, query, builder) -> 
        film == null ? 
                 builder.conjunction() :
                 builder.equal(root.get("film"), film);
      }
}
