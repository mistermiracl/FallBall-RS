/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.dao.impl;

import com.fallballrs.dao.PlayerDao;
import com.fallballrs.entity.PlayerData;
import com.fallballrs.util.DatabaseConnection;
import com.fallballrs.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class PlayerDaoImpl implements PlayerDao{
	
	public PlayerDaoImpl() {
	}
	
    @Override
    public boolean create(PlayerData.Player toInsert) {
        String sql = "INSERT INTO Player(PlayerName, PlayerScore, Level) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, toInsert.playerName);
            ps.setFloat(2, toInsert.playerScore);
            ps.setInt(3, toInsert.level);
            
            ps.executeUpdate();
            return true;
            
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
            return false;
        }
    }

    @Override
    public boolean update(PlayerData.Player toUpdate) {
        final String sql = "UPDATE Player SET PlayerName = ?, PlayerScore = ?, Level = ? WHERE PlayerId = ?";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, toUpdate.playerName);
            st.setFloat(2, toUpdate.playerScore);
            st.setInt(3, toUpdate.level);
            return true;
        } catch (SQLException x){
            System.out.printf("%s: %s\n", Util.DATABASE_ERROR, x);
            return false;
        }
    }

    @Override
    public boolean delete(Object id) {
        String sql = "DELETE FROM Player WHERE PlayerId = ?";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, (int)id);
            
            ps.executeUpdate();
            return true;
            
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
            return false;
        }
    }

    @Override
    public PlayerData.Player find(Object id) {
        PlayerData.Player player = null;
        String sql = "SELECT PlayerId, PlayerName, PlayerScore, Level FROM Player WHERE PlayerId = ?";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, id.toString());
            
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    player = new PlayerData.Player();
                    player.playerId = rs.getInt(1);
                    player.playerName = rs.getString(2);
                    player.playerScore = rs.getFloat(3);
                    player.level = rs.getInt(4);
                }
            }
            
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
        }
        
        return player;
    }

    @Override
    public List<PlayerData.Player> findAll() {
        List<PlayerData.Player> lPlayer = new ArrayList<>();
        PlayerData.Player player = null;
        String sql = "SELECT PlayerId, PlayerName, PlayerScore, Level FROM Player";
        try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                player = new PlayerData.Player();
                player.playerId = rs.getInt(1);
                player.playerName = rs.getString(2);
                player.playerScore = rs.getFloat(3);
                player.level = rs.getInt(4);
                lPlayer.add(player);
            }
            
        } catch(SQLException x){
            System.out.printf("Error en la base de datos: %s\n", x);
        }
        
        return lPlayer;
    }

}
