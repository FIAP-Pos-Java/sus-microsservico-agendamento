package sus.microservico.agendamento.sus_microservico_agendamento.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);
    
    public static final String EXCHANGE = "sus.exchange";
    
    // Filas do agendamento
    public static final String CIRURGIA_CRIADA_QUEUE = "cirurgia.criada.queue";
    public static final String CIRURGIA_ATUALIZADA_QUEUE = "cirurgia.atualizada.queue";
    public static final String CIRURGIA_CANCELADA_QUEUE = "cirurgia.cancelada.queue";
    
    public static final String CIRURGIA_CRIADA_ROUTING_KEY = "cirurgia.criada";
    public static final String CIRURGIA_ATUALIZADA_ROUTING_KEY = "cirurgia.atualizada";
    public static final String CIRURGIA_CANCELADA_ROUTING_KEY = "cirurgia.cancelada";
    
    // Filas de notificação
    public static final String NOTIFICACAO_CIRURGIA_CRIADA_ROUTING_KEY = "notificacao.cirurgia.criada";
    public static final String NOTIFICACAO_CIRURGIA_ATUALIZADA_ROUTING_KEY = "notificacao.cirurgia.atualizada";
    public static final String NOTIFICACAO_CIRURGIA_CANCELADA_ROUTING_KEY = "notificacao.cirurgia.cancelada";
    
    @PostConstruct
    public void init() {
        logger.info("==========================================================");
        logger.info(" RABBITMQ CONFIG - MICROSERVIÇO AGENDAMENTO");
        logger.info("==========================================================");
        logger.info("Filas que este serviço ESCUTA:");
        logger.info("  • {} -> {}", CIRURGIA_CRIADA_QUEUE, CIRURGIA_CRIADA_ROUTING_KEY);
        logger.info("  • {} -> {}", CIRURGIA_ATUALIZADA_QUEUE, CIRURGIA_ATUALIZADA_ROUTING_KEY);
        logger.info("  • {} -> {}", CIRURGIA_CANCELADA_QUEUE, CIRURGIA_CANCELADA_ROUTING_KEY);
        logger.info("==========================================================");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue cirurgiaCriadaQueue() {
        return new Queue(CIRURGIA_CRIADA_QUEUE, true);
    }

    @Bean
    public Binding cirurgiaCriadaBinding() {
        return BindingBuilder.bind(cirurgiaCriadaQueue())
                .to(exchange())
                .with(CIRURGIA_CRIADA_ROUTING_KEY);
    }

    @Bean
    public Queue cirurgiaAtualizadaQueue() {
        return new Queue(CIRURGIA_ATUALIZADA_QUEUE, true);
    }

    @Bean
    public Binding cirurgiaAtualizadaBinding() {
        return BindingBuilder.bind(cirurgiaAtualizadaQueue())
                .to(exchange())
                .with(CIRURGIA_ATUALIZADA_ROUTING_KEY);
    }

    @Bean
    public Queue cirurgiaCanceladaQueue() {
        return new Queue(CIRURGIA_CANCELADA_QUEUE, true);
    }

    @Bean
    public Binding cirurgiaCanceladaBinding() {
        return BindingBuilder.bind(cirurgiaCanceladaQueue())
                .to(exchange())
                .with(CIRURGIA_CANCELADA_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
