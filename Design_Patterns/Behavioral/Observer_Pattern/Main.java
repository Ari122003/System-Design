package Design_Patterns.Behavioral.Observer_Pattern;

public class Main {

    public static void main(String[] args) {

        YoutubeChannel channel = new YoutubeChannel();

        Subscriber sub1 = new Subscriber("Aritra");
        Subscriber sub2 = new Subscriber("Mimi");
        Subscriber sub3 = new Subscriber("Shoal");
        Subscriber sub4 = new Subscriber("Niladri");

        channel.subscribe(sub1);
        channel.subscribe(sub2);
        channel.subscribe(sub3);
        channel.subscribe(sub4);

        channel.uploadVideo("Spring Boot Tutorial");

    }

}
