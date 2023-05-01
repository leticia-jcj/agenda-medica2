package Controller;

import Entidades.Consulta;
import Persistencia.ConsultaDAO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import javax.swing.JOptionPane;
import lombok.Getter;

public class ConsultaController {

    private ConsultaDAO daoConsulta;
    private StringBuilder mensagensDeErro;
    private SimpleDateFormat sdf;
    @Getter
    private Collection<Consulta> listaDeConsultas;
    private boolean camposValidos;

    // MÉTODO CONSTRUTOR
    public ConsultaController() {

        setUp();
    }

    // Método de configuração inicial e instanciação dos objetos necessários
    private void setUp() {

        mensagensDeErro = new StringBuilder();
        daoConsulta = new ConsultaDAO();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        camposValidos = false;
        listaDeConsultas = daoConsulta.listarTodos();

    }

    public void cadastrarConsulta(Consulta consulta) {

        if (validarCampos(consulta)) {
            daoConsulta.salvar(consulta);
        }
    }

    public void alterarConsulta(Consulta consulta) {

        if (validarCampos(consulta)) {
            daoConsulta.update(consulta);
        }

    }

    public Consulta buscarPorId(int id) {

        //verifica se o id foi informado 
         
        if (id > 0) {

            return daoConsulta.buscarPorId(id);

        }

        JOptionPane.showMessageDialog(null, "Consulta inexistente");
        return null;
    }

    public void excluirConsulta(int id) {

        daoConsulta.deletar(daoConsulta.buscarPorId(id));

        if (daoConsulta.buscarPorId(id).getMedico() == null) {

            JOptionPane.showMessageDialog(null, "Consulta excluída  com sucesso");
        }
    }

    private boolean validarCampos(Consulta consulta) {

        camposValidos = true;

        // REGRA 01 - TODA CONSULTA TEM UM MÉDICO , MÉDICO NÃO PODE SER NULO 
        if (consulta.getMedico().getNome() == null) {
            mensagensDeErro.append("O(A) MÉDICO(A) DEVE SER INFORMADO(A)! \n");
            camposValidos = false;
        }

        //  REGRA 02 - TODA CONSULTA TEM UM PACIENTE , PACIENTE NÃO PODE SER NULO 
        if (consulta.getPaciente().getNome() == null) {
            mensagensDeErro.append("O(A) PACIENTE DEVE SER INFORMADO(A)! \n");
            camposValidos = false;
        }

        // REGRA 03 - TODA CONSULTA TEM UM PLANO , PLANO NÃO PODE SER NULO 
        if (consulta.getPlano().getOperadora() == null) {
            mensagensDeErro.append("O PLANO DEVE SER INFORMADO! \n");
            camposValidos = false;
        }

        // REGRA 04 - TODA CONSULTA É REALIZADA EM UMA SALA , SALA NÃO PODE SER NULA 
        if (consulta.getSala() == null
                || consulta.getSala().equals("")
                || consulta.getSala().isEmpty()) {
            mensagensDeErro.append("O CAMPO SALA DEVE SER PREENCHIDO! \n");
            camposValidos = false;
        }

        // REGRA 05 - A SALA AONDE É REALIZADA A CONSULTA NÃO PODE TER MAIS DE 10 CARACTERES
        if (consulta.getSala().length() > 10) {
            mensagensDeErro.append("O CAMPO SALA DEVE TER NO MÁXIMO 10 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 06 - A SALA AONDE É REALIZADA A CONSULTA NÃO PODE TER MENOS DE 2 CARACTERES
        if (consulta.getSala().length() < 2) {
            mensagensDeErro.append("O CAMPO SALA DEVE TER NO MÍNIMO 2 CARACTERES \n");
            camposValidos = false;
        }

        // REGRA 07 - TODA CONSULTA É REALIZADA EM UM HORÁRIO AGENDADO , O HORÁRIO DA CONSULTA NÃO PODE SER NULO 
        if (consulta.getHoraDaConsulta() == null)
                {
            mensagensDeErro.append("O CAMPO HORA DA CONSULTA DEVE SER PREENCHIDO! \n");
            camposValidos = false;
        }

        // REGRA 08 - O HORÁRIO AONDE É REALIZADA A CONSULTA NÃO PODE TER MAIS DE 05 CARACTERES
        if (consulta.getSala().length() > 5) {
            mensagensDeErro.append("O CAMPO HORÁRIO DA CONSULTA DEVE TER NO MÁXIMO 5 CARACTERES HH:MM ! \n");
            camposValidos = false;
        }

        // REGRA 09 - O HORÁRIO  DA CONSULTA  NÃO PODE TER MENOS DE 5 CARACTERES HH:MM
        if (consulta.getSala().length() < 5) {
            mensagensDeErro.append("O CAMPO HORÁRIO DEVE TER NO MÍNIMO 5 CARACTERES HH:MM \n");
            camposValidos = false;
        }

        // REGRA 10 -  VALIDAR DATA DA CONSULTA 
        if (consulta.getDataDaConsulta() == null) {
            mensagensDeErro.append("A DATA DA CONSULTA NÃO PODE SER NULA \n");
            camposValidos = false;
        } else {

            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate.parse(sdf.format(consulta.getDataDaConsulta()), formatter);

            } catch (DateTimeParseException e) {
                mensagensDeErro.append("DATA DA CONSULTA INVÁLIDA! \n");
                camposValidos = false;
            }

        }

        if (mensagensDeErro.length() > 0) {

            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());

        }

        return camposValidos;

    }

}
