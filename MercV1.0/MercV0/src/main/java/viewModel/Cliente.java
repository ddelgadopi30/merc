/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.util.ArrayList;
import view.cliente;

/**
 *
 * @author HOME
 */
public class Cliente {
    public static ArrayList<String> clientes = new ArrayList<>();
    public static void añadir(String nombrec, String nitc, String telefonoc, String dirección){
        clientes.add(nombrec);
        clientes.add(nitc);
        clientes.add(telefonoc);
        clientes.add(dirección);  
    }
    
}
