package sus.microservico.agendamento.sus_microservico_agendamento.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sus.microservico.agendamento.sus_microservico_agendamento.controller.dto.BuscarCirurgiaDTO;
import sus.microservico.agendamento.sus_microservico_agendamento.service.CirurgiaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cirurgias")
@RequiredArgsConstructor
public class CirurgiaController {

    private final Logger logger = LoggerFactory.getLogger(CirurgiaController.class);
    private final CirurgiaService cirurgiaService;

    @GetMapping
    public ResponseEntity<Page<BuscarCirurgiaDTO>> buscarTodasCirurgias(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        this.logger.info("GET -> /api/v1/cirurgias?page={}&size={}", page, size);
        Page<BuscarCirurgiaDTO> cirurgias = this.cirurgiaService.buscarTodasCirurgias(page, size);
        return ResponseEntity.ok(cirurgias);
    }

    @GetMapping("{id}")
    public ResponseEntity<BuscarCirurgiaDTO> buscarCirurgiaPorId(@PathVariable String id) {
        this.logger.info("GET -> /api/v1/cirurgias/{}", id);
        BuscarCirurgiaDTO cirurgia = this.cirurgiaService.buscarCirurgiaPorId(id);
        return ResponseEntity.ok(cirurgia);
    }

    
    @GetMapping("paciente/{pacienteId}")
    public ResponseEntity<List<BuscarCirurgiaDTO>> buscarCirurgiasPorPaciente(@PathVariable String pacienteId) {
        this.logger.info("GET -> /api/v1/cirurgias/paciente/{}", pacienteId);
        UUID uuid = UUID.fromString(pacienteId);
        List<BuscarCirurgiaDTO> cirurgias = this.cirurgiaService.buscarCirurgiasPorPaciente(uuid);
        return ResponseEntity.ok(cirurgias);
    }
}
