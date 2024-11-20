import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is running..");
            
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("Client connected..");

                    while (true) {
                        Object receivedObject = inputStream.readObject();

                        if (receivedObject instanceof String) {
                            String message = (String) receivedObject;
                            if (message.equalsIgnoreCase("Q")) {
                                System.out.println("1 client connected and requested the Q. Shutting down server...");
                                outputStream.writeObject("Connection closed.");
                                break;
                            }
                        }

                        if (receivedObject instanceof GeometricShape) {
                            GeometricShape shape = (GeometricShape) receivedObject;
                            double area = shape.calculateArea();
                            outputStream.writeObject("Area: " + area);
                        } else {
                            outputStream.writeObject("Invalid object");
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
