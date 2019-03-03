package com.javarush.task.task30.task3008;


import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class Connection implements Closeable {
    //1
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    //2
    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    //3
    public void send(Message message)throws IOException{
        synchronized (out){
            out.writeObject(message);
            out.flush();
        }
    }

    //4
    public Message receive()throws IOException, ClassNotFoundException{
        Message message;
        synchronized (in){
            message = (Message) in.readObject();
            return message;
        }
    }

    //5
    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    //6
    public void close()throws IOException{
        in.close();
        out.close();
        socket.close();
    }
}
