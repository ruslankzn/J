package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

//12.2
public class Client {
    //12.4
    protected Connection connection;
    //12.5
    private volatile boolean clientConnected = false;
    /******************************************************************************/
    //12.3
    public class SocketThread extends Thread{
        //15
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        /**************/
        protected void informAboutAddingNewUser(String userName){

            ConsoleHelper.writeMessage("User " + userName + " has been connected to chat");
        }
        /****************/
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage("User " + userName + " has been left from chat");
        }
        /****************/
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this){
                Client.this.notify();
            }
        }
        /***************************************************************************/
        //16.1
        protected void clientHandshake() throws IOException,ClassNotFoundException{
            while (true){
                //16.a
                Message message = connection.receive();
                //16.b
                if(message.getType() == MessageType.NAME_REQUEST){
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                }//16.c
                else if(message.getType() == MessageType.NAME_ACCEPTED){
                    notifyConnectionStatusChanged(true);
                    return;
                }//16.d
                else{
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
        //16.2
        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT){
                    processIncomingMessage(message.getData());
                }
                else if(message.getType() == MessageType.USER_ADDED){
                    informAboutAddingNewUser(message.getData());
                }
                else if(message.getType() == MessageType.USER_REMOVED){
                    informAboutDeletingNewUser(message.getData());
                }
                else{
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
        /***********************************************************************************/
        //17
        public void run(){
            try {
                Socket socket = new Socket(getServerAddress(), getServerPort());

                Client.this.connection = new Connection(socket);

                clientHandshake();

                clientMainLoop();

            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
    /********************************************************************************************/
    //13.1
    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Enter the server address: ");
        return ConsoleHelper.readString();
    }
    //13.2
    protected int getServerPort(){
        ConsoleHelper.writeMessage("Enter the server's port: ");
        return ConsoleHelper.readInt();
    }
    //13.3
    protected String getUserName(){
        ConsoleHelper.writeMessage("Enter the User name: ");
        return ConsoleHelper.readString();
    }
    //13.4
    protected boolean shouldSendTextFromConsole(){
        return true;
    }
    //13.5
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
    //13.6
    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Message send error!");
            clientConnected = false;
        }
    }

    //14.1
    /***************************************************************************************************/
    public void run(){
        /*****14.a******/
        SocketThread socketThread = getSocketThread();
        /******14.b******/
        socketThread.setDaemon(true);
        /*******14.c******/
        socketThread.start();
        /*****waiting***/
        try {
            synchronized (this){
                this.wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Error");
            return;
        }
        /****check clientConnected***/
        if(clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected){
                String sms;
                if(!(sms=ConsoleHelper.readString()).equals("exit")){
                    if(shouldSendTextFromConsole()){
                        sendTextMessage(sms);
                    }
                }else{
                    return;
                }
            }
        }
        else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
    }
    /**********************************************************************************************************/
    //14.2
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
