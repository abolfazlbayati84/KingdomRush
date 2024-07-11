package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.kingdomrush.HelloApplication;

public class MediaPlayerController {
    private static MediaPlayerController mediaPlayerController;
    private static Media music;
    private static MediaPlayer mediaPlayer;
    private static boolean isPlaying;
    private MediaPlayerController(){
        String path = HelloApplication.class.getResource("01. Main Theme.mp3").toExternalForm();
        music = new Media(path);
        mediaPlayer = new MediaPlayer(music);
        isPlaying = false;
    }

    public static MediaPlayerController getMediaPlayerController() {
        if(mediaPlayerController == null){
            mediaPlayerController = new MediaPlayerController();
        }
        return mediaPlayerController;
    }

    public static Media getMusic() {
        return music;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static boolean isIsPlaying() {
        return isPlaying;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        MediaPlayerController.mediaPlayer = mediaPlayer;
    }

    public static void setIsPlaying(boolean isPlaying) {
        MediaPlayerController.isPlaying = isPlaying;
    }

    public static void setMusic(Media music) {
        MediaPlayerController.music = music;
    }
}
