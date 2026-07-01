/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Anggota {
    private int idAnggota;
    private String nama;
    private String alamat;
    private String telepon;
    
    public int getIdAnggota(){
        return idAnggota;
    }
    
    public void SetIdAnggota(int idAnggota){
        this.idAnggota = idAnggota;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getAlamat(){
        return alamat;
    }
    
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    
    public String getTelepon(){
        return telepon;
    }
    
    public void setTelepon(String telepon){
        this.telepon = telepon;
    }
}
