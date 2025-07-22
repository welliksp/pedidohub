package br.com.wsp.repository;

import br.com.wsp.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    Page<Pedido> findAll(Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.user.id = :userId")
    List<Pedido> findByUser(Long userId);

    @Query(value = """
                SELECT u.id, u.first_name AS nome, COUNT(p.id) AS totalPedidos
                FROM tb_user u
                JOIN tb_pedido p ON p.user_id = u.id
                GROUP BY u.id, u.first_name
                ORDER BY totalPedidos DESC
                LIMIT 5
            """, nativeQuery = true)
    List<TopUsuarioProjection> findTop5UsuariosMaisCompraram();

    @Query(value = """
                SELECT u.id, u.first_name AS nome, AVG(p.total) AS ticketMedio
                FROM tb_user u
                JOIN tb_pedido p ON p.user_id = u.id
                GROUP BY u.id, u.first_name
            """, nativeQuery = true)
    List<TicketMedioProjection> calcularTicketMedioPorUsuario();

    @Query(value = """
                SELECT COALESCE(SUM(p.total), 0)
                FROM tb_pedido p
                WHERE MONTH(p.data_criacao) = MONTH(CURRENT_DATE())
                  AND YEAR(p.data_criacao) = YEAR(CURRENT_DATE())
            """, nativeQuery = true)
    BigDecimal calcularFaturamentoMesAtual();
}
