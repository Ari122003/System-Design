package Design_Patterns.Factory_Pattern;

public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape shape = factory.myShape("Circle");

        shape.draw();
    }

}
