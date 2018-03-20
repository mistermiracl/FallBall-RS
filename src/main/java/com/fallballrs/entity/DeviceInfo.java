/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.entity;

public class DeviceInfo {
    public String deviceId;
    public String platform;
    public String os;
    
    public DeviceInfo(){
    }
    
    public DeviceInfo(String deviceId, String platform, String os){
        this.deviceId = deviceId;
        this.platform = platform;
        this.os = os;
    }
    
}
