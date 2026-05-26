package br.com.faculdade.catolica.livro_service.listener;

import br.com.faculdade.catolica.livro_service.config.RabbitMQConfig;
import br.com.faculdade.catolica.livro_service.event.ReservaCriadaEvent;
import br.com.faculdade.catolica.livro_service.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservaCriadaListener {

    private final LivroService livroService;

    @RabbitListener(queues = RabbitMQConfig.RESERVA_CRIADA_QUEUE)
    public void processarReservaCriada(ReservaCriadaEvent event) {
        livroService.marcarComoIndisponivel(event.getLivroId());
    }
}
