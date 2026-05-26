package br.com.faculdade.catolica.reserva_service.dto;

import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    private String titulo;
    private boolean disponivel;
}
