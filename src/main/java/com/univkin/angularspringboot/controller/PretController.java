package com.univkin.angularspringboot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.univkin.angularspringboot.entite.Pret;
import com.univkin.angularspringboot.request.PretRequest;
import com.univkin.angularspringboot.response.PretResponse;
import com.univkin.angularspringboot.service.IPret;

@Controller
public class PretController {
	@Autowired
	private IPret pretService;

	@RequestMapping(value = "/ajoutpret", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<PretResponse> ajouterNouveauPret(@RequestBody PretRequest pretRequest) {
		ModelMapper modelMapper = new ModelMapper();
		Pret pretDto = modelMapper.map(pretRequest, Pret.class);

		Pret pretEnregistre = pretService.nouveauPret(pretDto);
		PretResponse pretResponse = modelMapper.map(pretEnregistre, PretResponse.class);
		return new ResponseEntity<PretResponse>(pretResponse, HttpStatus.OK);
	}
}
