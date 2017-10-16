package biblioteka;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;

/**
 * Created by Pro808 on 16.10.2017.
 */
public class Scene_Admin  extends GridPane {

    public TextField nameUserCreateLogin = new TextField();
    public TextField nameUserCreatePass = new TextField();
    public TextField nameUserDelete = new TextField();

    public Scene_Admin()
    {
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);

        Button logout = new Button("Выход");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Main.logoutAdmin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        logout.setPrefSize(150,30);
        add(logout,10,0);

        Label labelName = new Label("Добавить юзера:");
        labelName.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        Label labelPass = new Label("Добавить пароль:");
        labelPass.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        Button createNewUser = new Button("Создать юзера");
        createNewUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean noUser = true;
                for(int i = 0;i < Main.listUsersDb.size();i++)
                {
                    if(nameUserCreateLogin.getText().equals(Main.listUsersDb.get(i).login))
                    {
                        noUser = false;
                    }
                }
                if(noUser)
                {
                    String data = "";

                    File dbUsers = new File("./src/biblioteka/users.txt");
                    try {
                        BufferedReader readDbUsers = new BufferedReader(new InputStreamReader(new FileInputStream(dbUsers)));

                        String lineNow = "";

                        while ((lineNow = readDbUsers.readLine()) != null)
                        {
                            data += lineNow + "\n";
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                   OutputStream os;
                    try {
                        os = new FileOutputStream(new File("./src/biblioteka/users.txt"));
                        data += nameUserCreateLogin.getText() + ":" + nameUserCreatePass.getText() + ":1" + "\n";
                        os.write(data.getBytes(), 0, data.length());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        add(labelName, 2, 3);
        add(labelPass,2,5);
        add(nameUserCreateLogin,6,3);
        add(nameUserCreatePass,6,5);
        add(createNewUser,8,4);

        Label deleteLabel = new Label("Удалить юзера:");
        deleteLabel.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        add(deleteLabel, 2, 10);
        add(nameUserDelete,6,10);

        Button deleteUser = new Button("Удалить юзера");
        deleteUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                boolean existUser = false;
                String nameUserToDelete = "";
                for(int i = 0;i < Main.listUsersDb.size();i++)
                {
                    if(nameUserDelete.getText().equals(Main.listUsersDb.get(i).login))
                    {
                        existUser = true;
                        nameUserToDelete = Main.listUsersDb.get(i).login;
                    }
                }
                if(existUser)
                {
                    String data = "";

                    File dbUsers = new File("./src/biblioteka/users.txt");
                    try {
                        BufferedReader readDbUsers = new BufferedReader(new InputStreamReader(new FileInputStream(dbUsers)));

                        String lineNow = "";

                        while ((lineNow = readDbUsers.readLine()) != null)
                        {
                            String[] temp = lineNow.split(":");
                            if(!temp[0].equals(nameUserToDelete)) {
                                data += lineNow + "\n";
                            }
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    OutputStream os;
                    try {
                        os = new FileOutputStream(new File("./src/biblioteka/users.txt"));
                        os.write(data.getBytes(), 0, data.length());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        add(deleteUser, 8, 10);


    }

}
