/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dao;

import com.ridwan.DBConnection.DBConnection;
import com.ridwan.DaoInterface.GudangInterface;
import com.ridwan.Model.Gudang;
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
public class GudangDao implements GudangInterface{
    private Connection connection;

    public GudangDao() {
    connection = DBConnection.getConnection();
            
    }
    

    @Override
    public boolean insert(Gudang gudang) {
        boolean valid = false;
       PreparedStatement statement = null;
        String sql = "insert into gudang (nama, keterangan) values (?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, gudang.getNama());
            statement.setString(2, gudang.getKeterangan());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        
       return valid; 
    }

    @Override
    public void update(Gudang gudang) {
       PreparedStatement statement = null;
        String sql = "update gudang set nama=?, keterangan=? where gudang_id=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, gudang.getNama());
            statement.setString(2, gudang.getKeterangan());
            statement.setInt(3, gudang.getGudangId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public boolean delete(Gudang gudang) {
        boolean valid = false;
       PreparedStatement statement = null;
        String sql = "delete from gudang where gudang_id=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, gudang.getGudangId());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Gudang getById(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Gudang g =null;
        String sql = "select * from gudang where gudang_id=?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                g = new Gudang();
                g.setGudangId(rs.getInt("gudang_id"));
                g.setNama(rs.getString("nama"));
                g.setKeterangan(rs.getString("keterangan"));
                
            }return g;
        } catch (SQLException ex) {
            Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                if (rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        
    }
    }

    @Override
    public List<Gudang> getGudangs() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select * from gudang";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
               Gudang g = new Gudang();
                g.setGudangId(rs.getInt("gudang_id"));
                g.setNama(rs.getString("nama"));
                g.setKeterangan(rs.getString("keterangan"));
                list.add(g);
                
            }return list;
        } catch (SQLException ex) {
            Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                if (rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(GudangDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        
    }
    }
    
}
