package Controller;

import Entidades.Medico;
import Persistencia.MedicoDAO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import javax.swing.JOptionPane;
import lombok.Getter;

/**
 *
 * @author ivanoliveira
 */
public class MedicoController {

    private MedicoDAO daoMedico;
    private StringBuilder mensagensDeErro;
    private SimpleDateFormat sdf;
    @Getter
    private Collection<Medico> listaDeMedicos;
    private boolean camposValidos;

    //MÉTODO CONSTRUTOR
    public MedicoController() {

        setUp();
    }

    // Método de configuração inicial e instanciação dos objetos necessários
    private void setUp() {

        mensagensDeErro = new StringBuilder();
        daoMedico = new MedicoDAO();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        camposValidos = false;
        listaDeMedicos = daoMedico.listarTodos();

    }

    public void cadastrarMedico(Medico medico) {

        if (validarCampos(medico)) {
            daoMedico.salvar(medico);
        }
    }

    public void alterarMedico(Medico medico) {

        if (validarCampos(medico)) {
            daoMedico.update(medico);
        }

    }

    public Medico buscarPorId(int crm) {

        /*
         verifica se o id foi informado 
         */
        if (crm > 0) {

            return daoMedico.buscarPorId(crm);

        }

        JOptionPane.showMessageDialog(null, "Médico inexistente");
        return null;
    }

    public void excluirUsuario(int id) {

        daoMedico.deletar(daoMedico.buscarPorId(id));

        if (daoMedico.buscarPorId(id).getNome() == null) {

            JOptionPane.showMessageDialog(null, "Médico excluído  com sucesso");
        }
    }

    private boolean validarCampos(Medico medico) {

        camposValidos = true;

        // REGRA 01 - O CRM DO MÉDICO TEM QUE SER MAIOR QUE 0 
        if (medico.getCrm() > 0) {
            mensagensDeErro.append("O CRM DO MÉDICO TEM QUE SER MAIOR QUE ZERO! \n");
            camposValidos = false;
        }

        //  REGRA 02 - NOME NÃO PODE SER NULO
        if (medico.getNome() == null
                || medico.getNome().equals("")
                || medico.getNome().isEmpty()) {
            mensagensDeErro.append("O campo nome deve ser preenchido! \n");
            camposValidos = false;
        }

        // REGRA 03 - NOME NÃO PODE TER MAIS DE 80 CARACTERES
        if (medico.getNome().length() > 80) {

            mensagensDeErro.append("O nome deve ter no máximo 80 caracteres \n");
            camposValidos = false;
        }

        // REGRA 04 - NOME NÃO PODE TER MENOS DE 2 CARACTERES
        if (medico.getNome().length() < 2) {
            mensagensDeErro.append("O NOME deve ter no mímimo 02 caracteres \n");
            camposValidos = false;
        }

        // REGRA 05 - A ESPECIALIDADE DO MÉDICO NÃO PODE SER NULA
        if (medico.getEspecialidade() == null
                || medico.getEspecialidade().equals("")
                || medico.getEspecialidade().isEmpty()) {
            mensagensDeErro.append("O CAMPO ESPECIALIDADE DEVE SER PREENCHIDO! \n");
            camposValidos = false;
        }

        // REGRA 06 - A ESPECIALIDADE DO MÉDICO NÃO PODE TER MAIS DE 20 CARACTERES
        if (medico.getEspecialidade().length() > 20) {
            mensagensDeErro.append("O CAMPO ESPECIALIDADE DEVE TER NO MÁXIMO 20 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 07 - A ESPECIALIDADE DO MÉDICO NÃO PODE TER MENOS DE 2 CARACTERES
        if (medico.getEspecialidade().length() < 2) {
            mensagensDeErro.append("O CAMPO ESPECIALIDADE DEVE TER NO MÍNIMO 2 CARACTERES \n");
            camposValidos = false;
        }

        // REGRA 08 - O SEXO DO MÉDICO NÃO PODE SER NULO
        if (medico.getSexo() == null
                || medico.getSexo().equals("")
                || medico.getSexo().isEmpty()) {
            mensagensDeErro.append("O CAMPO SEXO DEVE SER PREENCHIDO! \n");
            camposValidos = false;
        }

        // REGRA 09 - O SEXO DO MÉDICO NÃO PODE TER MAIS DE 1 CARACTER
        if (medico.getEspecialidade().length() > 1) {
            mensagensDeErro.append("O CAMPO SEXO DEVE TER NO MÁXIMO 20 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 10 - O SEXO DO MÉDICO NÃO PODE TER MENOS DE 1 CARACTER
        if (medico.getEspecialidade().length() < 1) {
            mensagensDeErro.append("O CAMPO ESPECIALIDADE DEVE TER NO MÍNIMO 1 CARACTER \n");
            camposValidos = false;
        }

        // REGRA 11 - O SALÁRIO DO MÉDICO NÃO PODE SER NULO
        
       // if (medico.getSalario() == null)
        
        if (medico.getSalario() == null || medico.getSalario().equals("")) {
           mensagensDeErro.append("O CAMPO SALÁRIO DEVE SER PREENCHIDO! \n");
           camposValidos = false;
        }

        // REGRA 17 -  VALIDAR DATA DE NASCIMENTO 
        if (medico.getDataDeNascimento() == null) {
            mensagensDeErro.append("A DATA DE NASCIMENTO DO(A) USUÁRIO(A) NÃO PODE SER NULA \n");
            camposValidos = false;
        } else {

            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate.parse(sdf.format(medico.getDataDeNascimento()), formatter);

            } catch (DateTimeParseException e) {
                mensagensDeErro.append("DATA DE NASCIMENTO INVÁLIDA! \n");
                camposValidos = false;
            }

        }

        if (mensagensDeErro.length() > 0) {

            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());

        }

        return camposValidos;

    }

}
