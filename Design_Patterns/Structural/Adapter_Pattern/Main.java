
package Design_Patterns.Structural.Adapter_Pattern;

// Target interface
interface XboxController {
    void pressA();

    void pressB();
}

// Adaptee
class PlayStationController {
    public void pressX() {
        System.out.println("Pressed X (PlayStation)");
    }

    public void pressCircle() {
        System.out.println("Pressed Circle (PlayStation)");
    }
}

class PlayStationToXboxAdapter implements XboxController {

    private PlayStationController psController;

    public PlayStationToXboxAdapter(PlayStationController psController) {
        this.psController = psController;
    }

    @Override
    public void pressA() {
        // Mapping A → X
        psController.pressX();
    }

    @Override
    public void pressB() {
        // Mapping B → Circle
        psController.pressCircle();
    }
}

public class Main {

    public static void main(String[] args) {
        // Player has PlayStation controller
        PlayStationController ps = new PlayStationController();

        // Convert it into Xbox-compatible controller
        XboxController controller = new PlayStationToXboxAdapter(ps);

        // Game only understands Xbox inputs
        controller.pressA();
        controller.pressB();
    }

}
