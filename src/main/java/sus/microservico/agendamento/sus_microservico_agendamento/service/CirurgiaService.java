package sus.microservico.agendamento.sus_microservico_agendamento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sus.microservico.agendamento.sus_microservico_agendamento.controller.dto.BuscarCirurgiaDTO;
import sus.microservico.agendamento.sus_microservico_agendamento.model.Cirurgia;
import sus.microservico.agendamento.sus_microservico_agendamento.repository.CirurgiaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CirurgiaService {

    private final CirurgiaRepository cirurgiaRepository;

    public Page<BuscarCirurgiaDTO> buscarTodasCirurgias(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataAgendamento").descending());
        Page<Cirurgia> cirurgias = this.cirurgiaRepository.findAll(pageable);
        return cirurgias.map(this::mapToDTO);
    }

    public BuscarCirurgiaDTO buscarCirurgiaPorId(String id) {
        UUID uuid = UUID.fromString(id);
        Cirurgia cirurgia = this.cirurgiaRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Cirurgia não encontrada"));
        return mapToDTO(cirurgia);
    }

    public List<BuscarCirurgiaDTO> buscarCirurgiasPorPaciente(UUID pacienteId) {
        List<Cirurgia> cirurgias = this.cirurgiaRepository.findByPacienteIdOrderByDataCirurgiaDesc(pacienteId);
        return cirurgias.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BuscarCirurgiaDTO mapToDTO(Cirurgia cirurgia) {
        return new BuscarCirurgiaDTO(
                cirurgia.getId(),
                cirurgia.getPacienteId(),
                cirurgia.getMedicoId(),
                cirurgia.getDataCirurgia(),
                cirurgia.getHoraCirurgia(),
                cirurgia.getLocal(),
                cirurgia.getDescricao(),
                cirurgia.getStatus(),
                cirurgia.getDataAgendamento()
        );
    }
}
