package SocketLab2;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

public static void main(String[] args)
{ try {
Socket socket = new Socket("localhost", 20);
System.out.println("Client has connected to server");
Scanner reader = new Scanner(socket.getInputStream());
PrintWriter writer =
new PrintWriter(socket.getOutputStream(), true);

Scanner consoleInput = new Scanner(System.in);

new Thread() {
public void run() {
while(true) {
System.out.println("Server: "
+ reader.nextLine());

}
};
}.start();
while( socket.isConnected() ) {
writer.println(consoleInput.nextLine());
}
} catch (IOException e) {
e.printStackTrace();
}
}

}