//package by.yagafarov.example;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class HttpClientExample {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        var htttpClient = HttpClient.newBuilder()
//                          .version(HttpClient.Version.HTTP_1_1)
//                          .build();
//        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.google.com")).GET().build();
//        var response = htttpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//    }
//}
