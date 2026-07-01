/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Peminjaman {
    
    private int idPinjam;
    private int idAnggota;
    private int idBuku;
    private String tanggalPinjam;
    private String tanggalKembali;
    private String status;
    
    public Peminjaman(){
    }
    
    public int getIdPinjam(){
        return idPinjam;
    }
    
    public void setIdPinjam(int idPinjam){
        this.idPinjam = idPinjam;
    }
    
    public int getIdAnggota(){
        return idAnggota;
    }
    
    public void setIdAnggota(int idAnggota){
        this.idAnggota = idAnggota;
    }
    
    public int getIdBuku(){
        return idBuku;
    }
    
    public void setIdBuku(int idBuku){
        this.idBuku = idBuku;
    }
    
    public String getTanggalPinjam(){
        return tanggalPinjam;
    }
    
    public void setTanggalPinjam(String tanggalPinjam){
        this.tanggalPinjam = tanggalPinjam;
    }
    
    public String getTanggalKembali(){
        return tanggalKembali;
    }
    
    public void setTanggalKembali(String tanggalKembali){
        this.tanggalKembali = tanggalKembali;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
}
