/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.dao.impl;

import com.fallballrs.dao.DeviceInfoDao;
import com.fallballrs.dao.PlayerDao;


public final class DaoFactory {
    
    DaoFactory(){
    }
    
    static class DaoFactoryHolder{
        static DaoFactory INSTANCE = new DaoFactory();
    }
    
    public static DaoFactory getInstance(){
        return DaoFactoryHolder.INSTANCE;
    }
    
    public PlayerDao getPlayerDao(){
        return new PlayerDaoImpl();
    }
    
    public DeviceInfoDao getDeviceInfoDao(){
        return new DeviceInfoDaoImpl();
    }
    
}
