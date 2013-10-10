package checkers;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;



//ports 2000-2020 are open?
public class Server {
    
    Boolean serverRunning;
    String blackPlayerIp = "";
    String redPlayerIp = "";
    PrintWriter writer;
    BufferedReader reader;
    
    public Server(){
        startServer();
    }
    
    protected void startServer(){
        ServerSocket socketServer;
        try{
            socketServer = new ServerSocket(2000);
            while(serverRunning){
                    
                Socket socket = socketServer.accept();
                    
                writer = new PrintWriter(socket.getOutputStream());
                    
                reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                String ipAddress = socket.getInetAddress().toString().split("/")[1];
                
                setPlayerIp(ipAddress);
                
                if (ipAddress.equals(blackPlayerIp)){
                    parseMoveSequence("black");
                }
                if (ipAddress.equals(redPlayerIp)){
                    parseMoveSequence("red");
                }
                
                reader.close();
                writer.close();
                socket.close();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    private void setPlayerIp(String ipAddress){
        if (blackPlayerIp.isEmpty()) blackPlayerIp = ipAddress;
        if (redPlayerIp.isEmpty()) redPlayerIp = ipAddress;
    }
    
    private void parseMoveSequence(String playerColor){
        try{
            String[] message = reader.readLine().split(",");
            int[] moveSequence = new int[message.length];
            
            for(int i=0; i<message.length; i++){
                moveSequence[i] = Integer.parseInt(message[i]);
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    protected void stopServer(){
        serverRunning = false;
    }    
}