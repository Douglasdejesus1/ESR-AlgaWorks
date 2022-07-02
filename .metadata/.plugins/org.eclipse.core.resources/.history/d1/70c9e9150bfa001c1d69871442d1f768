package com.douglas.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douglas.algafood.di.modelo.Cliente;
import com.douglas.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroControler {
	private AtivacaoClienteService ativacaoClienteService;
	
	
	
	
	
	
	public MeuPrimeiroControler(AtivacaoClienteService ativacaoClienteService) {
		super();
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("MeuPrimeiroControler: "+ativacaoClienteService);
	}






	@GetMapping("/hello")
	@ResponseBody
	public String helo() {
		Cliente cliente = new Cliente("Douglas", "douglas@email.com", "1195486");
		ativacaoClienteService.ativar(cliente);
		
				
		return cliente;
	}

}
