package br.com.wsp.repository;

import br.com.wsp.entity.Categoria;
import br.com.wsp.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    Page<Pedido> findAll(Pageable pageable);

}
