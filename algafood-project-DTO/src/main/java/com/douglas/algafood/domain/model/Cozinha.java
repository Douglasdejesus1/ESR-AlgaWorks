package com.douglas.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.douglas.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;
//@JsonRootName("Origem")

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@NotNull(groups = Groups.CozinhaId.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore
	@NotBlank
	//@JsonProperty("titulo")
	@Column(nullable = false)
	private String nome;
	
	
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante>restaurantes = new ArrayList<>();


}