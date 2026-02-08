package sus.microservico.agendamento.sus_microservico_agendamento.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sus.microservico.agendamento.sus_microservico_agendamento.event.CirurgiaAgendadaEvent;
import sus.microservico.agendamento.sus_microservico_agendamento.model.Cirurgia;
import sus.microservico.agendamento.sus_microservico_agendamento.repository.CirurgiaRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CirurgiaAgendadaConsumer {
    
    private final Logger logger = LoggerFactory.getLogger(CirurgiaAgendadaConsumer.class);
    private final CirurgiaRepository cirurgiaRepository;

    @RabbitListener(queues = "cirurgia.agendada.queue")
    public void receberCirurgiaAgendada(CirurgiaAgendadaEvent evento) {
        logger.info("Evento de cirurgia recebido para paciente: {}", evento.pacienteId());
        
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
        
        Cirurgia salva = cirurgiaRepository.save(cirurgia);
        logger.info("Cirurgia {} persistida com sucesso", salva.getId());
    }
}
