package ev1.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAtencion {
    
    private Long id;

    @NotBlank(message = "El nombre del paciente es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombrePaciente;

    @NotBlank(message = "El rut del paciente es obligatorio")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9Kk]{1}$", message = "El RUT debe tener un formato válido (ej: 12345678-K)")
    private String rutPaciente;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @Pattern(regexp = "Pendiente|Atendida|Cancelada", message = "El estado debe ser Pendiente, Atendida o Cancelada")
    private String estado; 

    private LocalDateTime fechaRegistro;

    @NotNull(message = "La prioridad es obligatoria")
    @jakarta.validation.constraints.Min(value = 1, message = "La prioridad debe ser 1 (Alta), 2 (Media) o 3 (Baja)")
    @jakarta.validation.constraints.Max(value = 3, message = "La prioridad debe ser 1 (Alta), 2 (Media) o 3 (Baja)")
    private Integer prioridad; 
}
