/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HOME
 */
public class ReadData{
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> listcont = new ArrayList<>();
    public static ArrayList<String> listprod = new ArrayList<>();
    public static ArrayList<String> listfac = new ArrayList<>();
    public static void leer(){
        
        DatabaseReference databaseReference = FirebaseConnection.firebaseDatabase.getReference("/");
        DatabaseReference childRef = databaseReference.child("1").child("usuarios");
        childRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot ds) {
                list.clear();
                for(DataSnapshot s : ds.getChildren()){
                    list.add((String) s.getValue().toString());
                    if (!listcont.contains((String) s.getKey())){
                        listcont.add((String) s.getKey());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                
                
            
    }
    public static void leerpro(){
        DatabaseReference databaseReference = FirebaseConnection.firebaseDatabase.getReference("/");
        DatabaseReference childRef = databaseReference.child("1").child("productos");
        childRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot ds) {
                listprod.clear();
                for(DataSnapshot s : ds.getChildren()){
                    String h = s.getKey().concat(", "+s.getValue().toString());
                    listprod.add(h);
                }
            }
            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                
                
            
    }
    
    public static void leerfac(){
        DatabaseReference databaseReference = FirebaseConnection.firebaseDatabase.getReference("/");
        DatabaseReference childRef = (DatabaseReference) databaseReference.child("1").child("facturacion");
        childRef.orderByChild("name").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot ds) {
                for(DataSnapshot s : ds.getChildren()){
                    String h =  s.getValue().toString()+", "+s.getKey();
                    listfac.add(h);
                }
            }
            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                
                
            
    }
    
}
