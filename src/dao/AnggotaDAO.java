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
public class AnggotaDAO {
    
    Connection conn;
    
    public AnggotaDAO(){
        conn = Koneksi.getConnection();
    }
    
    public void insertAnggota(String nama, String alamat, String telepon){
        String sql = "INSERT INTO anggota(nama, alamat, telepon) VALUES (?, ?, ?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, telepon);
            
            int hasil = ps.executeUpdate();
            
            if(hasil > 0){
                System.out.println("Data anggota berhasil ditambahkan!");
            }
            
        }catch(Exception e){
            System.out.println("Error insert anggota: " + e.getMessage());
        }
    }
    
    public ResultSet getAllAnggota(){
        String sql = "SELECT * FROM anggota";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            return ps.executeQuery();
            
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return null;
    }
    
    public ResultSet cariAnggota(String keyword){
        
        String sql = "SELECT * FROM anggota WHERE nama LIKE ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            
            return ps.executeQuery();
            
        }catch(Exception e){
            System.out.println("Error Cari: " + e.getMessage());
        }
        
        return null;
    }
    
    public void deleteAnggota(int idAnggota){
        String sql = "DELETE FROM anggota WHERE id_anggota = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idAnggota);
            
            int hasil = ps.executeUpdate();
            
            if(hasil > 0){
                System.out.println("Data anggota berhasil dihapus!");
            }else{
                System.out.println("Data tidak ditemukan!");
            }
            
        }catch(Exception e){
            System.out.println("Error delete: " + e.getMessage());
        }
    }
    
    public void updateAnggota(int idAnggota, String nama, String alamat, String telepon){
        String sql = "UPDATE anggota SET nama=?, alamat=?, telepon=? WHERE id_anggota=?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, telepon);
            ps.setInt(4, idAnggota);
            
            int hasil = ps.executeUpdate();
            
            if(hasil > 0){
                System.out.println("Data anggota berhasil diupdate!");
            }else{
                System.out.println("Data tidak ditemukan!");
            }
            
        }catch(Exception e){
            System.out.println("Error update: " + e.getMessage());
        }
    }
    
    public int getTotalAnggota(){
        
        String sql = "SELECT COUNT(*) FROM anggota";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
    }
}
