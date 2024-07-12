package Controller;

import Model.Player.Player;
import Model.Player.Spells;
import Model.Raiders.Raider;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.kingdomrush.HomeController;

public class SpellController {
    private static SpellController spellController;

    public static SpellController getSpellController() {
        if(spellController == null){
            spellController = new SpellController();
        }
        return spellController;
    }
    public void dropHealthKit(){
        if(MapController.getMapController().getPassedRaiders()>=5){
            MapController.getMapController().setPassedRaiders(MapController.getMapController().getPassedRaiders()-5);
            HomeController.getHeartLabel().setText(MapController.getMapController().getPassedRaiders()+"/"+20);
        }else{
            MapController.getMapController().setPassedRaiders(0);
        }
        PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.HealthKit,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.HealthKit)-1);
        Player.getPlayer().deleteSpells("HealthKit");
    }
    public void dropFreeze(Pane pane){
        for(Node node : pane.getChildren()){
            if(node instanceof Raider){
                Raider raider = (Raider) node;
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(20));
                raider.getPathTransition().pause();
                pauseTransition.play();
                pauseTransition.setOnFinished(e ->{
                    raider.getPathTransition().play();
                });
            }
        }
        PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.Freeze,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.Freeze)-1);
        Player.getPlayer().deleteSpells("Freeze");
    }
    public void dropCoin(){
        MapController.getMapController().setCoins(MapController.getMapController().getCoins() + 200);
        HomeController.getCoinNumber().setText(" "+MapController.getMapController().getCoins());
        PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.Coin,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.Coin)-1);
        Player.getPlayer().deleteSpells("Coin");
    }
    public void dropLittleChild(Pane pane){
//        Platform.runLater(()->{
        for(Node node : pane.getChildren()){
            if(node instanceof Raider){
                Raider raider = (Raider) node;
                raider.setRaiderKilled(true);
                MapController.getMapController().setCoins(MapController.getMapController().getCoins() + raider.getLoot());
                Platform.runLater(()->{
                pane.getChildren().remove(raider);
                });
            }
            HomeController.getCoinNumber().setText(" "+MapController.getMapController().getCoins());
        }
        PlayerController.getPlayerController().getPlayer().getBackpack().replace(Spells.LittleChild,PlayerController.getPlayerController().getPlayer().getBackpack().get(Spells.LittleChild)-1);
        Player.getPlayer().deleteSpells("LittleChild");
        //});
    }
}
