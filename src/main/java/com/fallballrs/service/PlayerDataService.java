/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fallballrs.service;

import com.fallballrs.dao.PlayerDao;
import com.fallballrs.entity.PlayerData;
import com.fallballrs.service.model.FallBallRSResult;
import com.fallballrs.util.Util.FallBallResponseCode;
import com.fallballrs.util.Util.FallBallResponseMessage;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/player")//playerDataService
public class PlayerDataService {
	
    @Context
    private UriInfo context;
    
    //DI SPECIFIED IN APPLICATIONCONFIG
    @Inject
    private PlayerDao playerDao;
    /**
     * Creates a new instance of PlayerDataService
     */
    
    //WITHOUT DI
    /*public PlayerDataService() {
    playerDao = DaoFactory.getInstance().getPlayerDao();
    }*/

    
    /**No arg ctor*/
    public PlayerDataService() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerData.Player test() {
    	//return "<h1>Working test</h1>";
    	return new PlayerData.Player(1, "Player", 23f, 21);
    }
    
    /**
     * Retrieves representation of an instance of com.fallballrs.entity.PlayerData.Player
     * @param playerId Id of the requested player
     * @return an instance of java.lang.String
     */
    @GET
    @Path("getPlayer/{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("playerId") int playerId){
        return Response.status(Status.ACCEPTED)
        		.entity(this.playerDao.find(playerId))
        		.build();
    }
    
    @GET
    @Path("getAllPlayers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlayers(){
        List<PlayerData.Player> players = playerDao.findAll();
    	//ADD CORS HEADER TO ALLOW BROWSER AJAX REQUESTS
    	Response response = Response.status(Status.OK)
        		.entity(players)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    	
    	return response;
    }
    
    
    @POST
    @Path("newPlayer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)//IT SEEMS THAT WHEN TO DO REST WE ALWAYS NEED TO SPECIFY THE PRODUCES ANNOTATION ALONG WITH ITS MEDIA TYPE
    //ALSO A MESSAGEBODY WRITE FOR JSON IS IMPLCITLY USED IDK WHICH BUT WHEN NOT SPECIFYING THE PRODUCED OBJECT TEXT/XML IS USED WHICH NEEDS AN EXPLICIT BODY WRITER
    public Response newPlayer(PlayerData.Player player){
        //System.out.printf("%s - %f - %d", player.playerName, player.playerScore, player.level);
        boolean result = playerDao.create(player);
        return Response.status(Status.CREATED)
        		.entity(new FallBallRSResult(FallBallResponseCode.FINE, FallBallResponseMessage.INSERTION_SUCCESSFUL, result))
        		.build();
    }
    
    
    @PUT
    @Path("updatePlayer")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlayer(PlayerData.Player player) {
    	boolean result = playerDao.update(player);
    	return Response.status(Status.OK)
    			.entity(new FallBallRSResult(FallBallResponseCode.FINE, FallBallResponseMessage.UPDATE_SUCCESSFUL, result))
    			.build();
    }
}
