package com.univkin.angularspringboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univkin.angularspringboot.entite.Categorie;
import com.univkin.angularspringboot.repository.CategorieRepository;
import com.univkin.angularspringboot.service.ICategorie;

@Service
public class CategorieServiceImpl implements ICategorie {
	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public List<Categorie> recupererToutesCategories() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie chercherCategorieParCodeCat(String code) {
		return categorieRepository.findCategorieByCodeCategorie(code);
	}

}
