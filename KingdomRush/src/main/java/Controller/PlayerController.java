package Controller;

import Exceptions.NotEnoughDiamonds;
import Model.Player.Player;
import Model.Player.Spells;

public class PlayerController {

    static private PlayerController playerController;

    private Player player;
    private PlayerController(){

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static PlayerController getPlayerController() {
        if(playerController == null){
            playerController = new PlayerController();
        }
        return playerController;
    }
    public void signUp(String username,String password) throws Exception {
        Player.getPlayer().signUpPlayer(username,password,1,1000);
    }
    public void login(String username,String password) throws Exception {
        Player.getPlayer().login(username, password);
        if(Player.getPlayer().isLoggedIn()){
            player = Player.getPlayer();
        }
    }
    public void changeInformation(String newUsername,String newPassword){
        player.addNewInformation(newUsername,newPassword);
    }
    public void addSpell(String spell) throws NotEnoughDiamonds {
        if(spell.equals("Bomb") && Player.getPlayer().getDiamond()>250){
            player.setDiamond(player.getDiamond()-250);
            Player.getPlayer().addToBackpack(Spells.LittleChild.toString());
        }else if(spell.equals("Coin") && Player.getPlayer().getDiamond()>50){
            player.setDiamond(player.getDiamond()-50);
            Player.getPlayer().addToBackpack(Spells.Coin.toString());
        }else if(spell.equals("Freeze") && Player.getPlayer().getDiamond()>150){
            player.setDiamond(player.getDiamond()-150);
            Player.getPlayer().addToBackpack(Spells.Freeze.toString());
        }else if(spell.equals("Heart") && Player.getPlayer().getDiamond()>100){
            player.setDiamond(player.getDiamond()-100);
            Player.getPlayer().addToBackpack(Spells.HealthKit.toString());
        }else{
            throw new NotEnoughDiamonds();
        }
    }
}
