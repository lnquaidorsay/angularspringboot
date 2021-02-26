package com.univkin.angularspringboot.controller;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.univkin.angularspringboot.entite.Categorie;
import com.univkin.angularspringboot.entite.Livre;
import com.univkin.angularspringboot.request.LivreRequest;
import com.univkin.angularspringboot.response.LivreResponse;
import com.univkin.angularspringboot.service.ICategorie;
import com.univkin.angularspringboot.service.ILivre;

@Controller
public class LivreController {
	@Autowired
	private ILivre livreService;

	@Autowired
	private ICategorie categoryServiceImpl;

	@RequestMapping(value = "/ajoutlivre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<LivreResponse> ajouterNouveauLivre(@RequestBody LivreRequest livreRequest) {
		Livre livreExist = livreService.chercherLivreParIsbn(livreRequest.getIsbn());
		ModelMapper modelMapper = new ModelMapper();
		Livre livreDto = modelMapper.map(livreRequest, Livre.class);
		if (livreExist != null) {
			return new ResponseEntity<LivreResponse>(HttpStatus.CONFLICT);
		}
		Categorie categorie = categoryServiceImpl
				.chercherCategorieParCodeCat(livreRequest.getCategorie().getCodeCategorie());
		livreDto.setCategorie(categorie);
		livreDto.setDateEnregistrement(LocalDate.now());
		Livre livreEnregistre = livreService.sauvegarderLivre(livreDto);
		LivreResponse livreResponse = modelMapper.map(livreEnregistre, LivreResponse.class);
		return new ResponseEntity<LivreResponse>(livreResponse, HttpStatus.OK);
	}

	@PutMapping("/majlivre")
	public ResponseEntity<LivreResponse> modifierUnLivre(@RequestBody LivreRequest livreRequest) {
		ModelMapper modelMapper = new ModelMapper();
		Livre livreDto = modelMapper.map(livreRequest, Livre.class);
		Categorie categorie = categoryServiceImpl
				.chercherCategorieParCodeCat(livreRequest.getCategorie().getCodeCategorie());
		livreDto.setCategorie(categorie);
		Livre livreUpdate = livreService.miseAjourLivre(livreDto);
		LivreResponse livreResponse = modelMapper.map(livreUpdate, LivreResponse.class);
		return new ResponseEntity<LivreResponse>(livreResponse, HttpStatus.OK);
	}

	@DeleteMapping("/supprlivre/{livreId}")
	public ResponseEntity<String> supprimerUnLivre(@PathVariable Integer livreId) {

		livreService.supprimerLivre(livreId);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}
