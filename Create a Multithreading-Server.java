package SocketLab2;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
public class Server {
public static LinkedList<clientThread> clientList = new LinkedList<>();
public static void main(String[] args) {
try {
ServerSocket server = new ServerSocket(20);
System.out.println("Server Started");
new Thread() {
@Override
public void run() {
super.run();

Scanner consoleInput = new Scanner(System.in);

while(true) {
String serverInput = consoleInput.nextLine();
System.out.println(serverInput);
for (int i = 0; i < clientList.size(); i++) {
System.out.println(serverInput);
clientList.get(i)
.writer.println(serverInput);

}
}
}
}.start();

while(true) {
clientList.add( new clientThread(server.accept()) );
clientList.peekLast().start();
}

} catch (IOException e) {
System.out.println("Sorry! Can not start the server");
}
}
}
class clientThread extends Thread {

Socket clientSocket;
Scanner reader;
PrintWriter writer;

public clientThread(Socket clientSocket) throws IOException {

this.clientSocket = clientSocket;
this.reader = new Scanner( clientSocket.getInputStream() );
this.writer = new PrintWriter( clientSocket.getOutputStream(), true );
}
@Override

public void run() {
try {
while( clientSocket.isConnected() ) {
System.out.println("Client: " + reader.nextLine());
}
} catch (Exception e) {
System.out.println("Client Left");
}
Server.clientList.remove(this);
}
}