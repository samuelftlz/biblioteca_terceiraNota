package br.com.faculdade.catolica.reserva_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaCriadaEvent {

    private Long reservaId;
    private Long livroId;
    private String nomeUsuario;
    private String dataReserva;
}
