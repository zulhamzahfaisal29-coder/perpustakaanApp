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
public class PeminjamanDAO {
    
    Connection conn;
    
    public PeminjamanDAO(){
        conn = Koneksi.getConnection();
    }
    
    public void insertPeminjaman(int idAnggota,
                                 int idBuku,
                                 String tanggalPinjam,
                                 String tanggalKembali,
                                 String status){
        String sql = "INSERT INTO peminjaman(id_anggota, id_buku, tanggal_pinjam, tanggal_kembali, status) VALUES(?,?,?,?,?)";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idAnggota);
            ps.setInt(2, idBuku);
            ps.setString(3, tanggalPinjam);
            ps.setString(4, tanggalKembali);
            ps.setString(5, status);
            
            int hasil = ps.executeUpdate();
            
            if(hasil > 0){
                System.out.println("Data peminjaman berhasil ditambahkan!");
            }
                     
        }catch(Exception e){
            System.out.println("Error insert: " + e.getMessage());
        }
    }
    
    public ResultSet getAllPeminjaman(){
        
        String sql =
                "SELECT p.id_pinjam, " +
                "a.nama, " +
                "b.judul, " +
                "p.tanggal_pinjam, " +
                "p.tanggal_kembali, " +
                "p.status " +
                "FROM peminjaman p " +
                "JOIN anggota a on p.id_anggota = a.id_anggota " +
                "JOIN buku b on p.id_buku = b.id_buku";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            return ps.executeQuery();
            
        }catch(Exception e){
            System.out.println("Error tampil data: " + e.getMessage());
            
        }
        return null;
    
    }
}
