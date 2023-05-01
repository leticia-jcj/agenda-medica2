package Persistencia;

import Entidades.Consulta;
import Entidades.Medico;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConsultaDAO extends ConexaoComBancoDeDados implements InterfaceCrud<Integer, Consulta> {

    private PlanoDAO daoPlano = new PlanoDAO();
    private PacienteDAO daoPaciente = new PacienteDAO();
    private MedicoDAO daoMedico = new MedicoDAO();
    
    
    @Override
    public void salvar(Consulta consulta) {

        String sql = "INSERT INTO CONSULTA("
                + "ID,"
                + "ID_PLANO,"
                + "ID_PACIENTE,"
                + "CRM_MEDICO,"
                + "SALA"
                + "DATA_DA_CONSULTA"
                + "HORA_DA_CONSULTA,"
                + ") VALUES (?,?,?,?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, daoPlano.buscarPorId(1).getId());
            pstm.setInt(2, daoPaciente.buscarPorId(1).getId());
            pstm.setInt(3, daoMedico.buscarPorId(122).getCrm());
            pstm.setString(4, consulta.getSala());
            Date dataPadraoSql = new Date(consulta.getDataDaConsulta().getTime());
            pstm.setDate(5, dataPadraoSql);
            pstm.setString(6, consulta.getHoraDaConsulta());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Consulta buscarPorId(Integer id) {

        Consulta consulta = new Consulta();

        String sql = "SELECT * FROM CONSULTA WHERE ID = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
            consulta.setId(lista.getInt("ID"));
                consulta.setPlano(daoPlano.buscarPorId(lista.getInt("ID_PLANO")));
                consulta.setPaciente(daoPaciente.buscarPorId(lista.getInt("ID_PACIENTE")));
                consulta.setMedico(daoMedico.buscarPorId(lista.getInt("CRM_MEDICO")));
                consulta.setSala(lista.getString("SALA"));
                consulta.setDataDaConsulta(lista.getDate("DATA_DA_CONSULTA"));
                consulta.setHoraDaConsulta(lista.getString("HORA_DA_CONSULTA"));

            }

            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return consulta;

    }

    @Override
    public void deletar(Consulta consulta) {
        
        String sql = "DELETE FROM CONSULTA WHERE ID = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, consulta.getId());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }


    }

    @Override
    public Consulta update(Consulta consulta) {

        String sql = "UPDATE CONSULTA SET "
                + "ID,"
                + "ID_PLANO,"
                + "ID_PACIENTE,"
                + "CRM_MEDICO,"
                + "SALA"
                + "DATA_DA_CONSULTA"
                + "HORA_DA_CONSULTA,"
                + ") VALUES (?,?,?,?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, consulta.getId());
            pstm.setInt(1, consulta.getPlano().getId());
            pstm.setInt(2, consulta.getPaciente().getId());
            pstm.setInt(3, consulta.getMedico().getCrm());
            pstm.setString(5, consulta.getSala());
            Date dataPadraoSql = new Date(consulta.getDataDaConsulta().getTime());
            pstm.setDate(5, dataPadraoSql);
            pstm.setString(6, consulta.getHoraDaConsulta());
            pstm.setInt(7, consulta.getId());
            
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return buscarPorId(consulta.getId());


    }

    @Override
    public Collection<Consulta> listarTodos() {

        List<Consulta> listaDeConsultas = new ArrayList<Consulta>();

        String sql = "SELECT * FROM CONSULTA";

        try {
            conectar();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet lista = pst.executeQuery();
            while (lista.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(lista.getInt("ID"));
                consulta.setPlano(daoPlano.buscarPorId(lista.getInt("ID_PLANO")));
                consulta.setPaciente(daoPaciente.buscarPorId(lista.getInt("ID_PACIENTE")));
                consulta.setMedico(daoMedico.buscarPorId(lista.getInt("CRM_MEDICO")));
                consulta.setSala(lista.getString("SALA"));
                consulta.setDataDaConsulta(lista.getDate("DATA_DA_CONSULTA"));
                consulta.setHoraDaConsulta(lista.getString("HORA_DA_CONSULTA"));
                listaDeConsultas.add(consulta);
            }

            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);
        }

        return listaDeConsultas;

    }

    private void desconectar() {
       
    }

}
