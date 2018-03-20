/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerData {
    //STATIC INNER CLASSES ALLOW US TO INSTATIATE THEM WITHOUT HAVING AN INSTANCE OF ITS CONTAINER CLASS
    //USE NESTED CLASSES SINCE JAVA DOES NOT SUPPORT STRUCTS
	
    //TBH I'M NOT SURE IF THE JAXB ANNOTATION IS NEED AFTER ADDING THE JSON-JACKSON DEPENDENCY IN THE POM.XML
	//@XmlRootElement
	public static class Player{
        public int playerId;
        public String playerName;
        public float playerScore;
        public int level;
        /*public boolean lastPlayer;
        public float posX;
        public float posY;*/
        
        public Player(){
        }
        
        public Player(int playerId, String playerName, float playerScore, int level){
            this.playerId = playerId;
        	this.playerName = playerName;
            this.playerScore = playerScore;
            this.level = level;
        }
        
    }
    
    public List<Player> playerChart;
    public boolean level2Enabled;
    public boolean level3Enabled;
    public boolean ball2Enabled;
    public boolean ball3Enabled;
    public String currentSprite;
    
    public PlayerData(){
    }
}
