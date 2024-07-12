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
    public void updateAfterWin(int winingMap){
        if(Player.getPlayer().getLevel() == winingMap){
            Player.getPlayer().updateAfterWin(winingMap,true);
        }else{
            Player.getPlayer().updateAfterWin(winingMap,false);
        }
    }
    public void changeInformation(String newUsername,String newPassword){
        player.addNewInformation(newUsername,newPassword);
    }
    public void addSpell(String spell) throws NotEnoughDiamonds {
        if(spell.equals("Bomb") && Player.getPlayer().getDiamond()>250){
            player.setDiamond(player.getDiamond()-Spells.LittleChild.getPrice());
            PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.LittleChild,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.LittleChild)+1);
            Player.getPlayer().addToBackpack(Spells.LittleChild.toString(),PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.LittleChild));
        }else if(spell.equals("Coin") && Player.getPlayer().getDiamond()>50){
            player.setDiamond(player.getDiamond()-Spells.Coin.getPrice());
            PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.Coin,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.Coin)+1);
            Player.getPlayer().addToBackpack(Spells.Coin.toString(),PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.Coin));
        }else if(spell.equals("Freeze") && Player.getPlayer().getDiamond()>150){
            player.setDiamond(player.getDiamond()-Spells.Freeze.getPrice());
            PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.Freeze,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.Freeze)+1);
            Player.getPlayer().addToBackpack(Spells.Freeze.toString(),PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.Freeze));
        }else if(spell.equals("Heart") && Player.getPlayer().getDiamond()>100){
            player.setDiamond(player.getDiamond()-Spells.HealthKit.getPrice());
            PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.HealthKit,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.HealthKit)+1);
            Player.getPlayer().addToBackpack(Spells.HealthKit.toString(),PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.HealthKit));
        }else{
            throw new NotEnoughDiamonds();
        }
    }
}
