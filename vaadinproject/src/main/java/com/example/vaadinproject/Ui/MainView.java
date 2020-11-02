package com.example.vaadinproject.Ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class MainView extends VerticalLayout {
    public MainView(){
       /*
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField textField1 = new TextField("Number 1","Enter a Number");
        TextField textField2 = new TextField("Number 2","Enter a Number");
        TextField resultField = new TextField("Result","Show a result");
        Button addButton = new Button("Add");
        addButton.addClickListener(event -> {
             int number1 = Integer.parseInt(textField1.getValue());
            int number2 = Integer.parseInt(textField2.getValue());
            int result = number1 + number2;
            resultField.setValue("" + result);


        });
        horizontalLayout.add(textField1,textField2,addButton,resultField);
        add(horizontalLayout);

        Button button = new Button();
        button.setText("Click me");
        button.addClickListener(event -> Notification.show("hello word"));
        add(button);

        */
    }
}
