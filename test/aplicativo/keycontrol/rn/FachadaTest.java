/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.UsuarioDTO;
import java.awt.Component;
import java.awt.Container;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class FachadaTest {
    
    public FachadaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of limparTodosCampos method, of class Fachada.
     */
    @Test
    public void testLimparTodosCampos() {
        System.out.println("limparTodosCampos");
        Container container = null;
        Fachada instance = new Fachada();
        instance.limparTodosCampos(container);
        
    }

    /**
     * Test of logout method, of class Fachada.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        Fachada instance = new Fachada();
        instance.logout();
        
    }

    /**
     * Test of atualizarTxtUsuario method, of class Fachada.
     */
    @Test
    public void testAtualizarTxtUsuario() {
        System.out.println("atualizarTxtUsuario");
        Integer id = null;
        Fachada instance = new Fachada();
        instance.atualizarTxtUsuario(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizarTabelaUsuarios method, of class Fachada.
     */
    @Test
    public void testAtualizarTabelaUsuarios_0args() {
        System.out.println("atualizarTabelaUsuarios");
        Fachada instance = new Fachada();
        instance.atualizarTabelaUsuarios();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizarTabelaUsuarios method, of class Fachada.
     */
    @Test
    public void testAtualizarTabelaUsuarios_List() {
        System.out.println("atualizarTabelaUsuarios");
        List<UsuarioDTO> consulta = null;
        Fachada instance = new Fachada();
        instance.atualizarTabelaUsuarios(consulta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarUsuFiltrado method, of class Fachada.
     */
    @Test
    public void testListarUsuFiltrado() {
        System.out.println("listarUsuFiltrado");
        Fachada instance = new Fachada();
        instance.listarUsuFiltrado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fazerLogin method, of class Fachada.
     */
    @Test
    public void testFazerLogin() {
        System.out.println("fazerLogin");
        String login = "";
        String senha = "";
        Fachada instance = new Fachada();
        instance.fazerLogin(login, senha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menuPainel method, of class Fachada.
     */
    @Test
    public void testMenuPainel() {
        System.out.println("menuPainel");
        Integer tipo = null;
        Component comp = null;
        Fachada instance = new Fachada();
        instance.menuPainel(tipo, comp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cadastrarUsuario method, of class Fachada.
     */
    @Test
    public void testCadastrarUsuario() {
        System.out.println("cadastrarUsuario");
        String nome = "";
        String login = "";
        String senha = "";
        String senhar = "";
        Integer tipo = null;
        Fachada instance = new Fachada();
        instance.cadastrarUsuario(nome, login, senha, senhar, tipo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tabelaUsuarioSelecionada method, of class Fachada.
     */
    @Test
    public void testTabelaUsuarioSelecionada() {
        System.out.println("tabelaUsuarioSelecionada");
        Integer linha = null;
        Fachada instance = new Fachada();
        instance.tabelaUsuarioSelecionada(linha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterarUsuario method, of class Fachada.
     */
    @Test
    public void testAlterarUsuario() {
        System.out.println("alterarUsuario");
        Integer id = null;
        String nome = "";
        String login = "";
        String senha = "";
        Integer tipo = null;
        String senhar = "";
        Fachada instance = new Fachada();
        instance.alterarUsuario(id, nome, login, senha, tipo, senhar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirUsuario method, of class Fachada.
     */
    @Test
    public void testExcluirUsuario() {
        System.out.println("excluirUsuario");
        Integer id = null;
        Fachada instance = new Fachada();
        instance.excluirUsuario(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inserirChave method, of class Fachada.
     */
    @Test
    public void testInserirChave() {
        System.out.println("inserirChave");
        String sala = "";
        Integer capacidade = null;
        Integer tipo = null;
        Fachada instance = new Fachada();
        instance.inserirChave(sala, capacidade, tipo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletarChave method, of class Fachada.
     */
    @Test
    public void testDeletarChave() {
        System.out.println("deletarChave");
        Integer id = null;
        Fachada instance = new Fachada();
        instance.deletarChave(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of devolverChave method, of class Fachada.
     */
    @Test
    public void testDevolverChave() {
        System.out.println("devolverChave");
        String id = "";
        Fachada instance = new Fachada();
        instance.devolverChave(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarChave method, of class Fachada.
     */
    @Test
    public void testBuscarChave() {
        System.out.println("buscarChave");
        String sala = "";
        String capacidade = "";
        Integer tipo = null;
        Fachada instance = new Fachada();
        instance.buscarChave(sala, capacidade, tipo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
