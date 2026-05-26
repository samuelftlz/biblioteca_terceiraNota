package br.com.faculdade.catolica.livro_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaCriadaEvent {

    private Long reservaId;
    private Long livroId;
    private String nomeUsuario;
    private String dataReserva;
}
