package ev1.example.service;

import ev1.example.model.SolicitudAtencion;
import ev1.example.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudService {

    private final SolicitudRepository repository;

    public SolicitudService(SolicitudRepository repository) {
        this.repository = repository;
    }

    public List<SolicitudAtencion> obtenerTodas() {
        return repository.findAll();
    }

    public SolicitudAtencion obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + id));
    }

    public SolicitudAtencion crear(SolicitudAtencion solicitud) {
        solicitud.setFechaRegistro(LocalDateTime.now());
        if (solicitud.getEstado() == null || solicitud.getEstado().isBlank()) {
            solicitud.setEstado("Pendiente");
        }
        return repository.save(solicitud);
    }

    public SolicitudAtencion actualizar(Long id, SolicitudAtencion solicitud) {
        SolicitudAtencion existente = obtenerPorId(id);
        
        existente.setNombrePaciente(solicitud.getNombrePaciente());
        existente.setRutPaciente(solicitud.getRutPaciente());
        existente.setEspecialidad(solicitud.getEspecialidad());
        existente.setEstado(solicitud.getEstado());
        existente.setPrioridad(solicitud.getPrioridad());
        
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Solicitud no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    public List<SolicitudAtencion> buscarPorEspecialidad(String especialidad) {
        return repository.findAll().stream()
                .filter(s -> s.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }

    public List<SolicitudAtencion> obtenerOrdenadasPorPrioridad() {
        return repository.findAll().stream()
                .sorted(java.util.Comparator.comparingInt(SolicitudAtencion::getPrioridad))
                .collect(Collectors.toList());
    }
}
