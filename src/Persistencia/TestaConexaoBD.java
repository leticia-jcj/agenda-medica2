package Persistencia;

import java.sql.SQLException;

public class TestaConexaoBD {

    public static void main(String[] args) throws SQLException {

        
        /*
        ConexaoComBancoDeDados banco = new ConexaoComBancoDeDados();

        banco.conectar(); // se houver erro ...jogar exceção sql

        System.out.println("Conexão com banco de Dados sucesso!!!");

        banco.desconectar();
        
        System.out.println("Conexão com banco fechada com sucesso!!!");

         */
        // teste02
        BuildDeTabelas buildTabelas = new BuildDeTabelas();
        
        buildTabelas.construirTabelas();
        
        buildTabelas.inserirDados();

    }

}
