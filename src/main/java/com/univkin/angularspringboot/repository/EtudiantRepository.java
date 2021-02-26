package com.univkin.angularspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univkin.angularspringboot.entite.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
	public Etudiant findByEmailIgnoreCase(String email);

	public List<Etudiant> findByPrenomIgnoreCase(String prenom);

	public Optional<Etudiant> findById(Integer id);
}
