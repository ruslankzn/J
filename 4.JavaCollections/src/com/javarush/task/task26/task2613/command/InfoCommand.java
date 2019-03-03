package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.info");
    @Override
    public void execute() throws InterruptOperationException{
        boolean money = false;
        ConsoleHelper.writeMessage(res.getString("before"));
        for(CurrencyManipulator currencyManipulator: CurrencyManipulatorFactory.getAllCurrencyManipulators())
            if (currencyManipulator.hasMoney()) {
            if(currencyManipulator.getTotalAmount()>0){
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
                money = true;
            }
        }
        if(!money)
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
