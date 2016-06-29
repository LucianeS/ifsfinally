package br.edu.ifrs.canoas.lds.ifsfinally.controller;

import javax.servlet.http.HttpSession;

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
		model.addAttribute("doacoes", doacaoService.listIsDisponivel());
		return "index";
	}

}
