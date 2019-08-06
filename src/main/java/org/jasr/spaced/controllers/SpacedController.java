package org.jasr.spaced.controllers;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityExistsException;

import org.jasr.spaced.entities.Card;
import org.jasr.spaced.entities.CardSet;
import org.jasr.spaced.repositories.CardRepository;
import org.jasr.spaced.repositories.CardSetRepository;
import org.jasr.spaced.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpacedController {
	@Autowired
	private CardSetRepository cardSetRepository;
	@Autowired
	private CardRepository cardRepository;

	private final int PAGE_SIZE = 10;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService usersService;

	@GetMapping("/password")
	public String password(Model model) {
		return "password";
	}

	@GetMapping("/about")
	public String about(Model model) {
		return "about";
	}

	@GetMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("cardsets", cardSetRepository.findAll());
		return "index";
	}

	@GetMapping("/cardset/{id}/cards/{page}")
	@ResponseBody
	public Page<Card> cards(Model model, @PathVariable Long id, @PathVariable("page") int page) {
		return cardRepository.findAllByCardsetIdOrderByTaskAsc(id, PageRequest.of(page, PAGE_SIZE));
	}

	@GetMapping("/cardset/{id}")
	public String cardset(Model model, @PathVariable Long id) {
		model.addAttribute("cardset", cardSetRepository.findById(id).get());
		return "cardset";
	}

	@PostMapping("/entities/cardset")
	public ResponseEntity<String> upsertCardSet(Model model, @ModelAttribute CardSet entity) {
		String result = this.upsert(cardSetRepository, model, entity, "redirect:/index");
		return ResponseEntity.status((result == null) ? HttpStatus.CONFLICT : HttpStatus.FOUND).body(result);
	}

	@PostMapping("/entities/card")
	public ResponseEntity<String> upsertCard(Model model, @ModelAttribute(name = "cardset-id") Long id, @ModelAttribute Card entity) {
		entity.setCardset(new CardSet(id));
		String result = this.upsert(cardRepository, model, entity, "redirect:/cardset/" + id);
		return ResponseEntity.status((result == null) ? HttpStatus.CONFLICT : HttpStatus.FOUND).body(result);
	}

	@GetMapping("/entities/card/delete/{id}")
	public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
		return this.delete(cardRepository, id);
	}

	@GetMapping("/entities/cardset/delete/{id}")
	public String deleteCardSet(@PathVariable Long id) {
		this.delete(cardSetRepository, id);
		return "redirect:/index";
	}

	/*
	 * private CardSet filterCards(CardSet cardset) { Instant now = Instant.now();
	 * cardset.setCards(cardset.getCards().stream().filter( e ->
	 * e.getRecurrenceDays() >= ChronoUnit.DAYS.between(e.getPlay().toInstant(),
	 * now) ).collect(Collectors.toList()));
	 * 
	 * return cardset; }
	 */
	@GetMapping("/play/{id}")
	public String play(Model model, @PathVariable Long id) {
		model.addAttribute("description", cardSetRepository.getOne(id).getDescription());
		model.addAttribute("cards", Stream.concat(cardRepository.findTop5ByCardsetIdAndPlayIsNull(id).stream(), cardRepository.findTop10ByCardsetIdAndRecurrenceGreaterThanAndPlayIsNotAndPlayIsNotNullOrderByPlayAsc(id, -1, new Date()).stream()).collect(Collectors.toList()));
		return "play";
	}

	@PostMapping("/play/right/{id}")
	public ResponseEntity<Void> right(@PathVariable Long id) {
		cardRepository.updateCardDate(new Date(), new Date(), id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/play/wrong/{id}")
	public ResponseEntity<Void> wrong(@PathVariable Long id) {
		cardRepository.updateCardDate(new Date(), id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private <T> String upsert(JpaRepository<T, Long> repository, Model model, @ModelAttribute T entity, String result) {
		try {
			repository.saveAndFlush(entity);
		} catch (EntityExistsException e) {
			return null;
		}
		return result;
	}

	private <T> ResponseEntity<Void> delete(JpaRepository<T, Long> repository, @PathVariable Long id) {
		repository.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	// -------------------------------------------------------------------------------------------------------------

	private static String WRONG_PASSWORD = "Current password is incorrect or empty";
	private static String PASSWORD_MISMATCH = "New passwords input does not match";

	@PostMapping("/password")
	public String changePassword(Model model, @RequestParam String cpassword, @RequestParam String npassword, @RequestParam String rpassword) {

		if (StringUtils.isEmpty(cpassword) || !passwordEncoder.matches(cpassword, usersService.loadUserByUsername("admin").getPassword())) {
			model.addAttribute("error", WRONG_PASSWORD);
			return "/password";
		} else if (StringUtils.isEmpty(npassword) || StringUtils.isEmpty(rpassword) || !npassword.equals(rpassword)) {
			model.addAttribute("error", PASSWORD_MISMATCH);
			return "/password";
		} else
			usersService.changePassword(cpassword, npassword);
		return "redirect:/index";
	}

	@GetMapping("/card/{id}")
	public ResponseEntity<Optional<Card>> card(@PathVariable Long id) {
		return new ResponseEntity<>(cardRepository.findById(id), HttpStatus.OK);
	}

}