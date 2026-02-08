package sus.microservico.agendamento.sus_microservico_agendamento.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    public static final String CIRURGIA_CRIADA_QUEUE = "cirurgia.criada.queue";
    public static final String CIRURGIA_ATUALIZADA_QUEUE = "cirurgia.atualizada.queue";
    public static final String CIRURGIA_CANCELADA_QUEUE = "cirurgia.cancelada.queue";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
