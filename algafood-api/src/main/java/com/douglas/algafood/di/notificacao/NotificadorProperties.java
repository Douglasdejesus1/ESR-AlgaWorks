package com.douglas.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("configuration.email")
public class NotificadorProperties {

	//Determine o host
	private String hostServidor;
	//Determine a porta que será usada
	private Integer portaServidor = 66;
	public String getHostServidor() {
		return hostServidor;
	}
	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}
	public Integer getPortaServidor() {
		return portaServidor;
	}
	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}


	

}
