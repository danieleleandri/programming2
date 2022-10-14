package aWebServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class WebServer {
    /*Here you can make your customization
     * 1) specify the listening por7wwwt (in the text was 50505, but feel free to change it if it is busy)
     * 2) My default wwwRoot is "c:/wwwRoot". To successfully test the program i suggest to add here the testing file
     *      harrison.png and index.html*/
    private static final int LISTENING_PORT = 50505;
    private static final int TIMEOUT_MINUTES = 10;
    private static final String rootDirectory = "c:/wwwRoot";
    private static final String lineTerminator = "\r\n"; //The terminator for communication
    private static ServerSocket serverSocket;

    /**
     * The main method will create a web server listening on the port LISTENING_PORT
     * @param args
     */
    public static void main(String[] args) {
        
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
            // I added a timeout to release the port after 3 * 60 seconds of inactivity
            serverSocket.setSoTimeout(TIMEOUT_MINUTES * 60 * 1000);
        } catch (Exception e) {
            System.out.println("Failed to create listening socket." + e.getMessage());
            return;
        }
        //The folloqing message confirms that the server is listening
        //Now you can browse the page at http:\\localhost:LISTENING_PORT\<something>
        System.out.println("Listening on port " + LISTENING_PORT);
        try {
            /*The program will now stay cycling waiting for a client connection*/
            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("\nConnection from "  + connection.getRemoteSocketAddress());
                /*The following lines were added to manage multi-threads connections*/
                ConnectionThread thread = new ConnectionThread(connection);
                thread.start();
                //handleConnection(connection);
            }
        } catch (Exception e) {
            System.out.println("Server socket shut down unexpectedly!");
            System.out.println("Error: " + e);
            System.out.println("Exiting.");
        }
    }

    /*
     * The method finalize() is used by java to perform cleanings and closures before shutdown.
     * I used it to close the severSocket on program shutdown.*/
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Terminating serverSocket.");
        if ( serverSocket!= null )  serverSocket.close();
        super.finalize();
    }

    
    /**
     * This method was pasted from the requistes. It is the responsible for communications
     * handling
     * @param connection
     */
    private static void handleConnection(Socket connection) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = connection.getInputStream();
                out = connection.getOutputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // Reading the data from the socket:
            Scanner s = new Scanner(in);
            int tokenCheck = 0;
            String command = "";
            String resource = "";
            String protocol = "";
            if (s.hasNext()) {
                command = s.next();
                System.out.println("command " + command);
                tokenCheck++;
            }
            if (s.hasNext()) {
                resource = s.next();
                System.out.println("resource " + resource);
                tokenCheck++;
            }
            if (s.hasNext()) {
                protocol = s.next();
                System.out.println("protocol " + protocol);
                tokenCheck++;
            }
            // Check if the 3 tokens are ok, if not it throw an exception
            if (tokenCheck < 3) {
                    throw new IOException();
                }
            System.out.println(command + " " + rootDirectory + resource);
            // Execution of the command:
            if (command.toUpperCase().equals("GET")) {
                File f = new File(rootDirectory + resource);
                if (f.exists()) {
                    String mimeType = getMimeType(f.getName());
                    long fileSize = Files.size(f.toPath());
                    Scanner scan = new Scanner(f);

                    System.out.println("Sending to the client " + rootDirectory + resource);
                    PrintWriter pW = new PrintWriter(out);
                    pW.print(protocol + " 200 OK" + lineTerminator);
                    pW.print("Connection: close" + lineTerminator);
                    pW.print("Content-length: " + fileSize + lineTerminator);
                    pW.print("Content-type: " + getMimeType(f.getName()) + lineTerminator);
                    pW.print(lineTerminator);
                    pW.flush();
                    sendFile(f, out);
                    // pW.close();
                } else {
                    sendErrorResponse(404, out, getMimeType(resource), protocol);
                }
            } else {
                //If the requested method is not GET gives a 501 error
                sendErrorResponse(501, out, getMimeType(resource), protocol);
            }
            // 3673

        } catch (Exception e1) {
            System.out.println("Error while communicating with client: " + e1.getMessage());
        } finally { /*
                     * the finally block was added to catch every possible exceptions and always
                     * close
                     * the connection
                     */
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            connection.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * The method is responsible for sending rigth server error codes.
     * It manages only the errors 404 and 501
     * @param errorCode It is the error to notify
     * @param out Is the stream to the client
     * @param mimeType This was added by me to trace the file mimeType (the default null is "text/html")
     * @param protocol This was added by me to trace the file protocol (the default null is "HTTP/1.1")
     */
    static void sendErrorResponse(int errorCode, OutputStream out, String mimeType ,String protocol) {
        if (protocol == null) {
            protocol = "HTTP/1.1";
        }
        if (mimeType == null) {
            mimeType = "text/html";
        }
        PrintWriter pW = new PrintWriter(out);
        switch (errorCode) {
            case 404:                
                pW.print(protocol + " 404 Not Found" + lineTerminator);
                pW.print("Connection: close" + lineTerminator);
                pW.print("Content-type: " + mimeType + lineTerminator);
                pW.print(lineTerminator);
                pW.print("<html><head><title>Error</title></head><body>"  + lineTerminator);
                pW.print("<h2>Error: 404 Not Found</h2>"  + lineTerminator);
                pW.print("<p>The resource that you requested does not exist on this server.</p>"  + lineTerminator);
                pW.print("</body></html>"  + lineTerminator);
                pW.print(lineTerminator);
                pW.flush();
                break;
            case 403:
                //Not yet implemented
                break;
            case 400:
              //Not yet implemented
                break;
            case 501:                
                pW.print(protocol + " 501 Not implemented" + lineTerminator);
                pW.print("Connection: close" + lineTerminator);
                pW.print("Content-type: " + mimeType + lineTerminator);
                pW.print(lineTerminator);
                pW.print("<html><head><title>Error</title></head><body>"  + lineTerminator);
                pW.print("<h2>Error: 501 Not implemented</h2>"  + lineTerminator);
                pW.print("<p>The method you requested has not been implemented.</p>"  + lineTerminator);
                pW.print("</body></html>"  + lineTerminator);
                pW.print(lineTerminator);
                pW.flush();
                break;
            case 500:
              //Not yet implemented
                break;
        }
        pW.close();
        


    }

    /**
     * Method pasted from requisites.
     * It will return the correct Mime Type from the filename
     * 
     * @param fileName
     * @return
     */
    private static String getMimeType(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if (pos < 0) // no file extension in name
            return "x-application/x-unknown";
        String ext = fileName.substring(pos + 1).toLowerCase();
        if (ext.equals("txt"))
            return "text/plain";
        else if (ext.equals("html"))
            return "text/html";
        else if (ext.equals("htm"))
            return "text/html";
        else if (ext.equals("css"))
            return "text/css";
        else if (ext.equals("js"))
            return "text/javascript";
        else if (ext.equals("java"))
            return "text/x-java";
        else if (ext.equals("jpeg"))
            return "image/jpeg";
        else if (ext.equals("jpg"))
            return "image/jpeg";
        else if (ext.equals("png"))
            return "image/png";
        else if (ext.equals("gif"))
            return "image/gif";
        else if (ext.equals("ico"))
            return "image/x-icon";
        else if (ext.equals("class"))
            return "application/java-vm";
        else if (ext.equals("jar"))
            return "application/java-archive";
        else if (ext.equals("zip"))
            return "application/zip";
        else if (ext.equals("xml"))
            return "application/xml";
        else if (ext.equals("xhtml"))
            return "application/xhtml+xml";
        else
            return "x-application/x-unknown";
        // Note: x-application/x-unknown is something made up;
        // it will probably make the browser offer to save the file.
    }

    /**
     * Also this method wa pasted from requisites
     * 
     * @param file
     * @param socketOut
     * @throws IOException
     */
    private static void sendFile(File file, OutputStream socketOut) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        OutputStream out = new BufferedOutputStream(socketOut);
        while (true) {
            int x = in.read(); // read one byte from file
            if (x < 0)
                break; // end of file reached
            out.write(x); // write the byte to the socket
        }
        out.flush();
    }
    /**
     * Inner class pasted from requisites for managing multithreading
     * @author danie
     *
     */
    private static class ConnectionThread extends Thread {
        Socket connection;
        ConnectionThread(Socket connection) {
           this.connection = connection;
        }
        public void run() {
           handleConnection(connection);
        }
     }

}
