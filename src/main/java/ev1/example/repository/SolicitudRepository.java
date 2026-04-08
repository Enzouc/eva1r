package ev1.example.repository;

import ev1.example.model.SolicitudAtencion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SolicitudRepository {

    private final List<SolicitudAtencion> solicitudes = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(1);

    public List<SolicitudAtencion> findAll() {
        return new ArrayList<>(solicitudes);
    }

    public Optional<SolicitudAtencion> findById(Long id) {
        return solicitudes.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public SolicitudAtencion save(SolicitudAtencion solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(currentId.getAndIncrement());
            solicitudes.add(solicitud);
        } else {

            findById(solicitud.getId()).ifPresent(s -> {
                int index = solicitudes.indexOf(s);
                solicitudes.set(index, solicitud);
            });
        }
        return solicitud;
    }

    public void deleteById(Long id) {
        solicitudes.removeIf(s -> s.getId().equals(id));
    }

    public boolean existsById(Long id) {
        return solicitudes.stream().anyMatch(s -> s.getId().equals(id));
    }
}
