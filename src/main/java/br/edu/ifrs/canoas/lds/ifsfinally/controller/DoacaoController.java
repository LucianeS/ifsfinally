package br.edu.ifrs.canoas.lds.ifsfinally.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrs.canoas.lds.ifsfinally.domain.Doacao;
import br.edu.ifrs.canoas.lds.ifsfinally.service.DoacaoService;
import br.edu.ifrs.canoas.lds.ifsfinally.service.UserProfileService;

@Controller
@RequestMapping("/doacao")
public class DoacaoController {
	
	private DoacaoService doacaoService;
	private MessageSource messageSource;
	private UserProfileService userProfileService;
	
	@Autowired
	public DoacaoController(DoacaoService doacaoService, MessageSource messageSource,UserProfileService userProfileService) {
		
		this.doacaoService = doacaoService;
		this.messageSource = messageSource;
		this.userProfileService= userProfileService;
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("doacoes", doacaoService.listIsDisponivel());
		return "/doacao/list";
	}
	
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("doacao", new Doacao());
		model.addAttribute("readonly", false);
		return "/doacao/form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Doacao doacao, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs,
			Locale locale) {
		if (!bindingResult.hasErrors()) {
			if(!doacao.getIsDisponivel())
				doacao.setIsDisponivel(true);
			
			doacao.setPostedOn(new Date());
			doacao.setResponsible(userProfileService.getPrincipal().getUser());
			
			Doacao savedDoacao = doacaoService.save(doacao);
			redirectAttrs.addFlashAttribute("message", messageSource.getMessage("post.saved", null, locale));
			return "redirect:/post/view/" + savedDoacao.getId() + "?success";
		}
		model.addAttribute("readonly", false);
		return "/doacao/form";
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs, Locale locale) {
		Doacao doacao = doacaoService.get(id);

		if (doacao == null) {
			redirectAttrs.addFlashAttribute("message",
					MessageFormat.format(messageSource.getMessage("doacao.notFound", null, locale), id));
			return "redirect:/doacao/list";
		}

		model.addAttribute("doacao", doacaoService.get(id));
		model.addAttribute("readonly", true);

		return "/doacao/form";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs, Locale locale) {

		Doacao doacao = doacaoService.get(id);

		if (doacao == null) {
			redirectAttrs.addFlashAttribute("message",
					MessageFormat.format(messageSource.getMessage("doacao.deleted.failed", null, locale), null));

		} else {
			
			doacaoService.delete(id);

			redirectAttrs.addFlashAttribute("message", MessageFormat
					.format(messageSource.getMessage("doacao.deleted", null, locale), doacao.getTitle()));
		}

		return "redirect:/doacao/list";

	}
	
	@Secured("ROLE_USER")
	@RequestMapping("/edit/{id}")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("doacao", doacaoService.get(id));
		model.addAttribute("readonly", false);
		return "/doacao/form";
	}

}
