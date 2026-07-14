/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */
public class UserDAO {
    
    private Connection conn;
    
    public UserDAO(){
        
        conn = Koneksi.getConnection();
    }
    
    public boolean login(String username, String password){
        
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            return rs.next();
            
        }catch(Exception e){
            e.printStackTrace();
            
            return false;
            
        }
    }
    
    public String getRole(String username){
        
        String sql = "SELECT role FROM user WHERE username=?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
                    
                    ps.setString(1, username);
                    
                    ResultSet rs = ps.executeQuery();
                    
                    if(rs.next()){
                        return rs.getString("role");
                    }             
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean tambahUser(String username, String password,String role){
        
        String sql = "INSERT INTO user(username,password,role) VALUES(?,?,?)";
        
        try{
        
            PreparedStatement ps = conn.prepareStatement(sql);
                
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
                
            return ps.executeUpdate() > 0;
                
        }catch(Exception e){
        
            e.printStackTrace();
            return false;
        
        }
    }
    
    public boolean usernameSudahAda(String username){
        
        String sql = "SELECT * FROM user WHERE username=?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
                    
                    ps.setString(1, username);
                    
                    ResultSet rs = ps.executeQuery();
                    
                    return rs.next();
                    
        }catch(Exception e){
            e.printStackTrace();
            
            return false;
            
        }
    }
    
    public boolean cekPasswordAdmin(String username, String password){
        
        String sql = "SELECT * FROM user WHERE username=? AND password=? AND role='Admin'";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
                    
                    ps.setString(1, username);
                    ps.setString(2, password);
                    
                    ResultSet rs = ps.executeQuery();
                    
                    return rs.next();
                    
        }catch(Exception e){
            
            e.printStackTrace();
            
            return false;
            
        }
    }
    
}
