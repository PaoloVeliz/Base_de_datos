/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;
import java.sql.*;
/**
 *
 * @author Paolo_Veliz
 */
public class Basededatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 Coneccionptj base = new Coneccionptj();
 // Connection cb = 
 base.Conectarabase();
 MenuPrincipal menup = new MenuPrincipal();
 menup.setVisible(true);
    
    }
    
}
