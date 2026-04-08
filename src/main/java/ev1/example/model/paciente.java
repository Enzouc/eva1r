package ev1.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Genera getters, setters, toString, equals, hashCode y un constructor con los campos requeridos.

public class paciente {
    
    private int id;
    private String especialidad;

}
