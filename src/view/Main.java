/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.BukuDAO;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static BukuDAO dao = new BukuDAO();
    
    public static void main(String[] args) {

        int pilihan;
        
        do {
            menu();
            
            while(true){
                
                if(input.hasNextInt()){
                    pilihan = input.nextInt();
                    input.nextLine();
                    break;
                }
                
                System.out.println("Masukan angka yang valid!");
                input.nextLine();
                System.out.print("Pilih: ");
            }
            
            switch(pilihan){
                
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    break;
                
                case 1:
                    dao.tampilkanSemuaBuku();
                    break;
                    
                case 2:
                    tambahBuku();
                    break;
                    
                case 3:
                    updateStok();
                    break;
                    
                case 4:
                    hapusBuku();
                    break;
                    
                case 5:
                    System.out.print("Masukan ID atau judul: ");
                    String keyword = input.nextLine();
                    
                    if(keyword.trim().isEmpty()){
                        System.out.println("Keyword tidak boleh kosong!");
                        break;
                    }
                    
                    dao.cariBuku(keyword);
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
            
        }while(pilihan !=0);
    }
    
    //==================MENU=======================
    static void menu(){
        System.out.println("\n===== MENU PERPUSTAKAAN =====");
        System.out.println("1. Lihat Semua Buku");
        System.out.println("2. Tambah Buku");
        System.out.println("3. Update Stok");
        System.out.println("4. Hapus Buku");
        System.out.println("5. Cari Buku (ID / Judul)");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
    }

    //===================TAMBAH====================
    static void tambahBuku(){
        
        String judul = inputJudul();
        String penulis = inputPenulis();
        String penerbit = inputPenerbit();
        int tahun = inputTahun();
        int stok = inputStok();
        
        dao.insertBuku(judul, penulis, penerbit, tahun, stok);
        
    }
    
    //===================UPDATE=====================
    static void updateStok(){
        System.out.print("ID buku: ");
        
        while(!input.hasNextInt()){
            System.out.println("ID harus berupa angka!");
            input.nextLine();
            System.out.print("ID buku: ");
        }
        
        int id = input.nextInt();
        input.nextLine();
        
        int stok = inputStok();
        
        dao.updateStok(id, stok);
    }
    
    //===================DELETE====================
    static void hapusBuku(){
        
        System.out.print("ID Buku: ");
        
        while(!input.hasNextInt()){
            System.out.println("ID harus berupa angka!");
            input.nextLine();
            System.out.print("ID buku: ");
        }
        
        int id = input.nextInt();
        input.nextLine();
        
        dao.deleteBuku(id);
    }
    
    //===================VALIDATION FUNCTIONS======
    static String inputJudul(){
        while(true){
            System.out.print("Judul: ");
            String data = input.nextLine();
            
            if(!data.trim().isEmpty()) return data;
            
            System.out.println("Judul tidak boleh kosong");
        }
    }
    
    static String inputPenulis(){
        while(true){
            System.out.print("Penulis: ");
            String data = input.nextLine();
            
            if(!data.trim().isEmpty()) return data;
            
            System.out.println("Penulis tidak boleh kosong!");
        }
    }
    
    static String inputPenerbit(){
        while(true){
            System.out.print("Penerbit: ");
            String data = input.nextLine();
            
            if(!data.trim().isEmpty()) return data;
            
            System.out.println("Penerbit tidak boleh kosong!");
        }
    }
    
    static int inputTahun(){
        while(true){
            
            System.out.print("Tahun: ");
            
            if((!input.hasNextInt())){
                System.out.println("Masukan tahun berupa angka!");
                input.nextLine();
                continue;
            }
            
            int tahun = input.nextInt();
            input.nextLine();
            
            if(tahun >= 1900 && tahun <= 2026){ 
                return tahun;
            }
            
            System.out.println("Tahun harus antara 1900 - 2026.");
        }
    }
    
    static int inputStok(){
        while(true){
            
            System.out.print("Stok: ");
            
            if(!input.hasNextInt()){
                System.out.println("Masukan stok berupa angka!");
                input.nextLine();
                continue;
            }
            
            int stok = input.nextInt();
            input.nextLine();
            
            if(stok >= 0){
                return stok;
            }
            
            System.out.println("Stok tidak boleh negatif!");
        }
    }
}
  