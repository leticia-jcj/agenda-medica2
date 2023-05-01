package PersistenciaTeste;

import Entidades.Usuario;
import Persistencia.PerfilDAO;
import Persistencia.UsuarioDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ivanoliveira
 */
public class CrudUsuarioTeste extends TestCase {

    private UsuarioDAO daoUsuario;
    private Usuario usuarioDeTeste;
    private int numeroAtualDeUsuarios;
    private SimpleDateFormat sdf;
    private PerfilDAO daoPerfil;

    /*
    Este método será chamado antes de cada método de teste.
     */
    @Override
    @Before
    public void setUp() throws ParseException {

        daoUsuario = new UsuarioDAO();
        daoPerfil = new PerfilDAO();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        usuarioDeTeste = new Usuario();
        usuarioDeTeste.setNome("Usuário de Teste App");
        usuarioDeTeste.setCpf("8046788666");
        usuarioDeTeste.setMatricula("12345");
        usuarioDeTeste.setPerfil(daoPerfil.buscarPorId(101));
        usuarioDeTeste.setSenha("123");
        usuarioDeTeste.setUsername("teste");
        usuarioDeTeste.setTelefone("619873334");
        usuarioDeTeste.setDataNascimento(sdf.parse("16/08/1978"));
        numeroAtualDeUsuarios = daoUsuario.listarTodos().size();

        // Verificando se existe o usuário de teste no banco
        for (Usuario usuarioDoBD : daoUsuario.listarTodos()) {
           
            if (usuarioDoBD.getNome().equals("Usuário de Teste App")) {
                usuarioDeTeste = usuarioDoBD;
            }
        }

    }

    // Primeiro Teste. Usuários iniciais.
    public void testeUsuariosIniciais() {

        // número de usuários iniciais (mínimo 03)    
        assertTrue(numeroAtualDeUsuarios >= 6);

        /*
         TEM QUE TER OS USUÁRIOS INICIAIS
        ('865741361100','IOLANDA CAIXETA','IOLANDA.IC','RSA01',102,'21044555','21301','16/08/1978')
        ('014876554733','GLADSTONE CASTRO','GLADSTONE.GC','IPV06',101,'21120988','21402','21/09/1977')
        ('103488774443','JOÃO PAULO','JOÃO.JP','SSDH',103,'06121356678','21503','14/12/1975')
        ('765441112276','FLORINDA JUSTOS','FLORINDA.FJ','ATM02',103,'06122358901','21588','24/11/1980')
        ('967741364100','ITALO SILA','ITALO.IS','RIST3',102,'06124044555','21677','22/08/1971')
        ('114876854735','MIRELA COUTO','MIRELA.MC','PAT45',101,'06126120988','21843','26/09/1962')
         */
        // IOLANDA CAIXETA
        assertEquals("865741361100", daoUsuario.buscarPorId(100).getCpf());
        assertEquals("IOLANDA CAIXETA", daoUsuario.buscarPorId(100).getNome());
        assertEquals("IOLANDA.IC", daoUsuario.buscarPorId(100).getUsername());
        assertEquals("RSA01", daoUsuario.buscarPorId(100).getSenha());
        assertEquals(102, daoUsuario.buscarPorId(100).getPerfil().getCodigo());
        assertEquals("21044555", daoUsuario.buscarPorId(100).getTelefone());
        assertEquals("21301", daoUsuario.buscarPorId(100).getMatricula());
        assertEquals("16/08/1978", sdf.format(daoUsuario.buscarPorId(100).getDataNascimento()));

        // GLADSTONE CASTRO
        assertEquals("014876554733", daoUsuario.buscarPorId(101).getCpf());
        assertEquals("GLADSTONE CASTRO", daoUsuario.buscarPorId(101).getNome());
        assertEquals("GLADSTONE.GC", daoUsuario.buscarPorId(101).getUsername());
        assertEquals("IPV06", daoUsuario.buscarPorId(101).getSenha());
        assertEquals(101, daoUsuario.buscarPorId(101).getPerfil().getCodigo());
        assertEquals("21120988", daoUsuario.buscarPorId(101).getTelefone());
        assertEquals("21402", daoUsuario.buscarPorId(101).getMatricula());
        assertEquals("21/09/1977", sdf.format(daoUsuario.buscarPorId(101).getDataNascimento()));

        // JOÃO PAULO
        assertEquals("103488774443", daoUsuario.buscarPorId(102).getCpf());
        assertEquals("JOÃO PAULO", daoUsuario.buscarPorId(102).getNome());
        assertEquals("JOÃO.JP", daoUsuario.buscarPorId(102).getUsername());
        assertEquals("SSDH", daoUsuario.buscarPorId(102).getSenha());
        assertEquals(103, daoUsuario.buscarPorId(102).getPerfil().getCodigo());
        assertEquals("21356678", daoUsuario.buscarPorId(102).getTelefone());
        assertEquals("21503", daoUsuario.buscarPorId(102).getMatricula());
        assertEquals("14/12/1975", sdf.format(daoUsuario.buscarPorId(102).getDataNascimento()));

        // FLORINDA JUSTOS
        assertEquals("765441112276", daoUsuario.buscarPorId(103).getCpf());
        assertEquals("FLORINDA JUSTOS", daoUsuario.buscarPorId(103).getNome());
        assertEquals("FLORINDA.FJ", daoUsuario.buscarPorId(103).getUsername());
        assertEquals("ATM02", daoUsuario.buscarPorId(103).getSenha());
        assertEquals(103, daoUsuario.buscarPorId(103).getPerfil().getCodigo());
        assertEquals("22358901", daoUsuario.buscarPorId(103).getTelefone());
        assertEquals("21588", daoUsuario.buscarPorId(103).getMatricula());
        assertEquals("24/11/1980", sdf.format(daoUsuario.buscarPorId(103).getDataNascimento()));

        // ITALO SILA
        assertEquals("967741364100", daoUsuario.buscarPorId(104).getCpf());
        assertEquals("ITALO SILA", daoUsuario.buscarPorId(104).getNome());
        assertEquals("ITALO.IS", daoUsuario.buscarPorId(104).getUsername());
        assertEquals("RIST3", daoUsuario.buscarPorId(104).getSenha());
        assertEquals(102, daoUsuario.buscarPorId(104).getPerfil().getCodigo());
        assertEquals("24044555", daoUsuario.buscarPorId(104).getTelefone());
        assertEquals("21677", daoUsuario.buscarPorId(104).getMatricula());
        assertEquals("22/08/1971", sdf.format(daoUsuario.buscarPorId(104).getDataNascimento()));

        // MIRELA COUTO
        assertEquals("114876854735", daoUsuario.buscarPorId(105).getCpf());
        assertEquals("MIRELA COUTO", daoUsuario.buscarPorId(105).getNome());
        assertEquals("MIRELA.MC", daoUsuario.buscarPorId(105).getUsername());
        assertEquals("PAT45", daoUsuario.buscarPorId(105).getSenha());
        assertEquals(101, daoUsuario.buscarPorId(105).getPerfil().getCodigo());
        assertEquals("26120988", daoUsuario.buscarPorId(105).getTelefone());
        assertEquals("21843", daoUsuario.buscarPorId(105).getMatricula());
        assertEquals("26/09/1962", sdf.format(daoUsuario.buscarPorId(105).getDataNascimento()));

    }

    @Test
    public void testeBuscarPorId() {

        Usuario usuario = daoUsuario.buscarPorId(105);
        assertNotNull(usuario.getNome());
        assertEquals("MIRELA COUTO", usuario.getNome());
    }

    @Test
    public void testeSalvarUsuario() {

        // invocando o método da DAO para inserir no banco o usuário de teste
        daoUsuario.salvar(usuarioDeTeste);

        // verificando se o número de usuários exsitentes no banco aumentou em uma unidade  
        assertEquals(numeroAtualDeUsuarios + 1, daoUsuario.listarTodos().size());

        // Verificando se exsite o usuário de teste no banco
        for (Usuario usuarioDoBD : daoUsuario.listarTodos()) {

            if (usuarioDoBD.getNome().equals("Usuário de Teste App")) {
                usuarioDeTeste = usuarioDoBD;
            }
        }

        // Verificando se existe o usuário de teste no banco    
        assertTrue(usuarioDeTeste.getId() > 0);

        // verificando se o usuário de teste não é núlo no banco
        assertNotNull(daoUsuario.buscarPorId(usuarioDeTeste.getId()).getNome());

    }

    @Test
    public void testeUpdateUsuario() throws ParseException {

        // modificando o usuário em memória        
        usuarioDeTeste.setNome("Usuário de Teste App - Modificado");
        usuarioDeTeste.setCpf("99999999999");
        usuarioDeTeste.setMatricula("99999");
        usuarioDeTeste.setPerfil(daoPerfil.buscarPorId(102));
        usuarioDeTeste.setSenha("999");
        usuarioDeTeste.setUsername("Modificado");
        usuarioDeTeste.setTelefone("999999999");
        usuarioDeTeste.setDataNascimento(sdf.parse("01/01/1901"));

        // invocando o método da DAO para modificar o usuário no banco
        daoUsuario.update(usuarioDeTeste);

        // Verificando se o usuário foi modificado no Banco
        assertEquals("Usuário de Teste App - Modificado", daoUsuario.buscarPorId(usuarioDeTeste.getId()).getNome());
        assertEquals("99999999999", daoUsuario.buscarPorId(usuarioDeTeste.getId()).getCpf());
        assertEquals("99999", daoUsuario.buscarPorId(usuarioDeTeste.getId()).getMatricula());
        assertEquals(daoPerfil.buscarPorId(102).getNome(), daoUsuario.buscarPorId(usuarioDeTeste.getId()).getPerfil().getNome());
        assertEquals("999", daoUsuario.buscarPorId(usuarioDeTeste.getId()).getSenha());
        assertEquals("Modificado", daoUsuario.buscarPorId(usuarioDeTeste.getId()).getUsername());
        assertEquals("999999999", daoUsuario.buscarPorId(usuarioDeTeste.getId()).getTelefone());
        assertEquals("01/01/1901", sdf.format(daoUsuario.buscarPorId(usuarioDeTeste.getId()).getDataNascimento()));

        // voltar USUÁRIO para original
        usuarioDeTeste.setNome("Usuário de Teste App");
        usuarioDeTeste.setCpf("8046788666");
        usuarioDeTeste.setMatricula("12345");
        usuarioDeTeste.setPerfil(daoPerfil.buscarPorId(101));
        usuarioDeTeste.setSenha("123");
        usuarioDeTeste.setUsername("teste");
        usuarioDeTeste.setTelefone("619873334");
        usuarioDeTeste.setDataNascimento(sdf.parse("16/08/1978"));
        daoUsuario.update(usuarioDeTeste);

    }

    @Test
    public void testeDeletarUsuario() {

        // invocando o método da DAO para deletar o usuário de teste
        daoUsuario.deletar(usuarioDeTeste);

        // verificando se o número de usuários exsitentes no banco reduziu em uma unidade
        assertEquals(numeroAtualDeUsuarios - 1, daoUsuario.listarTodos().size());

        // verificando se o USUÁRIO de teste é núlo no banco
        assertNull(daoUsuario.buscarPorId(usuarioDeTeste.getId()).getNome());

    }

}