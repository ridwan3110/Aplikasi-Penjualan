/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dao;

import com.ridwan.DBconnection.DBConnection;
import com.ridwan.Interface.KaryawanInterface;
import com.ridwan.Model.KaryawanModel;
import java.sql.Connection;
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
public class KaryawanDao implements KaryawanInterface {
    private Connection connection;
    
    public KaryawanDao() {
    connection = DBConnection.getConnection();
    }

    @Override
    public void InsertKaryawan(KaryawanModel karyawanModel) {
        PreparedStatement statement= null;
        String sql = "insert into karyawan (nama, status)"
                + "values (?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, karyawanModel.getNama());
            statement.setString(2, karyawanModel.getStatus());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void UpdateKaryawan(KaryawanModel karyawanModel) {
       PreparedStatement statement = null;
       String sql ="update karyawan set nama=?, status=? where karyawan_id=?";
        try {
            statement =connection.prepareStatement(sql);
            statement.setString(1, karyawanModel.getNama());
            statement.setString(2, karyawanModel.getStatus());
            statement.setInt(3,karyawanModel.getKaryawanId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
    }

    @Override
    public void DeleteKaryawan(KaryawanModel karyawanModel) {
   PreparedStatement statement = null;
   String sql = "delete from karyawan where karyawan_id=?";
   
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, karyawanModel.getKaryawanId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
   
    }

    @Override
    public KaryawanModel getById (int Id){
     PreparedStatement statement = null;
        ResultSet rs = null;
        KaryawanModel k = null;
     String sql = "select from karyawan where karyawan_id";
     
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            rs = statement.executeQuery();
            while (rs.next()){
                k = new KaryawanModel();
                k.setKaryawanId(rs.getInt("karyawan_id"));
                k.setNama(rs.getString("nama"));
                k.setStatus(rs.getString("status"));
                
            }return k;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
     
    }

    @Override
    public List<KaryawanModel> getKaryawanModels() {
    PreparedStatement statement = null;
    ResultSet rs = null;
    List list = new ArrayList();
    String sql = "select * from karyawan";
    
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                KaryawanModel k = new KaryawanModel();
                k.setKaryawanId(rs.getInt("karyawan_id"));
                k.setNama(rs.getString("nama"));
                k.setStatus(rs.getString("status"));
                list.add(k);
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

  

   
}
