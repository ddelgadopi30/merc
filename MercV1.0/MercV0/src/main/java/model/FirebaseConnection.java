/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import viewModel.FacturaDTO;
import viewModel.ProductoDTO;
import viewModel.ProductopushDTO;
import viewModel.usuarioDTO;

/**
 *
 * @author HOME
 */
public class FirebaseConnection {
    public static FirebaseDatabase firebaseDatabase;
    public static Firestore db;
    public static void main(String[] args){
        try {
            conectar();
        } catch (IOException ex) {
            Logger.getLogger(FirebaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void conectar() throws FileNotFoundException, IOException {

        FileInputStream serviceAccount;
        try {

            serviceAccount = new FileInputStream("C:\\Users\\edson\\Downloads\\merc.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://merc-35f51-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            firebaseDatabase = FirebaseDatabase.getInstance();
            System.out.println("éxito");
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }
    public static void saveUsingPush(usuarioDTO usuario) {
        if (usuario != null) {
            
            /* Get database root reference */
            DatabaseReference databaseReference = firebaseDatabase.getReference("/");
            
            /* Get existing child or will be created new child. */
            DatabaseReference childReference = databaseReference.child("1").child("usuarios");

            /**
             * The Firebase Java client uses daemon threads, meaning it will not prevent a process from exiting.
             * So we'll wait(countDownLatch.await()) until firebase saves record. Then decrement `countDownLatch` value
             * using `countDownLatch.countDown()` and application will continues its execution.
             */
            CountDownLatch countDownLatch = new CountDownLatch(1);
            
            /**
             * push()
             * Add to a list of data in the database. Every time you push a new node onto a list, 
             * your database generates a unique key, like items/unique-item-id/data
             */
            
            
            childReference.child(usuario.getNit()).setValue(usuario, new DatabaseReference.CompletionListener() {
                
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    System.out.println("Registro guardado!");
                    // decrement countDownLatch value and application will be continues its execution.
                    countDownLatch.countDown();
                }
            });
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void saveUsingPushP(ProductoDTO producto) {
        if (producto != null) {
            
            /* Get database root reference */
            DatabaseReference databaseReference = firebaseDatabase.getReference("/");
            
            /* Get existing child or will be created new child. */
            DatabaseReference childReference = databaseReference.child("1").child("productos");

            /**
             * The Firebase Java client uses daemon threads, meaning it will not prevent a process from exiting.
             * So we'll wait(countDownLatch.await()) until firebase saves record. Then decrement `countDownLatch` value
             * using `countDownLatch.countDown()` and application will continues its execution.
             */
            CountDownLatch countDownLatch = new CountDownLatch(1);
            
            /**
             * push()
             * Add to a list of data in the database. Every time you push a new node onto a list, 
             * your database generates a unique key, like items/unique-item-id/data
             */
            childReference.child(producto.getCode()).setValue(producto, new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    System.out.println("Registro guardado!");
                    // decrement countDownLatch value and application will be continues its execution.
                    countDownLatch.countDown();
                }
            });
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void saveUsingPushP2(ProductopushDTO producto2) {
        if (producto2 != null) {
            
            /* Get database root reference */
            DatabaseReference databaseReference = firebaseDatabase.getReference("/");
            
            /* Get existing child or will be created new child. */
            DatabaseReference childReference = databaseReference.child("1").child("nombresProductos");

            /**
             * The Firebase Java client uses daemon threads, meaning it will not prevent a process from exiting.
             * So we'll wait(countDownLatch.await()) until firebase saves record. Then decrement `countDownLatch` value
             * using `countDownLatch.countDown()` and application will continues its execution.
             */
            CountDownLatch countDownLatch = new CountDownLatch(1);
            
            /**
             * push()
             * Add to a list of data in the database. Every time you push a new node onto a list, 
             * your database generates a unique key, like items/unique-item-id/data
             */
            childReference.child(producto2.getNombre()).setValue(producto2.getCantidad(), new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    System.out.println("Registro guardado!");
                    // decrement countDownLatch value and application will be continues its execution.
                    countDownLatch.countDown();
                }
            });
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void saveUsingPushF(ProductoDTO producto) {
        if (producto != null) {
            
            /* Get database root reference */
            DatabaseReference databaseReference = firebaseDatabase.getReference("/");
            
            /* Get existing child or will be created new child. */
            DatabaseReference childReference = databaseReference.child("factura");

            /**
             * The Firebase Java client uses daemon threads, meaning it will not prevent a process from exiting.
             * So we'll wait(countDownLatch.await()) until firebase saves record. Then decrement `countDownLatch` value
             * using `countDownLatch.countDown()` and application will continues its execution.
             */
            CountDownLatch countDownLatch = new CountDownLatch(1);
            
            /**
             * push()
             * Add to a list of data in the database. Every time you push a new node onto a list, 
             * your database generates a unique key, like items/unique-item-id/data
             */
            childReference.push().setValue(producto, new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    System.out.println("Registro guardado!");
                    // decrement countDownLatch value and application will be continues its execution.
                    countDownLatch.countDown();
                }
            });
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    
}
