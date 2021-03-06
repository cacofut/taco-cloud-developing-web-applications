package br.com.salao.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.salao.entity.Ingredient;
import br.com.salao.entity.Ingredient.Type;
import br.com.salao.entity.Taco;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla",  Type.WRAP),
			new Ingredient("GRBF", "Ground Beef", 	 Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", 		 Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", 		 Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", 		 Type.CHEESE),
			new Ingredient("JACK", "Monterrey",		 Type.CHEESE),
			new Ingredient("SLSA", "Salsa", 		 Type.SAUCE),
			new Ingredient("SRCR", "Sour cream", 	 Type.SAUCE)
			
		);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			List<Ingredient> list =	ingredients.stream()
				.filter( i -> i.getType().equals(type)).collect(Collectors.toList());
			model.addAttribute(type.toString().toLowerCase(), list);			
		}
		
		model.addAttribute("taco", new Taco());
		return "design";
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors) {
		if( errors.hasErrors() ) {
			log.info("processing design: " + errors);
			return "design";
		}
		// Save the taco design
		// We'll do this in chapter 33
		log.info("processing design: " + taco);
		return "redirect:/orders/current";
	}
}
