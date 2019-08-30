package com.d3lph1.bridge.client.forms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

public final class FormUtil
{
    private FormUtil()
    {
    }

    public static Stage constructMainFormStage() throws IOException
    {
        return constructGenericFormStage("/main_form.fxml");
    }

    public static Pair<FXMLLoader, Stage> constructMainFormStageAndGetWithLoader() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FormUtil.class.getResource("/main_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Bridge");
        stage.setResizable(false);
        stage.setScene(scene);
        scene.getStylesheets().add(FormUtil.class.getResource("/common.css").toExternalForm());

        return new Pair<>(fxmlLoader, stage);
    }

    public static Stage constructConnectionFormStage() throws IOException
    {
        return constructGenericFormStage("/connection_form.fxml");
    }

    public static Pair<FXMLLoader, Stage> constructEndGameFormStage() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FormUtil.class.getResource("/end_game_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("End of the game");
        stage.setResizable(false);
        stage.setScene(scene);
        scene.getStylesheets().add(FormUtil.class.getResource("/common.css").toExternalForm());

        return new Pair<>(fxmlLoader, stage);
    }

    public static Stage constructGenericFormStage(String fxmlPath) throws IOException
    {
        return constructGenericFormStage(fxmlPath, "Bridge");
    }

    public static Stage constructGenericFormStage(String fxmlPath, String title) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FormUtil.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);

        return stage;
    }
}
