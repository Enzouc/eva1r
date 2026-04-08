package ev1.example.controller;

import ev1.example.model.SolicitudAtencion;
import ev1.example.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes")
public class SolicitudController {

    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    // GET /api/v1/solicitudes - Obtener todas
    @GetMapping
    public List<SolicitudAtencion> obtenerTodas() {
        return service.obtenerTodas();
    }

    // GET /api/v1/solicitudes/1 - Obtener por ID
    @GetMapping("/{id}")
    public SolicitudAtencion obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // POST /api/v1/solicitudes - Crear nueva solicitud
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudAtencion crear(@Valid @RequestBody SolicitudAtencion solicitud) {
        return service.crear(solicitud);
    }

    // PUT /api/v1/solicitudes/1 - Actualizar solicitud existente
    @PutMapping("/{id}")
    public SolicitudAtencion actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudAtencion solicitud) {
        return service.actualizar(id, solicitud);
    }

    // DELETE /api/v1/solicitudes/1 - Eliminar solicitud
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // GET /api/v1/solicitudes/especialidad/Cardiologia - Buscar por especialidad
    @GetMapping("/especialidad/{especialidad}")
    public List<SolicitudAtencion> buscarPorEspecialidad(@PathVariable String especialidad) {
        return service.buscarPorEspecialidad(especialidad);
    }

    // GET /api/v1/solicitudes/ordenadas/prioridad - Obtener ordenadas por prioridad
    @GetMapping("/ordenadas/prioridad")
    public List<SolicitudAtencion> obtenerOrdenadasPorPrioridad() {
        return service.obtenerOrdenadasPorPrioridad();
    }
}
