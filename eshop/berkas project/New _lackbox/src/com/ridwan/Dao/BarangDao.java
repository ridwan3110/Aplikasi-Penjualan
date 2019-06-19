/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dao;

import com.ridwan.DBconnection.DBConnection;
import com.ridwan.Interface.BarangInterface;
import com.ridwan.Interface.KaryawanInterface;
import com.ridwan.Model.BarangModel;
import com.ridwan.Model.KaryawanModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ciwong
 */
public class BarangDao implements BarangInterface{
    
    private Connection connection;
    private KaryawanDao karyawanDao =  new KaryawanDao();

    public BarangDao() {
    connection = DBConnection.getConnection();
    }
    

    @Override
    public void InsertBarang(BarangModel barangModel) {
        PreparedStatement statement = null;
        String sql = "insert into barang (Barang_ID, nama_barang, serial_number, tanggal_keluar, untuk_perangkat, stasiun, karyawan_id)"
                + "values (?,?,?,?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, barangModel.getBarangId());
            statement.setString(2, barangModel.getNamaBarang());
            statement.setString(3, barangModel.getSerialNumber());
            statement.setDate(4, new Date(barangModel.getTanggalKeluar().getTime()));
            statement.setString(5, barangModel.getUntukPerangkat());
            statement.setString(6, barangModel.getStasiun());
            statement.setInt(7, barangModel.getKaryawan().getKaryawanId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    }

    @Override
    public void UpdateBarang(BarangModel barangModel) {
    PreparedStatement statement = null;
    String sql = "update barang set nama_barang=?, serial_number=?, tanggal_keluar=?, untuk_perangkat=?, stasiun=?, karyawan_id=? "
            + "where Barang_ID=?";
    
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, barangModel.getNamaBarang());
            statement.setString(2, barangModel.getSerialNumber());
            statement.setDate(3, new Date(barangModel.getTanggalKeluar().getTime()));
            statement.setString(4, barangModel.getUntukPerangkat());
            statement.setString(5, barangModel.getStasiun());
            statement.setInt(6, barangModel.getKaryawan().getKaryawanId());
            statement.setInt(7, barangModel.getBarangId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }

    @Override
    public void DeleteBarang(BarangModel barangModel) {
      
        PreparedStatement statement = null;
        String sql = "delete from barang where Barang_ID=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, barangModel.getBarangId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public BarangModel getById (int id) {
       PreparedStatement statement = null;
        ResultSet rs = null;
        BarangModel b = null;
       String sql = "select from barang where Barang_ID";
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                b = new BarangModel();
                b.setBarangId(rs.getInt("Barang_ID"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setSerialNumber(rs.getString("serial_number"));
                b.setTanggalKeluar(rs.getDate("tanggal_keluar"));
                b.setUntukPerangkat(rs.getString("untuk_perangkat"));
                b.setStasiun(rs.getString("stasiun"));
                int KaryawanId = rs.getInt("karyawan_id");
                KaryawanModel karyawanModel = karyawanDao.getById(KaryawanId);
                b.setKaryawan(karyawanModel);
            }return b;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally { 
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
            
        }
       
    }

    @Override
    public List<BarangModel> getBarangModels() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql =" select * from barang ";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
            while (rs.next()){
                BarangModel b = new BarangModel();
                b.setBarangId(rs.getInt("Barang_ID"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setSerialNumber(rs.getString("serial_number"));
                b.setTanggalKeluar(rs.getDate("tanggal_keluar"));
                b.setUntukPerangkat(rs.getString("untuk_perangkat"));
                b.setStasiun(rs.getString("stasiun"));
                int KaryawanId = rs.getInt("karyawan_id");
                KaryawanModel karyawanModel = karyawanDao.getById(KaryawanId);
                b.setKaryawan(karyawanModel);
                list.add(b);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
       
    }

   
}

    