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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("entities")
public class AdminController {


	
}
