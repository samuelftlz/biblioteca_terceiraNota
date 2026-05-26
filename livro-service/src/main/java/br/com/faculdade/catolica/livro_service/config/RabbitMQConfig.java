package br.com.faculdade.catolica.livro_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    public static final String RESERVA_EXCHANGE = "biblioteca.reservas.exchange";
    public static final String RESERVA_CRIADA_QUEUE = "livro.reserva-criada.queue";
    public static final String RESERVA_CRIADA_ROUTING_KEY = "reserva.criada";

    @Bean
    public DirectExchange reservaExchange() {
        return new DirectExchange(RESERVA_EXCHANGE);
    }

    @Bean
    public Queue reservaCriadaQueue() {
        return QueueBuilder.durable(RESERVA_CRIADA_QUEUE).build();
    }

    @Bean
    public Binding reservaCriadaBinding(Queue reservaCriadaQueue, DirectExchange reservaExchange) {
        return BindingBuilder.bind(reservaCriadaQueue)
                .to(reservaExchange)
                .with(RESERVA_CRIADA_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter jsonMessageConverter
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter);
        return factory;
    }
}
