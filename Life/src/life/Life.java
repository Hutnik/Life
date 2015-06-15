/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;
import java.awt.EventQueue;
import java.io.File;
/**
 *
 * @author Hutnik-Admin
 */
public class Life {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Frame();
            }
            
        });
        
    }
    
}
