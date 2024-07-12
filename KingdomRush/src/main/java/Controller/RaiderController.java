package Controller;

import Model.Map.Coordinate;
import Model.Raiders.Raider;
import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class RaiderController {
    private static RaiderController raiderController;

    public static RaiderController getRaiderController() {
        if(raiderController == null){
            raiderController = new RaiderController();
        }
        return raiderController;
    }
    public PathTransition setPathForRaider(Raider raider){
        Path path = new Path();
        path.getElements().add(new MoveTo(MapController.getMapController().getMap().getPath().get(0).getX(), MapController.getMapController().getMap().getPath().get(0).getY()));
        boolean isFirstTime = true;
        for (Coordinate coordinate : MapController.getMapController().getMap().getPath()) {
            if (!isFirstTime) {
                path.getElements().add(new LineTo(coordinate.getX(), coordinate.getY()));
            }
            isFirstTime = false;
        }

        PathTransition pathTransition = new PathTransition();
        raider.setPathTransition(pathTransition);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(raider.getSpeed()));
        pathTransition.setNode(raider);
        pathTransition.play();
        return pathTransition;
    }
}
