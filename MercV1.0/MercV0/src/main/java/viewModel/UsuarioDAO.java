/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.awt.List;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import model.FirebaseConnection;
import model.ReadData;
import static model.ReadData.list;
import viewModel.usuarioDTO;
import view.inicio;
import view.login;
public class UsuarioDAO {

    static ArrayList<usuarioDTO> listau = new ArrayList<usuarioDTO>();

    public static usuarioDTO registrarse(JTextField nombres, JTextField cedula, JPasswordField contraseña, JPasswordField codigoEmpresarial) {
        usuarioDTO usuario = new usuarioDTO(null, null, null, null);
        String nit = cedula.getText();
        String nom = nombres.getText();
        String con = contraseña.getText();
        String cor = codigoEmpresarial.getText();
        usuario.setName(nom);
        usuario.setNit(nit);
        usuario.setClave(con);
        usuario.setCorreo(cor);
        System.out.println(usuario.getNit());
        System.out.println(usuario.getClave());
        System.out.println(usuario.getCorreo());
        System.out.println(usuario.getName());
        return usuario;
    }

    public static boolean login(JTextField usuario, JPasswordField contraseña, login aThis) throws InterruptedException, ExecutionException {
        ReadData.leer();
        boolean x = false;
        System.out.println(ReadData.list.toString());
        System.out.println("nit="+usuario.getText());
        System.out.println("clave="+contraseña.getText());
        int i = 0;
        System.out.println(ReadData.listcont.toString());
        while(i < ReadData.list.size()){
            if(!contraseña.getText().isEmpty())
                x = ReadData.listcont.get(i).contains((usuario.getText())) && ReadData.list.get(i).contains("clave="+contraseña.getText());
                if (x==false){
                    i++;
                }else{
                    break;
                }
            }
        
        return x;
    }
}

