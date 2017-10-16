package biblioteka;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Pro808 on 15.10.2017.
 */
public class Scene_Login extends GridPane{

    public TextField nameText = new TextField("Admin");
    public PasswordField passText = new PasswordField();

    public Scene_Login() {

        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);

        Label labelName = new Label("Имя  пользователя:");
        labelName.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        add(labelName, 0, 3);

        add( nameText, 2, 3);

        Label passLabel = new Label("Пароль пользователя:");
        passLabel.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        add(passLabel, 0, 5);

        add(passText, 2, 5);

        Button loginButton = new Button("Войти");
        add(loginButton, 1, 7);

        loginButton.setOnAction(new EventButton(this,"Main"));

    }
}
