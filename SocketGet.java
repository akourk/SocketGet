// Author       :   Alex Kourkoumelis
// Date         :   10/17/2018
// Title        :   SocketGet
// Description  :   Makes an HTTP request to "GET" the HTML
//              :   page for that URL and then prints the
//              :   HTTP header as well as the HTML for the
//              :   page to the console.


import java.io.*;
import java.net.*;

public class SocketGet {

    // main entry point for the application
    public static void main(String[] args) {
        try {
            String urlString = args[0];
            URL httpUrl = new URL(urlString);

            // TODO: open the socket below

            // useful links:
            // https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
            // hint: you need to create a new socket object and use httpUrl to get some parameters for the constructor

            Socket httpSocket = new Socket(httpUrl.getHost(),80);

            // open up streams ot write to and read from

            PrintWriter request = new PrintWriter(httpSocket.getOutputStream(), true);
            BufferedReader response = new BufferedReader(new InputStreamReader(httpSocket.getInputStream()));

            // TODO: build the HTTP "GET" request, alter the httpHeader string to take a path (e.g. http://www.examplefoo.com/dir1/page.html)

            // useful links:
            // https://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html
            // https://msdn.microsoft.com/en-us/library/aa287673%28v=vs.71%29.aspx?f=255&MSPPError=-2147217396

            String httpHeader = "GET " + httpUrl + " HTTP/1.1\r\nAccept: */*\r\nAccept-Language: en-us\r\nHost: localhost\r\nConnection: close\r\n\r\n";

            // send the HTTP request
            request.println(httpHeader);

            // read the reply and print
            String responseStr = response.readLine();
            while ((responseStr != null) && (responseStr != "")) {
                System.out.println(responseStr);
                responseStr = response.readLine();
            }

            httpSocket.close();
            // TODO: close the socket
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know the hostname");
        }
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
        }
    }
}
