package br.com.faculdade.catolica.reserva_service.service;

import br.com.faculdade.catolica.reserva_service.client.LivroClient;
import br.com.faculdade.catolica.reserva_service.config.RabbitMQConfig;
import br.com.faculdade.catolica.reserva_service.dto.LivroDTO;
import br.com.faculdade.catolica.reserva_service.dto.ReservaDTO;
import br.com.faculdade.catolica.reserva_service.event.ReservaCriadaEvent;
import br.com.faculdade.catolica.reserva_service.model.Reserva;
import br.com.faculdade.catolica.reserva_service.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository repository;
    private final LivroClient livroClient;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public Reserva criarReserva(ReservaDTO dto) {

        LivroDTO livro = livroClient.buscarLivroPorId(dto.getLivroId());

        if (livro == null) {
            throw new RuntimeException("Livro não encontrado no sistema!");
        }

        if (!livro.isDisponivel()) {
            throw new RuntimeException("O livro '" + livro.getTitulo() + "' não está disponível no momento.");
        }

        Reserva reserva = Reserva.builder()
                .livroId(dto.getLivroId())
                .nomeUsuario(dto.getNomeUsuario())
                .build();

        Reserva reservaSalva = repository.save(reserva);

        ReservaCriadaEvent evento = ReservaCriadaEvent.builder()
                .reservaId(reservaSalva.getId())
                .livroId(reservaSalva.getLivroId())
                .nomeUsuario(reservaSalva.getNomeUsuario())
                .dataReserva(reservaSalva.getDataReserva().toString())
                .build();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.RESERVA_EXCHANGE,
                RabbitMQConfig.RESERVA_CRIADA_ROUTING_KEY,
                evento
        );

        return reservaSalva;
    }
}
