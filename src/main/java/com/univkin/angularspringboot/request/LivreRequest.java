package com.univkin.angularspringboot.request;

import java.time.LocalDate;

import com.univkin.angularspringboot.entite.Categorie;

public class LivreRequest {
	private Integer id;

	private String titre;

	private String isbn;

	private LocalDate datePublication;

	private LocalDate dateEnregistrement;

	private Integer nbExemplaires;

	private String auteur;

	private Categorie categorie;

	public LivreRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}

	public LocalDate getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(LocalDate dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	public Integer getNbExemplaires() {
		return nbExemplaires;
	}

	public void setNbExemplaires(Integer nbExemplaires) {
		this.nbExemplaires = nbExemplaires;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}