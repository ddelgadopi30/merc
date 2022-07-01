/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.ReadData;
import javax.swing.table.*;
/**
 *
 * @author edson
 */
public class ProductoDAO {
    public static ArrayList<Object> listp = new ArrayList<Object>();
    public static ProductoDTO crear(JTextField codigo, JTextField nombre, JTextField preciov, JTextField preciop, JTextField cantidad) throws SQLException{
        ProductoDTO producto = new ProductoDTO();
        String code = codigo.getText();
        String nom = nombre.getText();
        String prev = preciov.getText();
        int h = parseInt(prev);
        String prep = preciop.getText();
        int i = parseInt(prep);
        int can = parseInt(cantidad.getText());
        producto.setCode(code);
        producto.setNombre(nom);
        producto.setPrecio(h);
        producto.setPreciop(i);
        producto.setCantidad(can);
        javax.swing.JOptionPane.showMessageDialog(null, "Creado");
        return producto;
        
    }
    
    public static ProductopushDTO crear2(JTextField nombre, JTextField codigo){
        ProductopushDTO xd = new ProductopushDTO();
        xd.nombre = nombre.getText();
        xd.cantidad = parseInt(codigo.getText());
        return xd;
    }
    
    public static void actualizarInv() throws SQLException{
        ReadData.leerpro();
        System.out.println(ReadData.listprod.toString());
        for(int i = 0; i<ReadData.listprod.size();i++){
            String x = ReadData.listprod.get(i);
            String lista[] = x.split(", ");
            System.out.println(x);
            System.out.println(lista.toString());
            String code = lista[0];
            lista[1] = lista[1].replace("{precio=", "");
            String precio = lista[1];
            lista[2] = lista[2].replace("code=", "");
            lista[3] = lista[3].replace("preciop=", "");
            lista[4] = lista[4].replace("cantidad=", "");
            String cantidad = lista[4];
            int can = parseInt(cantidad);
            lista[5] = lista[5].replace("nombre=", "");
            lista[5] = lista[5].replace("\"", "");
            lista[5] = lista[5].replace("}", "");
            String preciop = lista[3];
            String nombre = lista[5];
            ProductoDTO p = new ProductoDTO();
            p.setPrecio(parseInt(precio));
            p.setCode(code);
            p.setPreciop(parseInt(preciop));
            p.setCantidad(can);
            p.setNombre(nombre);
            if(!listp.contains(p)){
                listp.add(p);
        }
        }
        
    }
    public static void añadirinv(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Código","Nombre","Cantidad","Precio de proveedor", "Precio de venta"},listp.size());
        tabla.setModel(modelo);
        TableModel modelodatos = tabla.getModel();
        for(int i = 0; i <listp.size();i++){
                ProductoDTO prod = (ProductoDTO) listp.get(i);
                modelodatos.setValueAt(prod.getCode(), i, 0);
                modelodatos.setValueAt(prod.getNombre(), i, 1);
                modelodatos.setValueAt(prod.getCantidad(), i, 2);
                modelodatos.setValueAt(prod.getPreciop(), i, 3);
                modelodatos.setValueAt(prod.getPrecio(), i, 4);
            
        }
    }
    
    public static void buscar(String dato, JTable tabla){
        System.out.println(listp.toString());
        for(int i = 0; i<listp.size();i++){   
            ProductoDTO p = (ProductoDTO) listp.get(i);
            System.out.print(p.nombre.contains(dato));
            System.out.print(dato);
            System.out.print(p.nombre);
            if (p.code == dato || p.nombre.contains(dato)){
                DefaultTableModel modelo = new DefaultTableModel(new String[]{"Código","Nombre","Cantidad","Precio de proveedor", "Precio de venta"},listp.size());
                modelo.setRowCount(0);
                tabla.setModel(modelo);
                modelo.setRowCount(1);
                TableModel modelodatos = tabla.getModel();
                modelodatos.setValueAt(p.getCode(), 0, 0);
                modelodatos.setValueAt(p.getNombre(), 0, 1);
                modelodatos.setValueAt(p.getCantidad(), 0, 2);
                modelodatos.setValueAt(p.getPreciop(), 0, 3);
                modelodatos.setValueAt(p.getPrecio(), 0, 4);
            }
                
            
        }
    }
}



