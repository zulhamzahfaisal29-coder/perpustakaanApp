/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author Lenovo
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    public static Connection getConnection(){
        try {
            String url =
                    "jdbc:mysql://localhost:3306/perpustakaan";
            String user = "root";
            
            String password = "admin123";
            
            Connection conn =
                    DriverManager.getConnection(
                            url,
                            user,
                            password
                    );
            System.out.println("koneksi Berhasil");
            return conn;
              
        } catch (Exception e) {
            
            System.out.println(
                    "Koneksi Gagal :" 
                    + e.getMessage()
            );
            
            return null;
        }
    }
    
}
