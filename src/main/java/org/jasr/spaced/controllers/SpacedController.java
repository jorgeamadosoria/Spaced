package org.jasr.spaced.controllers;

import org.jasr.spaced.repositories.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpacedController {
	@Autowired
    private CardSetRepository cardSetRepository;
	
	
    @GetMapping(value= {"/","/index"})
    public String index(Model model) {
    	model.addAttribute("cardsets", cardSetRepository.findAll());
        return "index";
    }
    
    
    @GetMapping(value= {"/cardset"})
    public String cardset(Model model,Long id) {
    	model.addAttribute("cardset", cardSetRepository.getOne(id));
        return "cardset";
    }
    
    //today set for a cardset
    //list of cards for a cardset
    //card ok
    //card wrong

}