package Controller;

import Persistencia.BuildDeTabelas;
import java.sql.SQLException;

public class AppAgendaMedica {

    public static void main(String[] args) throws SQLException {

        BuildDeTabelas buildDeTabelas = new BuildDeTabelas();
        buildDeTabelas.construirTabelas();
        buildDeTabelas.inserirPerfisIniciais();
        buildDeTabelas.inserirUsuariosIniciais();
        buildDeTabelas.inserirMedicosIniciais();
        buildDeTabelas.inserirPacientesIniciais();
        buildDeTabelas.inserirPlanosDeSaudeIniciais();
        buildDeTabelas.inserirConsultasIniciais();

    }

}
