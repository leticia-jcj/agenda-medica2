
package Persistencia;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ivanoliveira
 */
public interface InterfaceDAO {
    
    /// salvar entidades 
    public void salvar(Object entidade) throws SQLException;
    
    // deletar entidades 
    public void deletar(Object entidade) throws SQLException;
    
    // pesquisar por Id
    public Object buscarPorId(int id)throws SQLException;
    
    // modificar um objeto 
    public void atualizar(Object entidade)throws SQLException;
    
    // listar todos
   public List<?> listarTodos()throws SQLException;
    
    
            
    
    
    
    
    
  //  Object é mãe de todas as objetos 
    
}
