package com.douglas.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String nome;

	@ManyToOne
	private Estado estado;
}
