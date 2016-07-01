package br.edu.ifrs.canoas.lds.ifsfinally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrs.canoas.lds.ifsfinally.service.DoacaoService;

@Controller
public class HomeController {
	
	DoacaoService doacaoService;
	
	@Autowired
	public HomeController(DoacaoService doacaoService){
		this.doacaoService = doacaoService;
	}
	

	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("doacoes", doacaoService.listStatusDisponivel());
		return "index";
	}

}
