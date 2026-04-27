package Design_Patterns.Structural.Flyweight_Pattern;

import java.util.*;

// Flyweight interface
interface Tree {
    void display(int x, int y); // extrinsic state
}

// Concrete Flyweight
class TreeType implements Tree {
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Drawing " + name + " tree at (" + x + "," + y + ") with color " + color);
    }
}

// Flyweight Factory
class TreeFactory {
    private static Map<String, TreeType> treeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + color + texture;

        if (!treeMap.containsKey(key)) {
            treeMap.put(key, new TreeType(name, color, texture));
        }

        return treeMap.get(key);
    }
}

// Context class that uses the Flyweight
class TreeContext {
    private int x, y; // extrinsic
    private TreeType type; // shared

    public TreeContext(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void display() {
        type.display(x, y);
    }
}

public class Main {
    public static void main(String[] args) {
        List<TreeContext> trees = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            TreeType type = TreeFactory.getTreeType("Oak", "Green", "Rough");
            trees.add(new TreeContext(i, i + 1, type));
        }

        for (TreeContext tree : trees) {
            tree.display();
        }
    }
}