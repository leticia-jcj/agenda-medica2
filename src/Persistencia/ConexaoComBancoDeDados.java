package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexaoComBancoDeDados {

    //url de conexão com o banco 
    private final String urlDoBanco = "jdbc:hsqldb:file:BancoDeDados/";
    // nome do banco de dados
    private final String nomeDoBanco = "AgendaMedicaBD;hsqldb.lock_file=false";
    private final String usuarioDoBanco = "SA";
    private final String senhaDoBanco = "";

    public Connection conexao;

    public Connection conectar() throws SQLException {

        return conexao = DriverManager.getConnection(urlDoBanco + nomeDoBanco, usuarioDoBanco, senhaDoBanco);

    }

    public void desconetar() throws SQLException {

        conexao.close();

    }

    public void executarScriptSql(String sql) throws SQLException {

        System.out.println("Executando Script sql... \n " + sql);
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.execute();
        conexao.commit();
        desconetar();

    }

}
