
package Persistencia;

import Entidades.Medico;
import Entidades.PlanoDeSaude;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlanoDeSaudeDAO extends ConexaoComBancoDeDados implements InterfaceCrud<Integer, PlanoDeSaude>{

    @Override
    public void salvar(PlanoDeSaude planoDeSaude) {
        
        String sql = "INSERT INTO PLANO_DE_SAUDE("
                + "ID,"
                + "CODIGO_DO_PLANO,"
                + "OPERADORA,"
                + "TELEFONE,"
                + "ENDERECO"
                + "REGISTRO_ANS,"
                + ") VALUES (?,?,?,?,?,?);";

        try {
            conectar();

            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, planoDeSaude.getId());
            pstm.setString(2, planoDeSaude.getCodigoDoPlano());
            pstm.setString(3, planoDeSaude.getOperadoraDoPlano());
            pstm.setString(4, planoDeSaude.getTelefone());
            pstm.setString(5, planoDeSaude.getEndereco());
            pstm.setString(6, planoDeSaude.getRegistroANS());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public PlanoDeSaude buscarPorId(Integer id) {
    
        PlanoDeSaude planoDeSaude = new PlanoDeSaude();

        String sql = "SELECT * FROM PLANO_DE_SAUDE WHERE ID = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                planoDeSaude.setId(lista.getInt("ID"));
                planoDeSaude.setCodigoDoPlano(lista.getString("CODIGO_DO_PLANO"));
                planoDeSaude.setOperadoraDoPlano(lista.getString("OPERADORA"));
                planoDeSaude.setTelefone(lista.getString("TELEFONE"));
                planoDeSaude.setEndereco(lista.getString("ENDERECO"));
                planoDeSaude.setRegistroANS(lista.getString("REGISTRO_ANS"));

            }

            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return planoDeSaude;

    
    }

    @Override
    public void deletar(PlanoDeSaude planoDeSaude) {
        
        String sql = "DELETE FROM PLANO_DE_SAUDE WHERE ID = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, planoDeSaude.getId());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        
    }

    @Override
    public PlanoDeSaude update(PlanoDeSaude planoDeSaude) {
        
        String sql = "UPDATE PLANO_DE_SAUDE SET "
                + "ID,"
                + "CODIGO_DO_PLANO,"
                + "OPERADORA,"
                + "TELEFONE,"
                + "ENDERECO"
                + "REGISTRO_ANS,"
                + ") VALUES (?,?,?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, planoDeSaude.getId());
            pstm.setString(2, planoDeSaude.getCodigoDoPlano());
            pstm.setString(3, planoDeSaude.getOperadoraDoPlano());
            pstm.setString(4, planoDeSaude.getTelefone());
            pstm.setString(5, planoDeSaude.getEndereco());
            pstm.setString(6, planoDeSaude.getRegistroANS());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return buscarPorId(planoDeSaude.getId());
        
    }

    @Override
    public Collection<PlanoDeSaude> listarTodos() {
        
        List<PlanoDeSaude> listaDePlanosDeSaude= new ArrayList<PlanoDeSaude>();

        String sql = "SELECT * FROM PLANO_DE_SAUDE";

        try {
            conectar();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet lista = pst.executeQuery();
            while (lista.next()) {
                PlanoDeSaude planoDeSaude = new PlanoDeSaude();
                planoDeSaude.setId(lista.getInt("ID"));
                planoDeSaude.setCodigoDoPlano(lista.getString("CODIGO_DO_PLANO"));
                planoDeSaude.setOperadoraDoPlano(lista.getString("OPERADORA_DO_PLANO"));
                planoDeSaude.setTelefone(lista.getString("TELEFONE"));
                planoDeSaude.setEndereco(lista.getString("ENDERECO"));
                planoDeSaude.setRegistroANS(lista.getString("REGISTRO_ANS"));
                listaDePlanosDeSaude.add(planoDeSaude);
            }

            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);
        }

        return listaDePlanosDeSaude;

        
    
    }
    
}
