package com.univkin.angularspringboot.service;

import java.util.List;

import com.univkin.angularspringboot.entite.Categorie;

public interface ICategorie {
	public List<Categorie> recupererToutesCategories();

	public Categorie chercherCategorieParCodeCat(String code);
}
