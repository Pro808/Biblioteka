package biblioteka;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Pro808 on 14.10.2017.
 */
public class Main extends Application {

    public static ArrayList<UserPair> listUsersDb = new ArrayList<>();

    public static Stage stage;



    public static void initDbUser()
    {
        File dbUsers = new File("./src/biblioteka/users.txt");
        try {
            BufferedReader readDbUsers = new BufferedReader(new InputStreamReader(new FileInputStream(dbUsers)));

            String lineNow = "";

            while ((lineNow = readDbUsers.readLine()) != null)
            {
                String[] temp = lineNow.split(":");

                listUsersDb.add(new UserPair(temp[0],temp[1],temp[2]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void changeSceneToBiblioteka(String Login,String Group) throws IOException {
        stage.close();
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setScene(new Scene(new Scene_Biblioteka(Login,Group)));
        stage.show();
    }
    public static void changeSceneToAdmin() {
        stage.close();
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setScene(new Scene(new Scene_Admin()));
        stage.show();
    }
    public static void logoutBiblioteka()
    {
        stage.close();
        stage.setWidth(600);
        stage.setHeight(350);
        stage.setScene(new Scene(new Scene_Login()));
        stage.show();
    }
    public static void logoutAdmin() throws IOException {
        stage.close();
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setScene(new Scene(new Scene_Biblioteka("Admin","0")));
        stage.show();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        initDbUser();
        primaryStage.setWidth(600);
        primaryStage.setHeight(350);

        primaryStage.setTitle("LoginForm");
        Scene Logining = new Scene(new Scene_Login());

        primaryStage.setScene(Logining);
        primaryStage.show();
    }



}

    /*  public Main ()
      {
          setTitle("Войти в библиотеку");
          setSize(new Dimension(600,250));
          setLocationRelativeTo(null);

          JPanel panel = new JPanel(new GridBagLayout());

          JLabel labelName = new JLabel("Имя пользователя: ");
          labelName.setSize(150,30);
          JTextField nameText = new JTextField("Admin");
          nameText.setSize(1300,30);
          JLabel PassLabel= new JLabel("Пароль пользователя: ");
          JPasswordField PassText = new JPasswordField("11111");

          panel.add(labelName);
          panel.add(nameText);

          panel.add(PassLabel);
          panel.add(PassText);

          add(panel);


          setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          setVisible(true);
      }
  */