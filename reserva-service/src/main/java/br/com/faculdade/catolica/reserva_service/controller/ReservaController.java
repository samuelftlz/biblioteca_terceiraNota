package br.com.faculdade.catolica.reserva_service.controller;

import br.com.faculdade.catolica.reserva_service.dto.ReservaDTO;
import br.com.faculdade.catolica.reserva_service.model.Reserva;
import br.com.faculdade.catolica.reserva_service.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService service;

    @PostMapping("/solicitar")
    public ResponseEntity<Reserva> reservar(@RequestBody ReservaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarReserva(dto));
    }
}