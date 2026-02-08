package sus.microservico.agendamento.sus_microservico_agendamento.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sus.microservico.agendamento.sus_microservico_agendamento.config.RabbitMQConfig;
import sus.microservico.agendamento.sus_microservico_agendamento.event.CirurgiaAtualizadaEvent;
import sus.microservico.agendamento.sus_microservico_agendamento.event.CirurgiaCanceladaEvent;
import sus.microservico.agendamento.sus_microservico_agendamento.event.CirurgiaCriadaEvent;
import sus.microservico.agendamento.sus_microservico_agendamento.model.Cirurgia;
import sus.microservico.agendamento.sus_microservico_agendamento.repository.CirurgiaRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CirurgiaAgendadaConsumer {
    
    private final Logger logger = LoggerFactory.getLogger(CirurgiaAgendadaConsumer.class);
    private final CirurgiaRepository cirurgiaRepository;

    @RabbitListener(queues = RabbitMQConfig.CIRURGIA_CRIADA_QUEUE)
    public void receberCirurgiaCriada(CirurgiaCriadaEvent evento) {
        logger.info("Evento de criação recebido para paciente: {}", evento.pacienteId());
        
        Cirurgia cirurgia = new Cirurgia();
        cirurgia.setPacienteId(evento.pacienteId());
        cirurgia.setMedicoId(evento.medicoId());
        cirurgia.setDataCirurgia(evento.dataCirurgia());
        cirurgia.setHoraCirurgia(evento.horaCirurgia());
        cirurgia.setLocal(evento.local());
        cirurgia.setDescricao(evento.descricao());
        cirurgia.setStatus(evento.status());
        cirurgia.setDataAgendamento(LocalDateTime.now());
        cirurgia.setDataRecebimento(LocalDateTime.now());
        
        Cirurgia cirurgiaCriada = cirurgiaRepository.save(cirurgia);
        logger.info("Cirurgia {} criada com sucesso", cirurgiaCriada.getId());
    }

    @RabbitListener(queues = RabbitMQConfig.CIRURGIA_ATUALIZADA_QUEUE)
    public void receberCirurgiaAtualizada(CirurgiaAtualizadaEvent evento) {
        logger.info("Evento de atualização recebido para cirurgia: {}", evento.cirurgiaId());
        
        cirurgiaRepository.findById(evento.cirurgiaId()).ifPresentOrElse(
            cirurgia -> {
                cirurgia.setPacienteId(evento.pacienteId());
                cirurgia.setMedicoId(evento.medicoId());
                cirurgia.setDataCirurgia(evento.dataCirurgia());
                cirurgia.setHoraCirurgia(evento.horaCirurgia());
                cirurgia.setLocal(evento.local());
                cirurgia.setDescricao(evento.descricao());
                cirurgia.setStatus(evento.status());
                cirurgia.setDataRecebimento(LocalDateTime.now());
                
                cirurgiaRepository.save(cirurgia);
                logger.info("Cirurgia {} atualizada com sucesso", cirurgia.getId());
            },
            () -> logger.warn("Cirurgia {} não encontrada", evento.cirurgiaId())
        );
    }

    @RabbitListener(queues = RabbitMQConfig.CIRURGIA_CANCELADA_QUEUE)
    public void receberCirurgiaCancelada(CirurgiaCanceladaEvent evento) {
        logger.info("Evento de cancelamento recebido para cirurgia: {}", evento.cirurgiaId());
        
        cirurgiaRepository.findById(evento.cirurgiaId()).ifPresentOrElse(
            cirurgia -> {
                cirurgiaRepository.delete(cirurgia);
                logger.info("Cirurgia {} deletada com sucesso", evento.cirurgiaId());
            },
            () -> logger.warn("Cirurgia {} não encontrada", evento.cirurgiaId())
        );
    }
}
