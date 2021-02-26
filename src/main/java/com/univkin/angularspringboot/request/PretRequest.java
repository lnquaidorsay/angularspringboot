package com.univkin.angularspringboot.request;

import java.time.LocalDate;

import com.univkin.angularspringboot.entite.Etudiant;
import com.univkin.angularspringboot.entite.Livre;
import com.univkin.angularspringboot.entite.PretStatut;

public class PretRequest {
	private Integer id;

	private LocalDate debutPret;

	private LocalDate finPret;

	private PretStatut statut;

	private Livre livre;

	private Etudiant etudiant;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PretRequest() {
	}

	public LocalDate getDebutPret() {
		return debutPret;
	}

	public void setDebutPret(LocalDate debutPret) {
		this.debutPret = debutPret;
	}

	public LocalDate getFinPret() {
		return finPret;
	}

	public void setFinPret(LocalDate finPret) {
		this.finPret = finPret;
	}

	public PretStatut getStatut() {
		return statut;
	}

	public void setStatut(PretStatut statut) {
		this.statut = statut;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
