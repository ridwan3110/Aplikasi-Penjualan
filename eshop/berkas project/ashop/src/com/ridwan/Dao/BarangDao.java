/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dao;

import com.ridwan.DBConnection.DBConnection;
import com.ridwan.DaoInterface.BarangInterface;
import com.ridwan.Model.Barang;
import com.ridwan.Model.Gudang;
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
public class BarangDao implements  BarangInterface{
    private Connection connection;
    private GudangDao gudangDao = new GudangDao();
    public BarangDao() {
        connection = DBConnection.getConnection();
    }
    

    @Override
    public boolean insert(Barang barang) {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into barang (nama, harga_beli, harga_jual, tanggal, jumlah, gudang_id)"
                + "values (?,?,?,?,?,?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, barang.getNama());
            statement.setDouble(2, barang.getHargabeli());
            statement.setDouble(3, barang.getHargajual());
            statement.setDate(4, new Date(barang.getTanggal().getTime()));
            statement.setInt(5,barang.getJumlah());
            statement.setInt(6, barang.getGudang().getGudangId());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            valid = false;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
     return valid;   
    }

    @Override
    public void update(Barang barang) {
        PreparedStatement statement = null;
        String sql = "update barang set nama=?, harga_beli=?, harga_jual=?, tanggal=?, jumlah=?, gudang_id=? "
                + "where barang_id=?";
        
        try {
            statement = connection.prepareStatement(sql);
             statement.setString(1, barang.getNama());
            statement.setDouble(2, barang.getHargabeli());
            statement.setDouble(3, barang.getHargajual());
            statement.setDate(4, new Date(barang.getTanggal().getTime()));
            statement.setInt(5, barang.getJumlah());
            statement.setInt(6, barang.getGudang().getGudangId());
            statement.setInt(7,barang.getBarangId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
    }

    @Override
    public boolean delete(Barang barang) {
        boolean valid = false;
      PreparedStatement statement = null;
      String sql = " delete from barang where barang_id=?";
      
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, barang.getBarangId());
            statement.executeUpdate();
            valid = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
        valid = false;
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
      return valid;
    }

    @Override
    public void kurangJumlahstock(int jumlah, Barang barang) {
      PreparedStatement statement=null;
      String sql = "update barang set jumlah=jumlah-? where barang_id=?";
      
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, jumlah);
            statement.setInt(2, barang.getBarangId());
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
    public boolean tambahJumlahstock(int jumlah, Barang barang) {
       PreparedStatement statement=null;
       boolean valid = false;
       String sql = "update barang set jumlah=jumlah+? where barang_id=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, jumlah);
            statement.setInt(2, barang.getBarangId());
            statement.executeUpdate();
            return valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
            return valid = false;
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
    public Barang getById(int id) {
       PreparedStatement statement=null;
        ResultSet rs = null;
        Barang b = null;
        String sql = "select * from barang where barang_id=?";
        try {
            statement =connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()){
                b = new Barang();
                b.setBarangId(rs.getInt("barang_id"));
                int gudangId = rs.getInt("gudang_id");
                Gudang byId = gudangDao.getById(gudangId);
                b.setGudang(byId);
                
                b.setHargabeli(rs.getDouble("harga_beli"));
                b.setHargajual(rs.getDouble("harga_jual"));
                b.setNama(rs.getString("nama"));
                b.setJumlah(rs.getInt("jumlah"));
                b.setTanggal(rs.getDate("tanggal"));
                
                
            }
            return b;
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
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public List<Barang> getBarang() {
        PreparedStatement statement=null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select * from barang";
        try {
            statement =connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                Barang b = new Barang();
                b.setBarangId(rs.getInt("barang_id"));
                int gudangId = rs.getInt("gudang_id");
                Gudang byId = gudangDao.getById(gudangId);
                b.setGudang(byId);
                
                b.setHargabeli(rs.getDouble("harga_beli"));
                b.setHargajual(rs.getDouble("harga_jual"));
                b.setNama(rs.getString("nama"));
                b.setJumlah(rs.getInt("jumlah"));
                b.setTanggal(rs.getDate("tanggal"));
                list.add(b);
                
                
            }
            return list;
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
            } if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    
}
