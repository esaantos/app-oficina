package com.AppOficina.AppOficina.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppOficina.AppOficina.models.Cliente;
import com.AppOficina.AppOficina.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository cr;
	
	//Get que chama o form para cadastrar clientes
	@RequestMapping("/cadastrarCliente")
	public String form() {
		return "cliente/form-cliente";
	}
	
	//POST que cadastra clientes
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public String form(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarCliente";
		}

		cr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
		return "redirect:/cadastrarCliente";
	
	}
	
	//GET que lista clientes
	@RequestMapping("/clientes")
	public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("cliente/lista-cliente");
		Iterable<Cliente> clientes = cr.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	//GET que deleta cliente
	@RequestMapping("/deletarCliente")
	public String deletarCliente(long id) {
		Cliente cliente = cr.findById(id);
		cr.delete(cliente);
		return "redirect:/clientes";
		
	}
	
	// Métodos que atualizam cliente
	// GET que chama o FORM de edição do cliente
	@RequestMapping("/editar-cliente")
	public ModelAndView editarCliente(long id) {
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("cliente/update-cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	// POST que atualiza o cliente
	@RequestMapping(value = "/editar-cliente", method = RequestMethod.POST)
	public String updateCliente(@Valid Cliente cliente,  BindingResult result, RedirectAttributes attributes){
		
		cr.save(cliente);
		attributes.addFlashAttribute("success", "Cliente alterado com sucesso!");
		return "redirect:/clientes";
		
	}
	
}
