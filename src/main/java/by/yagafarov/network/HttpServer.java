package by.yagafarov.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int port;
    private final ExecutorService executorService;

    public HttpServer(int port, int poolSize) {
        this.port = port;
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            while (true) {
                var socket = serverSocket.accept();
                System.out.println("Socket accepted");
                executorService.submit(() -> proccessSocket(socket));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void proccessSocket(Socket socket) {
        try (socket;
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream()))
             {
                 Thread.sleep(1000);
                 System.out.println(new String(inputStream.readNBytes(400)));
                 byte[] body = Files.readAllBytes(Path.of("src/main/resources/example.html"));
                 outputStream.write("""
                         HTTP/1.1 200 OK
                         content-type: text/html
                         content-length: %s 
                         """.formatted(body.length).getBytes());
                 outputStream.write(System.lineSeparator().getBytes());
                 outputStream.write(body);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
