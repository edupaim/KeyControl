/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.ChaveDTO;
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
public class ChaveRNTest {
    
    public ChaveRNTest() {
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
     * Test of devolucaoChave method, of class ChaveRN.
     */
    @Test
    public void testDevolucaoChave() throws Exception {
        System.out.println("devolucaoChave");
        ChaveDTO chave = null;
        ChaveRN.devolucaoChave(chave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of emprestar method, of class ChaveRN.
     * @throws java.lang.Exception
     */
    @Test
    public void testEmprestar() throws Exception {
        System.out.println("emprestar");
        int idChave = 2;
        int idBeneficiario = 3;
        boolean expResult = true;
       // boolean result = ChaveRN.emprestar(idChave, idBeneficiario);
        //assertEquals(expResult, result);
    }

    /**
     * Test of verificarDisponibilidade method, of class ChaveRN.
     */
    @Test
    public void testVerificarDisponibilidade() throws Exception {
        System.out.println("verificarDisponibilidade");
        int id = 0;
        boolean expResult = false;
        boolean result = ChaveRN.verificarDisponibilidade(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarChave method, of class ChaveRN.
     */
    @Test
    public void testBuscarChave() throws Exception {
        System.out.println("buscarChave");
        ChaveDTO chave = null;
        List<ChaveDTO> expResult = null;
        List<ChaveDTO> result = ChaveRN.buscarChave(chave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inserir method, of class ChaveRN.
     */
    @Test
    public void testInserir() throws Exception {
        System.out.println("inserir");
        ChaveDTO chave = null;
        boolean expResult = false;
        boolean result = ChaveRN.inserir(chave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class ChaveRN.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Integer id = null;
        boolean expResult = false;
        boolean result = ChaveRN.deletar(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorId method, of class ChaveRN.
     */
    @Test
    public void testBuscarPorId() throws Exception {
        System.out.println("buscarPorId");
        Integer id = null;
        ChaveDTO expResult = null;
        ChaveDTO result = ChaveRN.buscarPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
