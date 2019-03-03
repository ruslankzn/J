//package com.javarush.task.task26.task2613.command;
//
//import com.javarush.task.task26.task2613.ConsoleHelper;
//import com.javarush.task.task26.task2613.exception.InterruptOperationException;
//
//public class LoginCommand implements Command {
//    private String CARD_NUMBER = "123456789012";
//    private String CARD_PIN = "1234";
//    //String cardNumber, cardPinNumber;
//
//    @Override
//    public void execute() throws InterruptOperationException {
//        while (true){
//            ConsoleHelper.writeMessage("specify.data");
//            String cardNumber = ConsoleHelper.readString();
//            String cardPinNumber = ConsoleHelper.readString();
//            if (cardNumber.length() != 12) {
//                ConsoleHelper.writeMessage("try.again.or.exit");
//                continue;
//            }
//            try {
//                long checkNum = Long.parseLong(cardNumber);
//            } catch (NumberFormatException e) {
//                ConsoleHelper.writeMessage("try.again.or.exit");
//                continue;
//            }
//            if (cardPinNumber.length() != 4) {
//                ConsoleHelper.writeMessage("try.again.with.details");
//                continue;
//            }
//            try {
//                int checkPin = Integer.parseInt(cardPinNumber);
//            } catch (NumberFormatException e) {
//                ConsoleHelper.writeMessage("try.again.with.details");
//                continue;
//            }
//
//            if(isVerified(cardNumber,cardPinNumber))
//                ConsoleHelper.writeMessage("Verified");
//            else
//                ConsoleHelper.writeMessage("Invalid card number or PIN.");
///*
//            try {
//                String s = ConsoleHelper.readString();
//                if (s.equalsIgnoreCase("exit")) {
//                    break;
//                }
//            } catch (InterruptOperationException e) {
//                throw new InterruptOperationException ();
//            }*/
//        }
//
//    }
//
//    private boolean validData (String cn, String pn)
//    {
//        if (cn == null || pn == null || cn.length() != 12 || pn.length() != 4) return false;
//        else return true;
//    }
//
//    private boolean isVerified(String cn, String pn)
//    {
//        if (cn.equals(CARD_NUMBER) && pn.equals(CARD_PIN)) return true;
//        else return false;
//    }
//}


package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.login_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
        String s1 = ConsoleHelper.readString();
        String s2 = ConsoleHelper.readString();
        if (validCreditCards.containsKey(s1)) {
            if (validCreditCards.getString(s1).equals(s2))
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), s1));
            else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
        } else {
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            continue;
        }

        break;

    }
    }
}
