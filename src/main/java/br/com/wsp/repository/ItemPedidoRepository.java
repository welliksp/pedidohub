package br.com.wsp.repository;

import br.com.wsp.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {

}
