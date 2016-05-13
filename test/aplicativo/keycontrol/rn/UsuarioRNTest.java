/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.UsuarioDTO;
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
public class UsuarioRNTest {
    
    UsuarioDTO u;
    
    public UsuarioRNTest() {
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
     * Test of logar method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testLogar() throws Exception {
        System.out.println("logar");
        String login = "Nall";
        String senha = "2810";                
        UsuarioRN instance = new UsuarioRN();
        boolean expResult = true;
        boolean result = instance.logar(login, senha);
        assertEquals(expResult, result);
    
        
    }

    /**
     * Test of inserir method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testInserir() throws Exception {
        System.out.println("inserir");
        int id = 19, tipo = 1;
        String nome = "Ian Pierre";
        String senhar = "2810";
        String login ="Nall";
        
        u = new UsuarioDTO(id, nome, login, senhar, tipo);
        String senha = "2810";
        UsuarioRN instance = new UsuarioRN();
        boolean expResult = true;
        boolean result = instance.inserir(u, senha);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of atualizar method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testAtualizar() throws Exception {
        System.out.println("atualizar");
        //u.setSenha("281094");
        String senhar = "281094";
        UsuarioRN instance = new UsuarioRN();
        boolean expResult = true;
        boolean result = instance.atualizar(u, senhar);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of deletar method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Integer id = 12;
        UsuarioRN instance = new UsuarioRN();
        boolean expResult = true;
        boolean result = instance.deletar(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of listarTodos method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testListarTodos() throws Exception {
        System.out.println("listarTodos");
        UsuarioRN instance = new UsuarioRN();
        List<UsuarioDTO> expResult = null;
        List<UsuarioDTO> result = instance.listarTodos();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of buscarPorId method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {
        System.out.println("buscarPorId");
        Integer id = 20;
        UsuarioRN instance = new UsuarioRN();
        UsuarioDTO expResult = u;
        UsuarioDTO result = instance.buscarPorId(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscar method, of class UsuarioRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        UsuarioDTO p = null;
        UsuarioRN instance = new UsuarioRN();
        List<UsuarioDTO> expResult = null;
        List<UsuarioDTO> result = instance.buscar(p);
        assertEquals(expResult, result);
    }
    
}
