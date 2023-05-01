
package Entidades;

import java.time.LocalDate;
import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Medico {
    private Integer crm;
    private String nome;
    private String especialidade;
    private String sexo;
    private Date dataNascimento;
    private Double salario;

    public Object getDataDeNascimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
}
