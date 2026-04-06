package Design_Patterns.Creational.Factory_Pattern;

public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape shape = factory.myShape("Circle");

        shape.draw();
    }

}
