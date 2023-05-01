
package Entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Usuario {
    
    
    private Integer id;
    private String cpf;
    private String nome;
    private String username;
    private String senha;
    private Perfil perfil;
    private String telefone;
    private String matricula;
    private Date dataNascimento;
    
}
