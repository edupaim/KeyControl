/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.rn;

import aplicativo.keycontrol.dto.IBeneficiarioDTO;
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
public class BeneficiarioRNTest {
    
    public BeneficiarioRNTest() {
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
     * Test of inserir method, of class BeneficiarioRN.
     */
    @Test
    public void testInserir() throws Exception {
        System.out.println("inserir");
        IBeneficiarioDTO beneficiario = null;
        BeneficiarioRN instance = new BeneficiarioRN();
        instance.inserir(beneficiario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorMatricula method, of class BeneficiarioRN.
     */
    @Test
    public void testBuscarPorMatricula() throws Exception {
        System.out.println("buscarPorMatricula");
        String matricula = "";
        BeneficiarioRN instance = new BeneficiarioRN();
        IBeneficiarioDTO expResult = null;
        IBeneficiarioDTO result = instance.buscarPorMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
