package br.com.wsp.repository;

import java.math.BigDecimal;

public interface TicketMedioProjection {
    Long getId();
    String getNome();
    BigDecimal getTicketMedio();
}