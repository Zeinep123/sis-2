import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Enter the name of the geometric shape (Circle, Rectangle) or Q to quit:");
                String shapeName = scanner.nextLine();

                if (shapeName.equalsIgnoreCase("Q")) {
                    outputStream.writeObject("Q");
                    break;
                }

                if (shapeName.equalsIgnoreCase("Circle")) {
                    System.out.print("Enter radius: ");
                    double radius = scanner.nextDouble();
                    outputStream.writeObject(new Circle(radius));
                } else if (shapeName.equalsIgnoreCase("Rectangle")) {
                    System.out.print("Enter width: ");
                    double width = scanner.nextDouble();
                    System.out.print("Enter height: ");
                    double height = scanner.nextDouble();
                    outputStream.writeObject(new Rectangle(width, height));
                } else {
                    System.out.println("Invalid shape name ");
                    continue;
                }

                String response = (String) inputStream.readObject();
                System.out.println("Server message: " + response);

                scanner.nextLine();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
