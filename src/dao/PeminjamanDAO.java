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
    
    public ResultSet getPeminjamanById(int idPinjam){
        String sql = "SELECT * FROM peminjaman WHERE id_pinjam = ?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idPinjam);
            
            return ps.executeQuery();
            
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return null;
    }
    
    public void updatePeminjaman(int idPinjam,
                                 int idAnggota,
                                 int idBuku,
                                 String tanggalPinjam,
                                 String tanggalKembali,
                                 String status){
        String sql = "UPDATE peminjaman SET "
                + "id_anggota=?, "
                + "id_buku=?, "
                + "tanggal_pinjam=?, "
                + "tanggal_kembali=?, "
                + "status=? "
                + "WHERE id_pinjam=?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idAnggota);
            ps.setInt(2, idBuku);
            ps.setString(3, tanggalPinjam);
            ps.setString(4, tanggalKembali);
            ps.setString(5, status);
            ps.setInt(6, idPinjam);
            
            ps.executeUpdate();
            
            System.out.println("Data berhasil diupdate!");
            
        }catch(Exception e){
            System.out.println("Error update: " + e.getMessage());
        }
    }
    
    public void deletePeminjaman(int idPinjam){
        
        String sql = "DELETE FROM peminjaman WHERE id_pinjam=?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idPinjam);
            
            ps.executeUpdate();
            
            System.out.println("Data berhasil dihapus!");
            
        }catch(Exception e){
            System.out.println("Error hapus: " + e.getMessage());
        }
            
    }
    
    public void kembalikanBuku(int idPinjam){
        
        String sql = "UPDATE peminjaman SET status='Dikembalikan' WHERE id_pinjam=?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
                    
            ps.setInt(1, idPinjam);
            
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println("Error pengembalian: " + e.getMessage());
        }
        
    }
    
    public ResultSet cariPeminjaman(String keyword){
        
        String sql = "SELECT p.id_pinjam, "
                + "a.nama, "
                + "b.judul, "
                + "p.tanggal_pinjam, "
                + "p.tanggal_kembali, "
                + "p.status "
                + "FROM peminjaman p "
                + "JOIN anggota a ON p.id_anggota = a.id_anggota "
                + "JOIN buku b ON p.id_buku = b.id_buku "
                + "WHERE a.nama LIKE ? "
                + "OR b.judul LIKE ?";
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            
            return ps.executeQuery();
            
        }catch(Exception e){
            System.out.println("Error cari: " + e.getMessage());
        }
        return null;
    }
    
    public int getTotalPeminjaman(){
        
        String sql = "SELECT COUNT(*) FROM peminjaman";
        
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
