package org.jasr.spaced.controllers;

import java.util.Optional;

import org.jasr.spaced.entities.CardSet;
import org.jasr.spaced.repositories.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SpacedController {
	@Autowired
    private CardSetRepository cardSetRepository;

	@Value("${angcv.export.url}")
	private String jsonUrl;
	
    @GetMapping("/index")
    public String index(Model model) {
    	model.addAttribute("cardsets", cardSetRepository.findAll());
        return "index";
    }
    /*
    @GetMapping("/about")
    public String about(Model model) {
    }
    */
    @GetMapping("/cardSet/{id}")
    public ResponseEntity<Optional<CardSet>> project(@PathVariable Long id) {
        return new ResponseEntity<>(cardSetRepository.findById(id),HttpStatus.OK);
    }
}