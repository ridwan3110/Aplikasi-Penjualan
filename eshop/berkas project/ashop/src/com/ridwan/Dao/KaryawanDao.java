/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dao;

import com.ridwan.DBConnection.DBConnection;
import com.ridwan.DaoInterface.KaryawanInterface;
import com.ridwan.Model.Karyawan;
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
public class KaryawanDao implements KaryawanInterface{
    private Connection connection;

    public KaryawanDao() {
    connection = DBConnection.getConnection();
    }
    

    @Override
    public boolean insert(Karyawan karyawan) {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into karyawan (username, password, nama, status, telepon, alamat)"
                + "values (?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, karyawan.getUsername());
            statement.setString(2, karyawan.getPassword());
            statement.setString(3, karyawan.getNama());
            statement.setString(4, karyawan.getStatus());
            statement.setString(5, karyawan.getTelepon());
            statement.setString(6, karyawan.getAlamat());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally{
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       return valid;
    }

    @Override
    public void update(Karyawan karyawan) {
       PreparedStatement statement=null;
       String sql = " update karyawan set username=?, password=?, nama=?, status=? telepon=?, alamat=?"
               + "where karyawan_id=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1, karyawan.getUsername());
            statement.setString(2, karyawan.getPassword());
            statement.setString(3, karyawan.getNama());
            statement.setString(4, karyawan.getStatus());
            statement.setString(5, karyawan.getTelepon());
            statement.setString(6, karyawan.getAlamat());
            statement.setInt(7, karyawan.getKaryawanId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
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
    public boolean delete(Karyawan karyawan) {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = " delete from karyawan where karyawan_id=?";
        
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1, karyawan.getKaryawanId());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
      return valid;  
    }

    @Override
    public Karyawan getById(int id) {
       PreparedStatement statement = null;
        ResultSet rs = null;
        Karyawan k = null;
       String sql = "select * from karyawan where karyawan_id=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                k = new Karyawan();
                k.setKaryawanId(rs.getInt("karyawan_id"));
                k.setUsername(rs.getString("username"));
                k.setPassword(rs.getString("password"));
                k.setNama(rs.getString("nama"));
                k.setStatus(rs.getString("status"));
                k.setTelepon(rs.getString("telepon"));
                k.setAlamat(rs.getString("alamat"));
                
            }return k ;
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
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
    }

    @Override
    public List<Karyawan> getKaryawan() {
         PreparedStatement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
       String sql = "select * from karyawan";
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                Karyawan k = new Karyawan();
                k.setKaryawanId(rs.getInt("karyawan_id"));
                k.setUsername(rs.getString("username"));
                k.setPassword(rs.getString("password"));
                k.setNama(rs.getString("nama"));
                k.setStatus(rs.getString("status"));
                k.setTelepon(rs.getString("telepon"));
                k.setAlamat(rs.getString("alamat"));
                list.add(k);
            }return list ;
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
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
    }

    @Override
    public Karyawan login(String username, String password) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Karyawan k = null;
       String sql = "select * from karyawan where username=? and password=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();
            while (rs.next()){
                k = new Karyawan();
                k.setKaryawanId(rs.getInt("karyawan_id"));
                k.setUsername(rs.getString("username"));
                k.setPassword(rs.getString("password"));
                k.setNama(rs.getString("nama"));
                k.setStatus(rs.getString("status"));
                k.setTelepon(rs.getString("telepon"));
                k.setAlamat(rs.getString("alamat"));
                
            }return k ;
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
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
    }

    @Override
    public boolean ubahPassword(String oldUsername, String oldPassword, String newUsername, String newPassword) {
    PreparedStatement statement=null;
    boolean valid = false;
       String sql = " update karyawan set username=?, password=? where username=? and password=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1, newUsername);
            statement.setString(2, newPassword);
            statement.setString(3, oldUsername);
            statement.setString(4, oldPassword);
            statement.executeUpdate();
            return valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
            return valid = false;
        }finally{
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    
    
}
