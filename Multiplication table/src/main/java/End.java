import elements.MyButton;
import elements.MyLabel;
import elements.MyStage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;


class End {

    void method(Window stage, int number, int mistakesNumber, ArrayList<Integer> mistakes, String time) {

        Stage mistakesStage = new Stage();


        MyLabel lbText1 = new MyLabel("Тест завершен!",28,73,15);
        MyLabel lbText2 = new MyLabel("Ваша статистика:  " + (number - mistakesNumber) + " / " + number,20,67,50);
        MyLabel lbText3 = new MyLabel("Ваша оценка: ",20,97,80);
        MyLabel lbTime = new MyLabel(time,18,105,110);
        MyLabel lbText4 = new MyLabel("Ниже представлены ошибки",19,35,190);
        MyLabel lbText5 = new MyLabel("",19,20,0);
        MyLabel lbText6 = new MyLabel("",19,230,0);

        //Вывод ошибок
        for (int i = 0; i < mistakes.size(); i += 3) {
            lbText5.setText(lbText5.getText() + "Ваш ответ:\nПравильный ответ:\n\n");
            lbText6.setText(lbText6.getText() + mistakes.get(i) + " x " + mistakes.get(i + 1) + " = " + mistakes.get(i + 2) +
                    "\n" + mistakes.get(i) + " x " + mistakes.get(i + 1) + " = " + (mistakes.get(i) * mistakes.get(i + 1)) + "\n\n");
        }

        //Если ошибок нет, не выводить lbText4
        if (lbText6.getText().equals("")) lbText4.setText("");

        //Высчитывается оценка
        double res=(double) ((number-mistakesNumber)*100) / number;
        if (Menu.settingArr.get(0) < res)
            lbText3.setText(lbText3.getText() + "5");
        else {
            if ((Menu.settingArr.get(1) < res) && (res <= Menu.settingArr.get(0)))
                lbText3.setText(lbText3.getText() + "4");
            else {
                if ((Menu.settingArr.get(2) < res) && (res <= Menu.settingArr.get(1)))
                    lbText3.setText(lbText3.getText() + "3");
                else
                    lbText3.setText(lbText3.getText() + "2");
            }
        }

        MyButton btnExit = new MyButton("Выход",20,150,100,145);
        btnExit.setOnActionExit(mistakesStage);



        Pane paneInScr = new Pane();
        paneInScr.getChildren().addAll(lbText5.getLb(),lbText6.getLb());

        ScrollPane scrP = new ScrollPane();
        scrP.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);   //Включить ползунок по вертикали
        scrP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);       //Отключить ползунок по горизонтали
        scrP.setContent(paneInScr);
        scrP.setPadding(new Insets(9,0,14,0));//Отступ

        Pane pane = new Pane();
        pane.getChildren().addAll(lbText1.getLb(), lbText2.getLb(), lbText3.getLb(), lbText4.getLb(),lbTime.getLb(),btnExit.getBtn());

        BorderPane root = new BorderPane();
        root.setTop(pane);
        root.setCenter(scrP);


        Scene mistakesScene = new Scene(root, 350, 350);
        mistakesScene.getStylesheets().add(End.class.getResource("css/End.css").toExternalForm());

        MyStage stg = new MyStage(mistakesStage,mistakesScene,stage);
        stg.showAndWait();

    }
}

