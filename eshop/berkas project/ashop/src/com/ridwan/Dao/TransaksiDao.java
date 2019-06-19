/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dao;

import com.ridwan.DBConnection.DBConnection;
import com.ridwan.DaoInterface.TransaksiInterface;
import com.ridwan.Model.Barang;
import com.ridwan.Model.BarangTransaksi;
import com.ridwan.Model.Karyawan;
import com.ridwan.Model.ReportTransaksi;
import com.ridwan.Model.Transaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ciwong
 */
public class TransaksiDao implements TransaksiInterface{
    private Connection connection;
    private KaryawanDao karyawanDao = new KaryawanDao();
    private BarangDao barangDao = new BarangDao();

    public TransaksiDao() {
    connection = DBConnection.getConnection();
    }
    
    
    

    @Override
    public boolean insertTransaksi(Transaksi transaksi) {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into transaksi (transaksi_id, ppn, tanggal, karyawan_id) values (?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, transaksi.getTransaksiId());
            statement.setDouble(2, transaksi.getPpn());
            statement.setDate(3, new java.sql.Date(transaksi.getTanggal().getTime()));
            statement.setInt(4, transaksi.getKaryawan().getKaryawanId());
            statement.executeUpdate();
            
            //proses insert ke barang transaksi
            
            String TRANASKSI_SQL = "insert into barang_transaksi (barang_id, transaksi_id, jumlah, harga)"
                    + "values (?,?,?,?)";
            List<BarangTransaksi> barangTransaksi = transaksi.getBarangTransaksi();
            int validJumlah = 0;
            for (BarangTransaksi transaksiBarang : barangTransaksi){
               statement = connection.prepareStatement(TRANASKSI_SQL);
               statement.setInt(1, transaksiBarang.getBarang().getBarangId());
               statement.setInt(2, transaksiBarang.getTransaksi().getTransaksiId());
               statement.setInt(3, transaksiBarang.getJumlah());
               statement.setDouble(4, transaksiBarang.getHarga());
               statement.executeUpdate();
               
               //lakukan update stock
                 barangDao = new BarangDao();
               if (transaksiBarang.getBarang().getJumlah()<transaksiBarang.getJumlah()){
                   JOptionPane.showMessageDialog(null, "jumlah" +transaksiBarang.getBarang().getNama()+ "tidak mencukupi");
              validJumlah=transaksiBarang.getBarang().getJumlah()-transaksiBarang.getJumlah();
              valid=false;
              
               }else {
                   barangDao.kurangJumlahstock(transaksiBarang.getJumlah(), transaksiBarang.getBarang());
               }
            }
            //lakukan validasi stock (jika stock tidak minus maka simpan transaksi)
            if(validJumlah<0){
                connection.rollback();
                connection.setAutoCommit(true);
            }else {
                connection.commit();
                connection.setAutoCommit(true);
                valid = true;
            }
            
        } catch (SQLException ex) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       return valid; 
    }

    @Override
    public List<ReportTransaksi> getTransaksi() {
       PreparedStatement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select * from transaksi inner join barang_transaksi on "
                + "(transaksi.transaksi_id=barang_transaksi.transaksi_id)";
        try {
            statement = connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                ReportTransaksi rt = new ReportTransaksi();
                Transaksi t = new Transaksi();
                t.setTransaksiId(rs.getInt("transaksi.transaksi_id"));
                t.setTanggal(rs.getDate("transaksi.tanggal"));
                int idKaryawan = rs.getInt("transaksi.karyawan_id");
                Karyawan byId = karyawanDao.getById(idKaryawan);
                t.setKaryawan(byId);
                t.setPpn(rs.getDouble("transaksi.ppn"));
                rt.setTransaksi(t);
                BarangTransaksi bt = new BarangTransaksi();
                int barangId = rs.getInt("barang_transaksi.barang_id");
                Barang byId1 = barangDao.getById(barangId);
                bt.setBarang(byId1);
                bt.setHarga(rs.getDouble("barang_transaksi.harga"));
                bt.setJumlah(rs.getInt("barang_transaksi.jumlah"));
                rt.setBarangTransaksi(bt);
                list.add(rt);
                
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        
    }

    @Override
    public List<ReportTransaksi> getTransaksi(Date tglMulai, Date tglAkhir) {
      PreparedStatement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select * from transaksi inner join barang_transaksi on "
                + "(transaksi.transaksi_id=barang_transaksi.transaksi_id)"
                + "where (transaksi.tanggal>=?) and (transaksi.tanggal<=?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(2, new java.sql.Date(tglAkhir.getTime()));
            rs=statement.executeQuery();
            while (rs.next()){
                ReportTransaksi rt = new ReportTransaksi();
                Transaksi t = new Transaksi();
                t.setTransaksiId(rs.getInt("transaksi.transaksi_id"));
                t.setTanggal(rs.getDate("transaksi.tanggal"));
                int idKaryawan = rs.getInt("transaksi.karyawan_id");
                Karyawan byId = karyawanDao.getById(idKaryawan);
                t.setKaryawan(byId);
                t.setPpn(rs.getDouble("transaksi.ppn"));
                rt.setTransaksi(t);
                BarangTransaksi bt = new BarangTransaksi();
                int barangId = rs.getInt("barang_transaksi.barang_id");
                Barang byId1 = barangDao.getById(barangId);
                bt.setBarang(byId1);
                bt.setHarga(rs.getDouble("barang_transaksi.harga"));
                bt.setJumlah(rs.getInt("barang_transaksi.jumlah"));
                rt.setBarangTransaksi(bt);
                list.add(rt);
                
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<ReportTransaksi> getTransaksi(Transaksi transaksi) {
     PreparedStatement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select * from transaksi inner join barang_transaksi on "
                + "(transaksi.transaksi_id=barang_transaksi.transaksi_id)"
                + "where transaksi.transaksi_id=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, transaksi.getTransaksiId());
            rs=statement.executeQuery();
            while (rs.next()){
                ReportTransaksi rt = new ReportTransaksi();
                Transaksi t = new Transaksi();
                t.setTransaksiId(rs.getInt("transaksi.transaksi_id"));
                t.setTanggal(rs.getDate("transaksi.tanggal"));
                int idKaryawan = rs.getInt("transaksi.karyawan_id");
                Karyawan byId = karyawanDao.getById(idKaryawan);
                t.setKaryawan(byId);
                t.setPpn(rs.getDouble("transaksi.ppn"));
                rt.setTransaksi(t);
                BarangTransaksi bt = new BarangTransaksi();
                int barangId = rs.getInt("barang_transaksi.barang_id");
                Barang byId1 = barangDao.getById(barangId);
                bt.setBarang(byId1);
                bt.setHarga(rs.getDouble("barang_transaksi.harga"));
                bt.setJumlah(rs.getInt("barang_transaksi.jumlah"));
                rt.setBarangTransaksi(bt);
                list.add(rt);
                
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public int setTransaksiId() {
         PreparedStatement statement = null;
        ResultSet rs = null;
        int kode=0;
        String sql = "SELECT max(transaksi.transaksi_id) as maks from transaksi;";
        try {
            statement = connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                kode = rs.getInt("maks");
                
                
            }
            return kode;
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
            return kode;
        }
        finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
    }
    
    
}
