package sus.microservico.agendamento.sus_microservico_agendamento.event;

import java.io.Serializable;
import java.util.UUID;

public record CirurgiaCanceladaEvent(
        UUID cirurgiaId
) implements Serializable {
}
