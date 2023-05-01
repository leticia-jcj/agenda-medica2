package PersistenciaTeste;

import Entidades.Perfil;
import Persistencia.PerfilDAO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CrudPerfilTeste extends TestCase {

    private PerfilDAO daoPerfil;
    private Perfil perfilDeTeste;
    private int numeroAtualDePerfis;

    /* 
    Este método será chamado antes de cada método de teste.
     */
    @Before
    @Override
    public void setUp() {

        daoPerfil = new PerfilDAO();
        perfilDeTeste = new Perfil();
        perfilDeTeste.setNome("Perfil de Teste App");
        perfilDeTeste.setDescricao("Perfil criado para Teste");
        numeroAtualDePerfis = daoPerfil.listarTodos().size();

        for (Perfil perfilDoBD : daoPerfil.listarTodos()) {

            if (perfilDoBD.getNome().equals("Perfil de Teste App")) {
                perfilDeTeste = perfilDoBD;
            }
        }
    }

    @Test
    public void testePerfisIniciais() {
        // número de perfis iniciais (mínimo 03)    
        assertTrue(numeroAtualDePerfis >= 3);

        /* TEM QUE TER OS PERFIS INICIAIS 
        101 ADMINISTRADOR SUPER USUÁRIO
        102 COMUM	  USUÁRIO SOMENTE CONSULTA
        103 AVANÇADO	  USUÁRIO CONSULTA E CADASTRA
         */
        assertEquals("ADMINISTRADOR", daoPerfil.buscarPorId(101).getNome());
        assertEquals("SUPER USUÁRIO", daoPerfil.buscarPorId(101).getDescricao());

        assertEquals("COMUM", daoPerfil.buscarPorId(102).getNome());
        assertEquals("USUÁRIO SOMENTE CONSULTA", daoPerfil.buscarPorId(102).getDescricao());

        assertEquals("AVANÇADO", daoPerfil.buscarPorId(103).getNome());
        assertEquals("USUÁRIO CONSULTA E CADASTRA", daoPerfil.buscarPorId(103).getDescricao());

    }

    @Test
    public void testeBuscarPorId() {

        Perfil perfil = daoPerfil.buscarPorId(101);
        assertNotNull(perfil.getNome());
        assertEquals("ADMINISTRADOR", perfil.getNome());
    }

    @Test
    public void testeSalvarPerfil() {
        
        // invocando o método da DAO para inserir no banco o perfil de teste 
        daoPerfil.salvar(perfilDeTeste);
        
        // verificando se o número de perfis exsitentes no banco aumentou em uma unidade  
        assertEquals(numeroAtualDePerfis + 1, daoPerfil.listarTodos().size());

        // Verificando se exsite o perfil de teste no banco
        for (Perfil perfilDoBD : daoPerfil.listarTodos()) {

            if (perfilDoBD.getNome().equals("Perfil de Teste App")) {
                perfilDeTeste = perfilDoBD;
            }
        }
        
        // Verificando se exsite o perfil de teste no banco
        assertTrue(daoPerfil.buscarPorId(perfilDeTeste.getCodigo()).getCodigo() > 0);

        // verificando se o perfil de teste não é núlo no banco
        assertNotNull(daoPerfil.buscarPorId(perfilDeTeste.getCodigo()).getNome());

    }

    @Test
    public void testeUpdatePerfil() {

        // modificando o perfil em memória        
        perfilDeTeste.setNome("Perfil de Teste App - Modificado");
        perfilDeTeste.setDescricao("Perfil modificado no teste");

        // invocando o método da DAO para modificar o perfil no banco
        daoPerfil.update(perfilDeTeste);

        // Verificando se o perfil foi modificado no Banco
        assertEquals("Perfil de Teste App - Modificado", daoPerfil.buscarPorId(perfilDeTeste.getCodigo()).getNome());
        assertEquals("Perfil modificado no teste", daoPerfil.buscarPorId(perfilDeTeste.getCodigo()).getDescricao());

        // voltar perfil para original
        perfilDeTeste.setNome("Perfil de Teste App");
        perfilDeTeste.setDescricao("Perfil criado para Teste");
        daoPerfil.update(perfilDeTeste);

    }

    @Test
    public void testeDeletarPerfil() {

        // invocando o método da DAO para deletar o perfil de teste
        daoPerfil.deletar(perfilDeTeste);
        
        // verificando se o número de perfis exsitentes no banco reduziu em uma unidade 
        assertEquals(numeroAtualDePerfis - 1, daoPerfil.listarTodos().size());

        // verificando se o perfil de teste é núlo no banco
        assertNull(daoPerfil.buscarPorId(perfilDeTeste.getCodigo()).getNome());

    }

}
