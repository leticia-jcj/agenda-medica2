/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entidades.Plano;
import Persistencia.PlanoDAO;
import java.text.SimpleDateFormat;
import java.util.Collection;
import javax.swing.JOptionPane;
import lombok.Getter;

/**
 *
 * @author ivanoliveira
 */
public class PlanoController {

    private PlanoDAO daoPlano;
    private StringBuilder mensagensDeErro;
    private SimpleDateFormat sdf;
    @Getter
    private Collection<Plano> listaDePlanos;
    private boolean camposValidos;

    // MÉTODO CONSTRUTOR
    public PlanoController() {

        setUp();

    }

    // Método de configuração inicial e instanciação dos objetos necessários
    private void setUp() {

        mensagensDeErro = new StringBuilder();
        daoPlano = new PlanoDAO();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        camposValidos = false;
        listaDePlanos = daoPlano.listarTodos();

    }

    private boolean validarCampos(Plano plano) {

        camposValidos = true;

        // REGRA 01 - CÓDIGO DO PLANO NÃO PODE SER NULO 
        if (plano.getCodigo() == null
                || plano.getCodigo().equals("")
                || plano.getCodigo().isEmpty()) {
            mensagensDeErro.append("O CÓDIGO DO PLANO NÃO PODE SER NULO! \n");
            camposValidos = false;
        }

        // REGRA 02 - O CÓDIGO DO PLANO NÃO PODE TER MAIS DE 06 CARACTERES
        if (plano.getCodigo().length() > 6) {
            mensagensDeErro.append("O CÓDIGO DO PLANO DEVE TER NO MÁXIMO 6 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 03 - O CÓDIGO DO PLANO NÃO PODE TER MENOS DE 2 CARACTERES
        if (plano.getCodigo().length() < 2) {
            mensagensDeErro.append("O CÓDIGO DEVE TER NO MÍNIMO 02 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 04 - A OPERADORA DO PLANO NÃO PODE SER NULA
        if (plano.getOperadora() == null
                || plano.getOperadora().equals("")
                || plano.getOperadora().isEmpty()) {
            mensagensDeErro.append("O CAMPO OPERADORA DEVE SER PREENCHIDO! \n");
            camposValidos = false;
        }

        // REGRA 05 - A OPERADORA DO PLANO NÃO PODE TER MAIS DE 80 CARACTERES
        if (plano.getOperadora().length() > 80) {
            mensagensDeErro.append("O NOME DA OPERADORA DEVE TER NO MÁXIMO 80 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 06 - A OPERADORA DO PLANO NÃO PODE TER MENOS DE 4 CARACTERES
        if (plano.getOperadora().length() < 4) {
            mensagensDeErro.append("O NOME DA OPERADORA DEVE TER NO MÍNIMO 04 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 07 - O TELEFONE DO PLANO NÃO PODE SER NULO
        if (plano.getTelefone() == null
                || plano.getTelefone().equals("")
                || plano.getTelefone().isEmpty()) {
            mensagensDeErro.append("O CAMPO TELEFONE DEVE SER PREENCHIDO! \n");
            camposValidos = false;
        }

        // REGRA 08 - TELEFONE NÃO PODE TER MAIS DE 15 CARACTERES
        if (plano.getTelefone().length() > 15) {

            mensagensDeErro.append("O Telefone deve ter no máximo 15 caracteres \n");
            camposValidos = false;
        }

        // REGRA 09 - TELEFONE NÃO PODE TER MENOS DE 7 CARACTERES
        if (plano.getTelefone().length() < 7) {
            mensagensDeErro.append("O TELEFONE deve ter no mímimo 07 caracteres \n");
            camposValidos = false;
        }

        // REGRA 10 - O ENDEREÇO DO PLANO NÃO PODE SER NULO
        if (plano.getEndereco() == null
                || plano.getEndereco().equals("")
                || plano.getEndereco().isEmpty()) {
            mensagensDeErro.append("O CAMPO ENDEREÇO DEVE SER PREENCHIDO! \n");
            camposValidos = false;
        }

        // REGRA 11 - O ENDEREÇO DO PLANO NÃO PODE TER MAIS DE 100 CARACTERES
        if (plano.getOperadora().length() > 100) {
            mensagensDeErro.append("O ENDEREÇO DA OPERADORA DEVE TER NO MÁXIMO 100 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 12 - O ENDEREÇO DO PLANO NÃO PODE TER MENOS DE 4 CARACTERES
        if (plano.getOperadora().length() < 06) {
            mensagensDeErro.append("O ENDEREÇO DA OPERADORA DEVE TER NO MÍNIMO 06 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 13 - O REGISTRO NA ANS DO PLANO NÃO PODE SER NULO 
        if (plano.getRegistroAns() == null
                || plano.getRegistroAns().equals("")
                || plano.getRegistroAns().isEmpty()) {
            mensagensDeErro.append("O REGISTRO DA ANS DO PLANO NÃO PODE SER NULO! \n");
            camposValidos = false;
        }

        // REGRA 14 - O REGISTRO NA ANS DO PLANO NÃO PODE TER MAIS DE 10 CARACTERES
        if (plano.getRegistroAns().length() > 10) {
            mensagensDeErro.append("O REGISTRO NA ANS DO PLANO DEVE TER NO MÁXIMO 10 CARACTERES! \n");
            camposValidos = false;
        }

        // REGRA 15 - O REGISTRO NA ANS DO PLANO NÃO PODE TER MENOS DE 2 CARACTERES
        if (plano.getRegistroAns().length() < 2) {
            mensagensDeErro.append("O REGISTRO NA ANS DO PLANO DEVE TER NO MÍNIMO 02 CARACTERES! \n");
            camposValidos = false;
        }

        if (mensagensDeErro.length() > 0) {

            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());

        }

        return camposValidos;

    }

}
