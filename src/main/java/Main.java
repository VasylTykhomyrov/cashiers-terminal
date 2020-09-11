
import dao.DBConnector;
import dao.TablesCreation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import provider.DatabaseProvider;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cashiersTerminal.fxml"));
        primaryStage.setTitle("Cashiers Terminal");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Thread operations = new Thread(new DatabaseProvider(
                new TablesCreation(),
                new DBConnector()));
        operations.start();

        launch(args);
    }
}
