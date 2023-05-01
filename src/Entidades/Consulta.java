
package Entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Consulta {
    
    private Integer id;
    private Plano plano;
    private Paciente paciente;
    private Medico medico;
    private String sala;
    private Date dataDaConsulta;
    private String horaDaConsulta;
}
