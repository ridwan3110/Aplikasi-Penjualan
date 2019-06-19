/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Dao;

import com.ridwan.DBConnection.DBConnection;
import com.ridwan.DaoInterface.BarangInterface;
import com.ridwan.DaoInterface.KembaliInterface;
import com.ridwan.Model.Barang;
import com.ridwan.Model.kembali;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ciwong
 */
public class KembaliDao implements KembaliInterface{
    private Connection connection;
    private BarangInterface barangDao = new BarangDao();

    public KembaliDao() {
    connection = DBConnection.getConnection();
    }

    @Override
    public boolean insertReturn(kembali kembalian) {
        boolean valid = false;
        BarangInterface barangInterface ;
        PreparedStatement statement = null;
        String insert_return ="insert into kembali (barang_id, jumlah, tanggal, keterangan)"
                + "values (?,?,?,?)";
        
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insert_return);
            statement.setInt(1,kembalian.getBarang().getBarangId());
            statement.setInt(2, kembalian.getJumlah());
            statement.setDate(3, new Date(kembalian.getTanggal().getTime()));
            statement.setString(4, kembalian.getKeterangan());
            statement.executeUpdate();
            
            barangInterface = new BarangDao();
            barangInterface.tambahJumlahstock(kembalian.getJumlah(),kembalian.getBarang());
            connection.commit();
            connection.setAutoCommit(true);
            return valid = true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                Logger.getLogger(KembaliDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(KembaliDao.class.getName()).log(Level.SEVERE, null, ex1);
            }return valid = false;
        
    }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KembaliDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    
}

    @Override
    public List<kembali> getReturn() {
    PreparedStatement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        
        String sql = "select * from kembali";
        
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                kembali k = new kembali();
                k.setReturnId(rs.getInt("return_id"));
                k.setJumlah(rs.getInt("jumlah"));
                k.setTanggal(rs.getDate("tanggal"));
                k.setKeterangan(rs.getString("keterangan"));
                int idBarang = rs.getInt("barang_id");
                Barang byId = barangDao.getById(idBarang);
                k.setBarang(byId);
                list.add(k);
            }return  list;
        } catch (SQLException ex) {
            Logger.getLogger(KembaliDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KembaliDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KembaliDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
