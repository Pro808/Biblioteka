package biblioteka;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Pro808 on 14.10.2017.
 */
public class EventButton implements EventHandler<ActionEvent> {


    private Object obj;

    private String objName = "";

    private HashMap<String, String> param = new HashMap<>();

    EventButton(Object obj, String objName) {
        this.obj = obj;
        this.objName = objName;
    }

    @Override
    public void handle(ActionEvent event) {

        if (this.objName.equals("Login")) {

            Scene_Login objTemp = (Scene_Login) this.obj;

            String[] user = new String[]{objTemp.nameText.getText(), objTemp.passText.getText()};

            for (int i = 0; i < Main.listUsersDb.size(); i++) {

                if (Main.listUsersDb.get(i).login.equals(user[0]))
                {
                    if(Main.listUsersDb.get(i).pass.equals(user[1]))
                    {
                        try {
                            Main.changeSceneToBiblioteka(user[0],Main.listUsersDb.get(i).group);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        if (this.objName.equals("reUser"))
        {
            Main.changeSceneToAdmin();
        }

    }
}
