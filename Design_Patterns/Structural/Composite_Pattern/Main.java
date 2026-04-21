package Design_Patterns.Structural.Composite_Pattern;

import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystemComponent {
    void showDetails();
}

// Leaf
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        components.add(component);
    }

    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);

        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Leaf objects
        File resume = new File("resume.pdf");
        File degree = new File("degree.pdf");

        File photo = new File("photo.jpg");
        File pan = new File("pan.pdf");

        // Composite objects
        Folder education = new Folder("Education");
        education.add(resume);
        education.add(degree);

        Folder images = new Folder("Images");
        images.add(photo);
        images.add(pan);

        Folder mainFolder = new Folder("Main Folder");
        mainFolder.add(education);
        mainFolder.add(images);

        // Treating both same
        mainFolder.showDetails();
    }

}
