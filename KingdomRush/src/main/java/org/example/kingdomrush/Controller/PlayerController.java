package org.example.kingdomrush.Controller;

import org.example.kingdomrush.Model.Player.Player;

public class PlayerController {

    static private PlayerController playerController;

    private Player player;
    private PlayerController(){

    }
    public static PlayerController getPlayerController() {
        if(playerController == null){
            playerController = new PlayerController();
        }
        return playerController;
    }
    public void signUp(String username,String password) throws Exception {
        Player.getPlayer().signUpPlayer(username,password,1,0);
    }
    public void login(String username,String password) throws Exception {
        Player.getPlayer().login(username, password);
        if(Player.getPlayer().isLoggedIn()){
            player = Player.getPlayer();
        }
    }
}
