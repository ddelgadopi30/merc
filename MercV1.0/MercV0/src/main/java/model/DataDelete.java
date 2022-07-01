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
import org.apache.commons.logging.Log;

/**
 *
 * @author HOME
 */
public class DataDelete {
    public static void delete(String objeto){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query query = ref.child("producto").orderByChild("nombre").equalTo(objeto);

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot Snapshot: ds.getChildren()) {
                    
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {
                
            }
    });
    }
    
  
}