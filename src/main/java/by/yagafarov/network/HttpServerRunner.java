package by.yagafarov.network;

public class HttpServerRunner {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(8083,100);
        httpServer.run();
    }
}
