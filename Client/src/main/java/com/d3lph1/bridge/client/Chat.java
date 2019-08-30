package com.d3lph1.bridge.client;

import com.d3lph1.bridge.client.forms.MainFormController;
import javafx.application.Platform;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat
{
    private MainFormController mainFormController;

    public Chat(MainFormController mainFormController)
    {
        this.mainFormController = mainFormController;
    }

    public void display(String message, String source)
    {
        Platform.runLater(() -> {
            mainFormController.getChatTextArea().appendText(
                    String.format(
                            "[%s] %s: %s\n",
                            new SimpleDateFormat("HH:mm:ss").format(new Date()),
                            source,
                            message
                    )
            );
            mainFormController.getChatTextArea().setScrollTop(Double.MAX_VALUE);
        });
    }
}
