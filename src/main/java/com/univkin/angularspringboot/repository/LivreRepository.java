package com.univkin.angularspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univkin.angularspringboot.entite.Livre;

public interface LivreRepository extends JpaRepository<Livre, Integer> {

	public Livre findByIsbnIgnoreCase(String isbn);

	public Livre findByTitre(String titre);

	public Optional<Livre> findById(Integer id);

	public List<Livre> findByTitreLikeIgnoreCase(String title);

	/*
	 * @Query("SELECT l" + "FROM Livre l " + "INNER JOIN l.categorie cat " +
	 * "WHERE cat.codeCategorie = :codeCategorie") public List<Livre>
	 * findByCategorie(@Param("codeCategorie") String codeCategorie);
	 */

}
