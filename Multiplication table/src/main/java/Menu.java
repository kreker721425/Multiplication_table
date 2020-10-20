import elements.MyButton;
import elements.MyLabel;
import elements.MyStage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Menu extends Application {

    public static final ArrayList<Integer> settingArr = new ArrayList<>();

    public static void main(String[] args){
        settingArr.add(90);
        settingArr.add(70);
        settingArr.add(50);
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        MyLabel LbHello = new MyLabel("Привет, дорогой друг!",30,180,80);
        MyLabel lbAbout = new MyLabel("Это программа для проверки знаний таблицы умножения\n"+
                "       По умолчанию процент оценивания следующий:\n" +
                "             '5' - > "+settingArr.get(0)+"%, '4' - > "+
                settingArr.get(1)+"%, '3' - > "+settingArr.get(2)+"%, '2' - <= "+settingArr.get(2)+"%\n",
                20,65,150);

        MyButton btnStart = new MyButton("Начать тест", 19, 320, 25, 292);
        btnStart.btn.setOnAction(event -> {
            Test testClass = new Test();
            testClass.method(stage);
        });

        MyButton btnSetting = new MyButton("Настроить процент оценивания", 19, 320, 355, 292);
        btnSetting.btn.setOnAction(event -> {
            Setting settingClass = new Setting();
            settingClass.method(stage, lbAbout.getLb());
        });

        MyButton btnExit = new MyButton("Выход", 19, 650, 25, 340);
        btnExit.setOnActionExit(stage);



        BorderPane root = new BorderPane();
        Pane pane = new Pane();
        pane.getChildren().addAll(btnStart.getBtn(),btnSetting.getBtn(),btnExit.getBtn(),lbAbout.getLb(),LbHello.getLb());
        root.setTop(pane);

        Scene scene = new Scene(root, 700, 400, Color.TRANSPARENT);
        scene.getStylesheets().add(Menu.class.getResource("css/Menu.css").toExternalForm());

        MyStage stg = new MyStage(stage,scene);
        stg.show();

        //Метод для передвижения окна по экрану
        com.sun.glass.ui.Window.getWindows().get(0).setUndecoratedMoveRectangle(30);

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

}