package com.douglas.algafood.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.di.modelo.Cliente;
import com.douglas.algafood.di.notificacao.NivelUrgencia;
import com.douglas.algafood.di.notificacao.Notificador;
import com.douglas.algafood.di.notificacao.TipoNotificador;

@Component
public class AtivacaoClienteService {
//public class AtivacaoClienteService implements InitializingBean, DisposableBean  {	
	@TipoNotificador(NivelUrgencia.PRIORITARIO)
	@Autowired
	private Notificador notificador;
	
	@PostConstruct
	void iniciar() {
		System.out.println("INICIADO");
	}
	
	@PreDestroy
	void destroi() {
		System.out.println("TERMINADO");
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		
		notificador.notificar(cliente, "Seu cadastro está ativo");
		}

	/*@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("INICIADO");	
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("TERMINADO");
		
	}*/
	

	
}
