package org.jasr.spaced.controllers;

import java.util.Optional;

import org.jasr.spaced.entities.Card;
import org.jasr.spaced.entities.CardSet;
import org.jasr.spaced.entities.Deck;
import org.jasr.spaced.repositories.CardRepository;
import org.jasr.spaced.repositories.CardSetRepository;
import org.jasr.spaced.repositories.DeckRepository;
import org.jasr.spaced.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private DeckRepository deckRepository;
	@Autowired
	private CardSetRepository cardSetRepository;
	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService usersService;

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("cardsets", cardSetRepository.findAll());
		return "admin/index";
	}

	@PostMapping("/card")
	public String upsertType(@ModelAttribute Card entity) {
		cardRepository.saveAndFlush(entity);
		return "redirect:/admin/index";
	}

	@PostMapping("/password")
	public String changePassword(Model model,@RequestParam String cpassword, @RequestParam String npassword, @RequestParam String rpassword) {

		if (!StringUtils.isEmpty(cpassword) && !StringUtils.isEmpty(npassword) && npassword.equals(rpassword) && passwordEncoder.matches(cpassword, usersService.loadUserByUsername("admin").getPassword()))
			usersService.changePassword(cpassword, npassword);
		return "redirect:/admin/index";
	}

	@GetMapping("/card/delete/{id}")
	public ResponseEntity<Void> deleteType(@PathVariable Long id) {
		cardRepository.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/card/{id}")
	public ResponseEntity<Optional<Card>> card(@PathVariable Long id) {
		return this.get(cardRepository, id);
	}

	@PostMapping("/cardset")
	public String upsertCardSet(Model model,@ModelAttribute CardSet entity) {
		return this.upsert(cardSetRepository, model, entity);
	}

	@GetMapping("/cardset/delete/{id}")
	public ResponseEntity<Void> deleteCardSet(@PathVariable Long id) {
		return this.delete(cardSetRepository, id);
	}
	
	@GetMapping("/cardset/{id}")
	public ResponseEntity<Optional<CardSet>> cardset(@PathVariable Long id) {
		return this.get(cardSetRepository, id);
	}


	@GetMapping("/deck/{id}")
	public ResponseEntity<Optional<Deck>> deck(@PathVariable Long id) {
		return this.get(deckRepository, id);
	}

	@PostMapping("/deck")
	public String upsertDeck(Model model,@ModelAttribute Deck entity) {
		return this.upsert(deckRepository,model,entity);
	}

	@GetMapping("/deck/delete/{id}")
	public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
		return this.delete(deckRepository, id);
	}

	
	private <T> ResponseEntity<Optional<T>> get(JpaRepository<T, Long> repository,@PathVariable Long id) {
		return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
	}

	private <T> String upsert(JpaRepository<T, Long> repository,Model model,@ModelAttribute T entity) {
		repository.saveAndFlush(entity);
		return "redirect:/admin/index";
	}

	private <T> ResponseEntity<Void> delete(JpaRepository<T, Long> repository,@PathVariable Long id) {
		repository.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	
}
