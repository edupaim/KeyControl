/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class MensagensUtil {

    public static void addMsg(Component comp, String msg) {
        JOptionPane.showMessageDialog(comp, msg);
    }

    public static boolean confirm(String pergunta, String titulo) {
        Object[] options = {"Sim", "Não"};
        return JOptionPane.showOptionDialog(null,
                pergunta,
                titulo,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null, options, options[0]) == 0;
    }

}
