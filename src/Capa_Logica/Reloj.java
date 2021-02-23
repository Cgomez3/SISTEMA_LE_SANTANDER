
package Capa_Logica;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class Reloj extends Thread{
    private JLabel lbl;

    public Reloj(JLabel lbl) {
    this.lbl=lbl;
    }
    @Override
    public void run(){
        while(true){
            Date hoy=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss aa");
            lbl.setText(sdf.format(hoy));
            try {
                sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}
