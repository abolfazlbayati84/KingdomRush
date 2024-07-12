package Controller;

import Model.Map.Coordinate;
import Model.Map.FourthMap;
import Model.Raiders.Raider;
import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

public class RaiderController {
    private static RaiderController raiderController;

    public static RaiderController getRaiderController() {
        if(raiderController == null){
            raiderController = new RaiderController();
        }
        return raiderController;
    }
    public PathTransition setPathForRaider(Raider raider){
        MapController.getMapController().getRaiders().add(raider);
        PathTransition pathTransition = null;
        Random random = new Random();
        if(!(MapController.getMapController().getMap() instanceof FourthMap)){
            Path path = new Path();
            path.getElements().add(new MoveTo(MapController.getMapController().getMap().getPath().get(0).getX(), MapController.getMapController().getMap().getPath().get(0).getY()));
            boolean isFirstTime = true;
            for (Coordinate coordinate : MapController.getMapController().getMap().getPath()) {
                if (!isFirstTime) {
                    path.getElements().add(new LineTo(coordinate.getX(), coordinate.getY()));
                }
                isFirstTime = false;
            }

            pathTransition = new PathTransition();
            raider.setPathTransition(pathTransition);
            pathTransition.setPath(path);
            pathTransition.setDuration(Duration.seconds(raider.getSpeed()));
            pathTransition.setNode(raider);
            pathTransition.play();
        }else if(random.nextBoolean()){
            Path path = new Path();
            path.getElements().add(new MoveTo(MapController.getMapController().getMap().getPath().get(0).getX(), MapController.getMapController().getMap().getPath().get(0).getY()));
            boolean isFirstTime = true;
            for (Coordinate coordinate : MapController.getMapController().getMap().getPath()) {
                if (!isFirstTime) {
                    path.getElements().add(new LineTo(coordinate.getX(), coordinate.getY()));
                }
                isFirstTime = false;
            }

            pathTransition = new PathTransition();
            raider.setPathTransition(pathTransition);
            pathTransition.setPath(path);
            pathTransition.setDuration(Duration.seconds(raider.getSpeed()));
            pathTransition.setNode(raider);
            pathTransition.play();
        }else{
            Path path = new Path();
            FourthMap fourthMap = (FourthMap) MapController.getMapController().getMap();
            path.getElements().add(new MoveTo(fourthMap.getPath2().get(0).getX(), fourthMap.getPath2().get(0).getY()));
            boolean isFirstTime = true;
            for (Coordinate coordinate : fourthMap.getPath2()) {
                if (!isFirstTime) {
                    path.getElements().add(new LineTo(coordinate.getX(), coordinate.getY()));
                }
                isFirstTime = false;
            }

            pathTransition = new PathTransition();
            raider.setPathTransition(pathTransition);
            pathTransition.setPath(path);
            pathTransition.setDuration(Duration.seconds(raider.getSpeed()));
            pathTransition.setNode(raider);
            pathTransition.play();
        }
        return pathTransition;
    }
}
