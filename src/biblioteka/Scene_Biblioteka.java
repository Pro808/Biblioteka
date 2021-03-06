package biblioteka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.*;
/**
 * Created by Pro808 on 14.10.2017.
 */
public class Scene_Biblioteka extends AnchorPane{

    String nameUser = "";
    String groupUser = "";

    public Text textBookLabel = new Text("TEXT");

    public Scene_Biblioteka(String nameUser,String groupUser) throws IOException {
        this.nameUser = nameUser;
        this.groupUser = groupUser;

        Pane pane = new Pane(textBookLabel);

        ScrollPane scrollPane = new ScrollPane(pane);

        ListView<itemBook> viewBooks = new ListView<>();

        Label nameUserLabel = new Label(this.nameUser + ((this.groupUser.equals("0")) ? " - Admin":" - User"));

        nameUserLabel.setFont(Font.font("ARIAl", FontWeight.BOLD, 14));

        if(this.groupUser.equals("0")) {

            Button reUser = new Button("Работа с юзерами");
            reUser.setPrefSize(200, 30);
            setLeftAnchor(reUser, 12.5d);
            setTopAnchor(reUser, 10d);

            setLeftAnchor(nameUserLabel,reUser.getPrefWidth() + 22.5d);
            setTopAnchor(nameUserLabel,15d);

            setTopAnchor(viewBooks,50d);

            getChildren().add(reUser);

            reUser.setOnAction(new EventButton(this,"reUser"));

        }else
        {
            setLeftAnchor(nameUserLabel,12.5d);
            setTopAnchor(nameUserLabel,3.5d);
            setTopAnchor(viewBooks,25d);
        }

        Button logout = new Button("Выход");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.logoutBiblioteka();
            }
        });
        logout.setPrefSize(200, 30);
        setRightAnchor(logout, 12.5d);
        setTopAnchor(logout, 10d);

        scrollPane.setPrefSize(Main.stage.getWidth()*0.6,Main.stage.getHeight() - 150 - nameUserLabel.getHeight());
        pane.setPrefWidth(Main.stage.getWidth()*0.6);
        pane.setMinHeight(Main.stage.getHeight() - 150 - nameUserLabel.getHeight());
        scrollPane.setStyle("-fx-background-color: white;-fx-padding: 0px 30px 0px 30px;");
        pane.setStyle("-fx-background-color: white;");
        textBookLabel.setFont(Font.font("ARIAl", FontWeight.findByWeight(500), 14));
        textBookLabel.setStyle("-fx-background-color: white;");
        textBookLabel.setTextAlignment(TextAlignment.LEFT);
        setRightAnchor(scrollPane,25d);
        setTopAnchor(scrollPane,nameUserLabel.getHeight() + 28.5 + logout.getPrefHeight());


        viewBooks.setMinSize(Main.stage.getWidth()*0.33,Main.stage.getHeight() - 150 - nameUserLabel.getHeight());
        viewBooks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); setLeftAnchor(viewBooks,0d);


        viewBooks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(int i =0; i < viewBooks.getItems().size();i++)
                {
                    if(viewBooks.getItems().get(i).nameBook.split("[.]")[0].equals(event.getTarget().toString().split("'")[1].replace("'","")))
                    {
                        String textBookFromFIle = "";
                        try {
                            File book = new File(viewBooks.getItems().get(i).pathBook);

                            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(book), "Cp1251"));

                            String lineNow = "";

                            while ((lineNow = in.readLine()) != null)
                            {
                                textBookFromFIle += lineNow + "\n";
                            }
                            in.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        textBookLabel.setText(textBookFromFIle);
                    }
                }
            }
        });

        getChildren().add(logout);
        getChildren().add(viewBooks);
        getChildren().add(nameUserLabel);
        getChildren().addAll(scrollPane);

        ObservableList<itemBook> listBooks = FXCollections.observableArrayList();


        File folderUsersBooks = new File("./src/UsersBooks/" + this.nameUser + "/");

        if(!folderUsersBooks.exists())
        {
            folderUsersBooks.mkdirs();
        }

        File[] folderEntries = folderUsersBooks.listFiles();

        for (File entry : folderEntries)
        {
            if (!entry.isDirectory()){
                listBooks.add(new itemBook(this.nameUser,entry.getName(),entry.getAbsolutePath()));
            }
        }

        viewBooks.setItems(listBooks);

    }

}
