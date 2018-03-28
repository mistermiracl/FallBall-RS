/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.service;

import com.fallballrs.dao.DeviceInfoDao;
import com.fallballrs.dao.PlayerDao;
import com.fallballrs.dao.impl.DaoFactory;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
//import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;


//NEEDED, CANNOT BE BLANK, IF BLANK USE web.xml INSTEAD TO SPECIFY ROUTE, OTHERWISE CONFIGURE IN web.xml
@javax.ws.rs.ApplicationPath("/api")//web resources
public class ApplicationConfig extends ResourceConfig {//EXTEND FROM ResourceConfig INSTEAD OF Application(default) SINCE IT PROVIDES UTILY METHODS TO USE DEPENDENCY INJECTION

    public ApplicationConfig() {
        
        //LOOKS FOR RESOURCES(ENDPOINTS) ON SPECIFIED PACKAGES
        packages(this.getClass().getPackage().getName());
        //GLASSFISH SERVER INCLUDES MOXY AND JACKSON AS JSONBODYWRITERS, SINCE GLASSFISH HAS SERIOUS BUGS IN THIS VERSION MOXY(DEFAULT) DOES NOT WORK SO WE NEED TO MANUALLY SETUP JACKSON
        register(JacksonFeature.class);
        //MANUALLY REGISTERS RESOURCES IN THIS CASE A BINDER FOR DI
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                //BIND THE IMPLEMENTATION TO THE INTERFACE FOR DI
                this.bind(deviceInfoDao()).to(DeviceInfoDao.class);
                this.bind(playerDao()).to(PlayerDao.class);
            }
        });
    }

    private DeviceInfoDao deviceInfoDao(){
        return DaoFactory.getInstance().getDeviceInfoDao();
    }
    
    private PlayerDao playerDao(){
        return DaoFactory.getInstance().getPlayerDao();
    }
    
}













/*@Override
public Set<Class<?>> getClasses() {
Set<Class<?>> resources = new java.util.HashSet<>();
addRestResourceClasses(resources);
return resources;
}*/

/**
 * Do not modify addRestResourceClasses() method.
 * It is automatically populated with
 * all resources defined in the project.
 * If required, comment out calling this method in getClasses().
 */
/*private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(fallball.service.PlayerDataService.class);
}*/
