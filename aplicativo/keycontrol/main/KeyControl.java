/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo.keycontrol.main;

import aplicativo.keycontrol.dto.UsuarioDTO;
import aplicativo.keycontrol.gui.LoginFrame;

/**
 *
 * @author Edu
 */
public class KeyControl {

    private static UsuarioDTO usuarioLogado;
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public static UsuarioDTO getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(UsuarioDTO usuarioLogado) {
        KeyControl.usuarioLogado = usuarioLogado;
    }
}
