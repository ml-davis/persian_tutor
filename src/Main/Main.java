package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static BorderPane borderPane = new BorderPane();
    private static URL previousPage = null;
    private static URL currentPage = Main.class.getResource("/View/BrowserPage.fxml");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Persian Tutor 1.1");

        // load resources
        borderPane.getStylesheets().add("/Resources/CSS/DarkTheme.css");
        Font.loadFont(Main.class.getResource("/Resources/Fonts/beyond_the_mountains.ttf").toExternalForm(), 10);
        Font.loadFont(Main.class.getResource("/Resources/Fonts/Roboto-Medium.ttf").toExternalForm(), 10);
        Font.loadFont(Main.class.getResource("/Resources/Fonts/SourceCodePro-Semibold.ttf").toExternalForm(), 10);

        MenuBar menuBar = FXMLLoader.load(getClass().getResource("/View/MenuBar.fxml"));
        borderPane.setTop(menuBar);

        Parent root = FXMLLoader.load(currentPage);
        borderPane.setCenter(root);

        primaryStage.setScene(new Scene(borderPane, 1366, 950));
        primaryStage.getIcons().add(new Image("/Media/Images/logo.png"));
        primaryStage.show();
    }

    public static void setPage(URL page) {
        previousPage = currentPage;
        currentPage = page;

        try {
            Parent root = FXMLLoader.load(page);
            borderPane.setCenter(root);

        } catch (IOException e) {
            System.out.println("Couldn't set page");
            e.printStackTrace();
        }
    }

    public static URL getPreviousPage() {
        return previousPage;
    }
}
