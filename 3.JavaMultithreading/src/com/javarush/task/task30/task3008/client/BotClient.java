package com.javarush.task.task30.task3008.client;


import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//18
public class BotClient extends Client{

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected String getUserName() {
        int counterB = (int)(Math.random() * 100);
        return "date_bot_" + counterB;
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    /**************************************************************/
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);

            String senderName = "";
            String senderText;

            if(message.contains(": ")){
                senderName = message.substring(0, message.indexOf(": "));
                senderText = message.substring(message.indexOf(": ") + 2);
            }else{
                senderText = message;
            }

            SimpleDateFormat format = null;
            if ("дата".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("d.MM.YYYY");
            } else if ("день".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("d");
            } else if ("месяц".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("MMMM");
            } else if ("год".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("YYYY");
            } else if ("время".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("H:mm:ss");
            } else if ("час".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("H");
            } else if ("минуты".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("m");
            } else if ("секунды".equalsIgnoreCase(senderText)) {
                format = new SimpleDateFormat("s");
            }

            if(format != null){
                sendTextMessage("Информация для " + senderName + ": " + format.format(Calendar.getInstance().getTime()));
            }
        }
    }
    /***************************************************************/
}
