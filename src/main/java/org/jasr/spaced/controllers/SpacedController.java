package org.jasr.spaced.controllers;

import java.util.Optional;

import org.jasr.spaced.entities.Card;
import org.jasr.spaced.entities.CardSet;
import org.jasr.spaced.repositories.CardRepository;
import org.jasr.spaced.repositories.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpacedController {
	@Autowired
    private CardSetRepository cardSetRepository;
	@Autowired
	private CardRepository cardRepository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	@Autowired
//	private UserService usersService;
	
    @GetMapping(value= {"/","/index"})
    public String index(Model model) {
    	model.addAttribute("cardsets", cardSetRepository.findAll());
        return "index";
    }
  
    @GetMapping("/cardset/{id}")
	public String cardset(Model model, @PathVariable Long id) {
    	model.addAttribute("cardset",this.get(cardSetRepository, id).getBody().get());
		return "cardset";
	}
    
	@PostMapping("/entities/cardset")
	public String upsertCardSet(Model model,@ModelAttribute CardSet entity) {
		return this.upsert(cardSetRepository, model, entity,"redirect:/index");
	}

	@PostMapping("/entities/card")
	public String upsertCard(Model model, @ModelAttribute(name="cardset-id") Long id, @ModelAttribute Card entity) {
		entity.setCardset(new CardSet(id));
		return this.upsert(cardRepository, model, entity,"redirect:/cardset/" + id);
	}
	
	@GetMapping("/entities/card/delete/{id}")
	public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
		return this.delete(cardRepository, id);
	}
	
	@GetMapping("/entities/cardset/delete/{id}")
	public ResponseEntity<Void> deleteCardSet(@PathVariable Long id) {
		return this.delete(cardSetRepository, id);
	}
	
	
	
	private <T> String upsert(JpaRepository<T, Long> repository,Model model,@ModelAttribute T entity, String result) {
		repository.saveAndFlush(entity);
		return result;
	}

	private <T> ResponseEntity<Void> delete(JpaRepository<T, Long> repository,@PathVariable Long id) {
		repository.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}	
//-------------------------------------------------------------------------------------------------------------
	
	
//
//	@PostMapping("/password")
//	public String changePassword(Model model,@RequestParam String cpassword, @RequestParam String npassword, @RequestParam String rpassword) {
//
//		if (!StringUtils.isEmpty(cpassword) && !StringUtils.isEmpty(npassword) && npassword.equals(rpassword) && passwordEncoder.matches(cpassword, usersService.loadUserByUsername("admin").getPassword()))
//			usersService.changePassword(cpassword, npassword);
//		return "redirect:/admin/index";
//	}

	

	@GetMapping("/card/{id}")
	public ResponseEntity<Optional<Card>> card(@PathVariable Long id) {
		return this.get(cardRepository, id);
	}

	

	
	
	

	

    
    
    
    //today set for a cardset
    
    //card ok
    //card wrong

    
	private <T> ResponseEntity<Optional<T>> get(JpaRepository<T, Long> repository,@PathVariable Long id) {
		return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
	}


}