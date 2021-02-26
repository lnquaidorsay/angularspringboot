package com.univkin.angularspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.univkin.angularspringboot.entite.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
	@Query("SELECT c FROM Categorie c WHERE c.codeCategorie = ?1")
	public Categorie findCategorieByCodeCategorie(String code);

	Categorie findByCodeCategorie(String codeCategorie);
}
