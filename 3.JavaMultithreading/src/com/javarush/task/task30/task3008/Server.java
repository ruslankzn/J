package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    //7.1
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();//7.2

    public static void main(String[] args)throws IOException{
        //6.4
        ConsoleHelper.writeMessage("Enter the server's port:");
        int serverPort = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(serverPort);

        try{
            ConsoleHelper.writeMessage("Server has been launched");
            while(true){
                //listen
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                //starting handler
                handler.start();
            }
        } catch(IOException e){
            System.out.println(" Connection error!");
            serverSocket.close();
        }
    }

    //7.3
    public static void sendBroadcastMessage(Message message){
        try {
            for(Connection connection: connectionMap.values()){
                connection.send(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Message can't be sent");
        }
    }

    //6.1
    private static class Handler extends Thread{
        //6.2
        private Socket socket;

        //6.3
        public Handler(Socket socket) {
            this.socket = socket;
        }

        //8.0
        private String serverHandshake(Connection connection)throws IOException, ClassNotFoundException{
            while(true){
                connection.send(new Message(MessageType.NAME_REQUEST));
                //answer
                Message message = connection.receive();

                if(message.getType() == MessageType.USER_NAME){
                    if(!message.getData().isEmpty()){
                        //if client has not been connected
                        if(connectionMap.get(message.getData())==null){
                            connectionMap.put(message.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return message.getData();
                        }
                    }
                }
            }
        }

        //9.1
        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(String key: connectionMap.keySet()){
                Message message = new Message(MessageType.USER_ADDED, key);
                if(!key.equals(userName)){
                    connection.send(message);
                }
            }
        }

        //10.1
        private void serverMainLoop(Connection connection,String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT){
                    String sMessage = userName + ": " + message.getData();
                    Message formattedSMessage = new Message(MessageType.TEXT, sMessage);
                    sendBroadcastMessage(formattedSMessage);
                }
                else
                {
                    ConsoleHelper.writeMessage("Error!");
                }
            }
        }

        //11.1
        @Override
        public void run() {
            ConsoleHelper.writeMessage("New connection with remote server has been initiated: " + socket.getRemoteSocketAddress());
            String userName = null;
            try {
                Connection connection = new Connection(socket);
                    userName = serverHandshake(connection);
                    sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                    sendListOfUsers(connection, userName);
                    serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Error with connection!");
            }

            if(userName !=null){
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Connection with remote server has been closed");
        }
    }
}
