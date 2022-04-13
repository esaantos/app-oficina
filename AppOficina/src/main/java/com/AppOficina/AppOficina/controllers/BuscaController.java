package com.AppOficina.AppOficina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppOficina.AppOficina.repository.ClienteRepository;

@Controller
public class BuscaController {

	@Autowired
	private ClienteRepository cr;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}	
	
	//GET
	@RequestMapping("/")
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	//POST
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {
		
		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;
		
		if(nome.equals("nomecliente")) {
			mv.addObject("clientes", cr.findByNomes(buscar));
			
		}else {
			mv.addObject("clientes", cr.findByNomes(buscar));
		}
		
		mv.addObject("mensagem", mensagem);
		
		return mv;
	}
	
}
