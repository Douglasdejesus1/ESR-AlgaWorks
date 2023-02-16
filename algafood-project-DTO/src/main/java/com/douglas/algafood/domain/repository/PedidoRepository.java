package com.douglas.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.douglas.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>{	
}
