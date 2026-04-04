package gh2;

import java.io.File;

public class MyMidiPlayer {
    public static void main(String[] args) {
        File mySong = new File("mysong.mid");
        if (!mySong.exists()) {
            return;
        }
        GuitarPlayer player = new GuitarPlayer(mySong);
        player.play();
    }
}