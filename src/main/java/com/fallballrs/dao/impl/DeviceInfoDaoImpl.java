/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.dao.impl;

import com.fallballrs.dao.DeviceInfoDao;
import com.fallballrs.entity.DeviceInfo;
import com.fallballrs.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
class DeviceInfoDaoImpl implements DeviceInfoDao {

    @Override
    public boolean create(DeviceInfo toInsert) {
        String sql = "INSERT INTO DeviceInfo (DeviceId, Platform, OS) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, toInsert.deviceId);
            ps.setString(2, toInsert.platform);
            ps.setString(3, toInsert.os);
            
            ps.executeUpdate();
            
            return true;
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
            return false;
        }
    }

    @Override
    public boolean update(DeviceInfo toUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object id) {
        String sql = "TRUNCATE TABLE DeviceInfo";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.executeUpdate();
            
            return true;
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
            return false;
        }     
    }

    @Override
    public DeviceInfo find(Object id) {
        DeviceInfo device = null;
        
        String sql = "SELECT DeviceId, Platform, OS FROM DeviceInfo WHERE DeviceId = ?";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, id.toString());
            
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    device = new DeviceInfo(rs.getString(1), rs.getString(2), rs.getString(3));
                }
            }
            
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
        }
        return device;
    }

    @Override
    public List<DeviceInfo> findAll() {
        List<DeviceInfo> lDevices = new ArrayList<>();
        DeviceInfo device = null;
        
        String sql = "SELECT DeviceId, Platform, OS FROM DeviceInfo";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                device = new DeviceInfo(rs.getString(1), rs.getString(2), rs.getString(3));
                lDevices.add(device);
            }
            
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
        }
        return lDevices;
    }
    
}
