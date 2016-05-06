package aplicativo.keycontrol.util;

import aplicativo.keycontrol.gui.MainFrame;
import aplicativo.keycontrol.main.KeyControl;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @author Edu
 */
public class HoraUtil implements Runnable{
    
    private SimpleDateFormat sdf = null;

        public HoraUtil() {
            sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        }

        @Override
        public void run() {
            while (true) {
                hora();
            }
        }

        public void hora() {
            KeyControl.mainFrame.LbHora.setText(sdf.format(new Date()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                MensagensUtil.addMsg(KeyControl.mainFrame, ex.getMessage());
            }
        }

}
