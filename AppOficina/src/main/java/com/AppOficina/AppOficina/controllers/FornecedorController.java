package com.AppOficina.AppOficina.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppOficina.AppOficina.models.Fornecedor;
import com.AppOficina.AppOficina.repository.FornecedorRepository;

@Controller
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository fre;
	
	
	//GET que chama o form para cadastrar fornecedores
	@RequestMapping("/cadastrarFornecedor")
	public String form() {
		return "fornecedor/form-fornecedor";
	}
	
	//POST que cadastra fornecedores
	@RequestMapping(value = "/cadastrarFornecedor", method = RequestMethod.POST)
	public String form(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarFornecedor";
		}

		fre.save(fornecedor);
		attributes.addFlashAttribute("mensagem", "Fornecedor cadastrado com sucesso!");
		return "redirect:/cadastrarFornecedor";
	}
	
	//GET que lista fornecedores
	@RequestMapping("/fornecedores")
	public ModelAndView listaFornecedores() {
		ModelAndView mv = new ModelAndView("fornecedor/lista-fornecedor");
		Iterable<Fornecedor> fornecedores = fre.findAll();
		mv.addObject("fornecedores", fornecedores);
		return mv;
	}
	
	//GET que deleta cliente
	@RequestMapping("/deletarFornecedor")
	public String deletarFornecedor(long id) {
		Fornecedor fornecedor = fre.findById(id);
		fre.delete(fornecedor);
		return "redirect:/fornecedores";
		
	}
	// GET que chama o FORM de edição do cliente
	@RequestMapping("/editar-fornecedor")
	public ModelAndView editarFornecedor(long id) {
		Fornecedor fornecedor = fre.findById(id);
		ModelAndView mv = new ModelAndView("fornecedor/update-fornecedor");
		mv.addObject("fornecedor", fornecedor);
		return mv;
	}
	
	// POST que atualiza o cliente
	@RequestMapping(value = "/editar-fornecedor", method = RequestMethod.POST)
	public String updateFornecedor(@Valid Fornecedor fornecedor,  BindingResult result, RedirectAttributes attributes){
		
		fre.save(fornecedor);
		attributes.addFlashAttribute("success", "Fornecedor alterado com sucesso!");
		return "redirect:/fornecedores";
	}
}
