package biblioteka;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;

/**
 * Created by Pro808 on 16.10.2017.
 */
public class Scene_Admin  extends Pane {

    public TextField nameUserCreateLogin = new TextField();
    public TextField nameUserCreatePass = new TextField();
    public TextField nameUserDelete = new TextField();

    public Scene_Admin()
    {

        GridPane paneForItems = new GridPane();

        paneForItems.setAlignment(Pos.CENTER);
        paneForItems.setHgap(10);
        paneForItems.setVgap(10);


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
        paneForItems.add(labelName, 1, 3);
        paneForItems.add(labelPass,1,5);
        paneForItems.add(nameUserCreateLogin,2,3);
        paneForItems.add(nameUserCreatePass,2,5);
        paneForItems.add(createNewUser,3,4);

        Label deleteLabel = new Label("Удалить юзера:");
        deleteLabel.setFont(Font.font("ARIAl", FontWeight.BOLD, 18));
        paneForItems.add(deleteLabel, 1, 10);
        paneForItems.add(nameUserDelete,2,10);

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
        paneForItems.add(deleteUser, 3, 10);



        Region backPanelLoginFormm = new Region();
        Pane panelForBack = new Pane(backPanelLoginFormm);
        backPanelLoginFormm.setPrefSize(Main.stage.getWidth()*0.8,Main.stage.getHeight()*0.6);
        panelForBack.setStyle("-fx-background-color: rgba(0,0,0,0.05);-fx-border-color: #ccc;-fx-border-width: 3px;");
        panelForBack.setLayoutX(Main.stage.getWidth()*0.10);
        panelForBack.setLayoutY((Main.stage.getHeight()*0.2 - 20));

        paneForItems.setLayoutX((Main.stage.getWidth()*0.12));
        paneForItems.setLayoutY((Main.stage.getHeight()*0.20 - 20));

        paneForItems.setPrefSize(Main.stage.getWidth()*0.75,Main.stage.getHeight()*0.5);
        getChildren().add(panelForBack);
        getChildren().add(paneForItems);


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
        logout.setLayoutX(Main.stage.getWidth() - 180);
        logout.setLayoutY(10);
        getChildren().add(logout);
    }

}
