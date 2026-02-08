package sus.microservico.agendamento.sus_microservico_agendamento.event;

import sus.microservico.agendamento.sus_microservico_agendamento.model.enums.StatusCirurgia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CirurgiaAgendadaEvent(
        UUID pacienteId,
        UUID medicoId,
        LocalDate dataCirurgia,
        LocalTime horaCirurgia,
        String local,
        String descricao,
        StatusCirurgia status
) implements Serializable {
}
