package Design_Patterns.Factory_Pattern;

public class ShapeFactory {

    Shape myShape(String shape) {

        switch (shape) {
            case "Circle":
                return new Circle();

            case "Rect":
                return new Rectangle();

            default:
                return null;

        }
    }
}
