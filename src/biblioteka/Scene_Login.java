package biblioteka;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Pro808 on 15.10.2017.
 */
public class Scene_Login extends Pane{

    public TextField nameText = new TextField("Admin");
    public PasswordField passText = new PasswordField();

    public Scene_Login() {

        GridPane formPaneMain = new GridPane();

        formPaneMain.setAlignment(Pos.CENTER);
        formPaneMain.setHgap(10);
        formPaneMain.setVgap(10);

        Label labelName = new Label("Имя  пользователя:");
        labelName.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        formPaneMain.add(labelName, 2, 3);

        formPaneMain.add( nameText, 4, 3);

        Label passLabel = new Label("Пароль пользователя:");
        passLabel.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        formPaneMain.add(passLabel, 2, 5);

        formPaneMain.add(passText, 4, 5);



        Region backPanelLoginFormm = new Region();
        Pane panelForBack = new Pane(backPanelLoginFormm);
        backPanelLoginFormm.setPrefSize(Main.stage.getWidth()*0.8,Main.stage.getHeight()*0.6);
        panelForBack.setStyle("-fx-background-color: rgba(0,0,0,0.05);-fx-border-color: #ccc;-fx-border-width: 3px;");
        panelForBack.setLayoutX(Main.stage.getWidth()*0.10);
        panelForBack.setLayoutY((Main.stage.getHeight()*0.2 - 25));
        formPaneMain.setLayoutX((Main.stage.getWidth()*0.10 + 15));
        formPaneMain.setLayoutY((Main.stage.getHeight()*0.2 + 10));

        getChildren().addAll(panelForBack);
        getChildren().addAll(formPaneMain);



        Button loginButton = new Button("Войти");
        loginButton.setPrefSize(150,35);
        loginButton.setLayoutX(Main.stage.getWidth()* 0.375);
        loginButton.setLayoutY(Main.stage.getHeight()*0.6);
        getChildren().add(loginButton);
        loginButton.setOnAction(new EventButton(this,"Login"));



        setStyle("-fx-background-color: white");
        Scene_Login thisScene = this;
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                {
                    loginButton.fire();
                }
            }
        });

    }
}
