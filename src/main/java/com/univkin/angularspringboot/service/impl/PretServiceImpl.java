package com.univkin.angularspringboot.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univkin.angularspringboot.entite.Etudiant;
import com.univkin.angularspringboot.entite.Livre;
import com.univkin.angularspringboot.entite.Pret;
import com.univkin.angularspringboot.repository.EtudiantRepository;
import com.univkin.angularspringboot.repository.LivreRepository;
import com.univkin.angularspringboot.repository.PretRepository;
import com.univkin.angularspringboot.service.IPret;

@Service
public class PretServiceImpl implements IPret {
	@Autowired
	private PretRepository pretRepository;

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private LivreRepository livreRepository;

	@Override
	public List<Pret> findAllLoansByEndDateBefore(LocalDate maxEndDate) {
		// TODO Auto-generated method stub
		return pretRepository.findByFinPretBefore(maxEndDate);
	}

	@Override
	public List<Pret> getAllOpenLoansOfThisStudent(String email) {
		// TODO Auto-generated method stub
		return pretRepository.recupererTousLesPretsDunEtudiant(email);
	}

	@Override
	public Pret saveLoan(Pret pret) {
		return pretRepository.save(pret);
	}

	@Override
	public void closeLoan(Pret pret) {

	}

	@Override
	public Pret nouveauPret(Pret pret) {
		Etudiant et = pret.getEtudiant();
		Etudiant etudToSave = etudiantRepository.findByEmailIgnoreCase(et.getEmail());
		if (etudToSave != null) {
			pret.setEtudiant(etudToSave);
		} else {
			Etudiant etudSaved = etudiantRepository.save(et);
			pret.setEtudiant(etudSaved);
		}

		Livre liv = pret.getLivre();
		Livre livToSave = livreRepository.findByIsbnIgnoreCase(liv.getIsbn());
		if (livToSave != null) {
			pret.setLivre(livToSave);
		} else {
			Livre livSaved = livreRepository.save(liv);
			pret.setLivre(livSaved);
		}
		return pretRepository.save(pret);
	}

}
