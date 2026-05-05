package Design_Patterns.Behavioral.State_Pattern;

// State Interface
interface PlayerState {
    void pressPlay(MediaPlayer player);
}

// Context
class PlayingState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        System.out.println("Pausing the music...");
        player.setState(new PausedState());
    }
}

class PausedState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        System.out.println("Resuming the music...");
        player.setState(new PlayingState());
    }
}

class StoppedState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        System.out.println("Starting the music...");
        player.setState(new PlayingState());
    }
}

class MediaPlayer {
    private PlayerState state;

    public MediaPlayer() {
        state = new StoppedState(); // initial state
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void pressPlay() {
        state.pressPlay(this);
    }
}

public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        player.pressPlay(); // Starting the music...
        player.pressPlay(); // Pausing the music...
        player.pressPlay(); // Resuming the music...

    }
}
