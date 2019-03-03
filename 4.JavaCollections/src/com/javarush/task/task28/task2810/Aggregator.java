package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.MoikrugStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;


import java.io.IOException;


public class Aggregator {
    public static void main(String[] args) throws IOException {
        HtmlView view = new HtmlView();
        Provider providers = new Provider(new HHStrategy() );
        Provider mkProvider = new Provider(new MoikrugStrategy());
       // Model model = new Model(view, providers);
        Model model = new Model(view, providers,mkProvider);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
