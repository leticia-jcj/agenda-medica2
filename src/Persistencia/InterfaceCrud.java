package Persistencia;

import java.util.Collection;
/*
   Esta interface tem como objetivo definir a assinatura padrão para os 
   comportamentos de interação das entidades da aplicação com o banco
   de dados (CRUD-Padrão). Usamos nesta implementação o Generics. K - Key
   representa a chave primária de cada entidade. T é a representação da 
   entidade que será persistida no banco de dados.
*/
public interface InterfaceCrud<K, T> {

    public void salvar(T t);

    public T buscarPorId(K id);

    public void deletar(T t);

    public T update(T t);
    
    public Collection<T> listarTodos();
}
