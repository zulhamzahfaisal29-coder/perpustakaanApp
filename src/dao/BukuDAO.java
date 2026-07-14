/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BukuDAO {
    
    public ResultSet getAllBuku(){
        String sql = "SELECT * FROM buku";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return null;
    }
    
    public ResultSet cariBuku(String keyword){
        
        String sql = "SELECT * FROM buku WHERE judul LIKE ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            
            return ps.executeQuery();
            
        }catch(Exception e){
            System.out.println("Error Cari: " + e.getMessage());
        }
        
        return null;
    }

    Connection conn;

    public BukuDAO() {
        conn = Koneksi.getConnection();
    }

    public void insertBuku(String judul, String penulis, String penerbit, int tahun, int stok) {

        String sql = "INSERT INTO buku (judul, penulis, penerbit, tahun, stok) VALUES(?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, judul);
            ps.setString(2, penulis);
            ps.setString(3, penerbit);
            ps.setInt(4, tahun);
            ps.setInt(5, stok);

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                System.out.println("Data berhasil ditambahkan!");
            } 

        } catch (Exception e) {

            System.out.println("Error insert: " + e.getMessage());

        }
    }
    //READ (SELECT)
    
    public void tampilkanSemuaBuku(){
        String sql = "SELECT * FROM buku";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            boolean adaData = false;
            
            System.out.println("\n====================");
            
            while (rs.next()){
                adaData = true;
                
                System.out.println("ID       : " + rs.getInt("id_buku"));
                System.out.println("Judul    : " + rs.getString("judul"));
                System.out.println("Penulis  : " + rs.getString("penulis"));
                System.out.println("Penerbit : " + rs.getString("penerbit"));
                System.out.println("Tahun    : " + rs.getInt("tahun"));
                System.out.println("Stok     : " + rs.getInt("stok"));
                System.out.println("---------------------"); 
            }
            
            if(!adaData){
                    System.out.println("Belum ada data buku.");
            }
            
        }catch (Exception e){
            System.out.println("ERROR tampil data: " + e.getMessage());
        }
    }
    
//UPDATE
    
    public void updateStok(int idBuku, int stokBaru){
        String sql = "UPDATE buku SET stok = ? WHERE id_buku = ?";
    
        try {
        
            PreparedStatement ps = conn.prepareStatement(sql);
        
            ps.setInt(1, stokBaru);
            ps.setInt(2, idBuku);
        
            int hasil = ps.executeUpdate();
        
            if (hasil > 0){
                System.out.println("Data berhasil diupdate!");
            }else{
                System.out.println("ID buku tidak ditemukan!");
            }
        
        }catch (Exception e){
        System.out.println("ERROR update: " + e.getMessage());
        }
    }
    
    public void updateBuku(int id, String judul, String penulis, String penerbit, int tahun, int stok ){
        String sql = "UPDATE buku SET judul=?, penulis=?, penerbit=?, tahun=?, stok=? WHERE id_buku=?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, judul);
            ps.setString(2, penulis);
            ps.setString(3, penerbit);
            ps.setInt(4, tahun);
            ps.setInt(5, stok);
            ps.setInt(6, id);
            
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Error update: " + e.getMessage());
        }
    }


//DELETE

    public void deleteBuku(int idBuku) {
        
        String sql = "DELETE FROM buku WHERE id_buku = ?";
    
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
        
            ps.setInt(1, idBuku);
        
            int hasil = ps.executeUpdate();
        
            if (hasil > 0){
                System.out.println("Data berhasil dihapus!");   
            }else{
                System.out.println("ID buku tidak ditemukan!");
            }
        
        }catch (Exception e){
            System.out.println("Error delete: " + e.getMessage());
        }
    }
    
    public void KurangiStok(int idBuku){
        
        System.out.println("Kurangi stok buku ID: " +idBuku);
        
        String sql = "UPDATE buku SET stok = stok - 1 WHERE id_buku=?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idBuku);
            
            int hasil = ps.executeUpdate();
            
            System.out.println("Baris yang diupdate: " + hasil);
            
        }catch(Exception e){
            System.out.println("Error stok: "+ e.getMessage());
        }
    }
    
    public int getStok(int idBuku){
        
        String sql = "SELECT stok FROM buku WHERE id_buku=?";
         try{
             
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setInt(1, idBuku);
             
             ResultSet rs = ps.executeQuery();
             
             if(rs.next()){
                 return rs.getInt("stok");
             }
             
         }catch(Exception e){
             System.out.println("Error cek stok: " + e.getMessage());
         }
         return 0;
    }
    
    public void tambahStok(int idBuku){
        
        String sql = "UPDATE buku SET stok = stok + 1 WHERE id_buku=?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idBuku);
            
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println("Error tambah stok: " + e.getMessage());
        }
        
    }
    
    public int getTotalBuku(){
        
        String sql = "SELECT COUNT(*) FROM buku";
        
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
    
    

