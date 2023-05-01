package Entidades;
import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Paciente {
    
    private Integer id; //incremental ao banco de dados
    private String cpf;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String endereco;
    private String sexo;

    public void setSexo(char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDataNascimento(Date parse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
