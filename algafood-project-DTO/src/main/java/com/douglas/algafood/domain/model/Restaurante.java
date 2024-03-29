package com.douglas.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.douglas.algafood.core.validation.Groups;
import com.douglas.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ValorZeroIncluiDescricao(valorField="taxaFrete", descricaoField="nome", descricaoObrigatoria = "Frete Grátis")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	//@PositiveOrZero uso no lugar, a titulo de exemplo:
	//@NotNull
	//@PositiveOrZero
	//@TaxaFrete
	//@Multiplo(numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	//OnDelete permite excluir classes associadas a outras classes
	//@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore //para listar os restaurantes vinculados, tiro o ignore da cozinha e mando pra ca
	//@JsonIgnoreProperties("hibernateLazyInitializer")
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull
	@ManyToOne //(fetch=FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	private Boolean ativo = Boolean.TRUE;
	
	@Embedded
	private Endereco endereco;
	
	
	@CreationTimestamp
	@Column(nullable=false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;	
	@UpdateTimestamp
	@Column(nullable=false,columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@ManyToMany
	@JoinTable(name= "restaurante_forma_pagamento",
	        joinColumns = @JoinColumn(name = "restaurante_id"),
	        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@OneToMany(mappedBy="restaurante")
	private List<Produto>produtos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="restaurante_usuario_responsavel",
	joinColumns= @JoinColumn(name="restaurante_id"),
	inverseJoinColumns =@JoinColumn(name="usuario_id"))
	private List<Usuario>usuarios = new ArrayList<>();
	
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	private boolean aberto = Boolean.FALSE;
	
	public void abrir() {
		setAberto(true);
	}
	public void fechar() {
		setAberto(false);
	}
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}
	public boolean desassociarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}
	
	public boolean adicionarResponsavel(Usuario usuario) {
		return getUsuarios().add(usuario);
	}
	public boolean desassociarResponsavel(Usuario usuario) {
		return getUsuarios().remove(usuario);
	}
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().contains(formaPagamento);
	}
	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaPagamento(formaPagamento);
	}
	

}

