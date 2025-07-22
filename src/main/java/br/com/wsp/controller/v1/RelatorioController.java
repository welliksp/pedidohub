package br.com.wsp.controller.v1;

import br.com.wsp.repository.PedidoRepository;
import br.com.wsp.repository.TicketMedioProjection;
import br.com.wsp.repository.TopUsuarioProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/relatorio")
public class RelatorioController {

    private final PedidoRepository pedidoRepository;

    public RelatorioController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/top-clientes")
    public List<TopUsuarioProjection> topClientes() {
        return pedidoRepository.findTop5UsuariosMaisCompraram();
    }

    @GetMapping("/ticket-medio")
    public List<TicketMedioProjection> ticketMedio() {
        return pedidoRepository.calcularTicketMedioPorUsuario();
    }

    @GetMapping("/faturamento-mensal")
    public ResponseEntity<BigDecimal> faturamentoMensal() {
        return ResponseEntity.ok(pedidoRepository.calcularFaturamentoMesAtual());
    }
}
