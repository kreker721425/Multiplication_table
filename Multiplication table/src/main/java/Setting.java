import elements.*;
import exceptions.InequalityException;
import exceptions.IntervalException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

class Setting {

    private final MyAlert numberFormatAlert = new MyAlert(Alert.AlertType.ERROR,"Строки должны содержать только цифры");
    private final MyAlert inequalityAlert = new MyAlert(Alert.AlertType.ERROR,"Должно соблюдаться неравенство: \n'5'% > '4'% > '3'%");
    private final MyAlert intervalAlert = new MyAlert(Alert.AlertType.ERROR,"Интервал значений должен быть [1,99]");

    void method(Window stage, Label menuLb) {

        Stage settingStage = new Stage();

        MyLabel lbText = new MyLabel("Введите проценты оценивания\n      в соответствующие окна",25,65,10);
        MyLabel lbFive = new MyLabel("Оценка - '5'. Процент правильных ответов больше",16,20,90);
        MyLabel lbFour = new MyLabel("Оценка - '4'. Процент правильных ответов больше",16,20,130);
        MyLabel lbThree = new MyLabel("Оценка - '3'. Процент правильных ответов больше",16,20,170);

        MyTextField tfFive = new MyTextField(50,15,430,85);
        MyTextField tfFour = new MyTextField(50,15,430,125);
        MyTextField tfThree = new MyTextField(50,15,430,165);

        MyButton btnExit = new MyButton("Отмена",20,150,75,200);
        btnExit.setOnActionExit(settingStage);

        MyButton btnNext = new MyButton("Принять",20,150,275,200);
        btnNext.btn.setOnAction(event -> {
            try {
                int intFive = Integer.parseInt(tfFive.getText());
                int intFour = Integer.parseInt(tfFour.getText());
                int intThree = Integer.parseInt(tfThree.getText());

                //Проверка корректности ввода
                if ((intFive <= intFour) || (intFour <= intThree)) {
                    throw new InequalityException();
                }

                //Проверка корректности ввода
                if ((intFive > 99) || (intThree <= 0)) {
                    throw new IntervalException();
                }
                Menu.settingArr.set(0, intFive);
                Menu.settingArr.set(1, intFour);
                Menu.settingArr.set(2, intThree);
                menuLb.setText("Это программа для проверки знаний таблицы умножения\n" +
                        "                   Процент оценивания следующий:\n" +
                        "             '5' - > " + Menu.settingArr.get(0) + "%, '4' - > " + Menu.settingArr.get(1) + "%, " +
                        "'3' - > " + Menu.settingArr.get(2) + "%, '2' - <= " + Menu.settingArr.get(2) + "%\n");
                settingStage.close();
            } catch (NumberFormatException e) {
                numberFormatAlert.showAndWait();
            } catch (InequalityException e) {
                inequalityAlert.showAndWait();
            } catch (IntervalException e) {
                intervalAlert.showAndWait();
            }
        });



        Pane settingPane = new Pane();
        settingPane.getChildren().addAll(lbText.getLb(),lbFive.getLb(),lbFour.getLb(),lbThree.getLb(),tfFive.getTf(),
                tfFour.getTf(),tfThree.getTf(), btnExit.getBtn(),btnNext.getBtn());

        BorderPane settingRoot = new BorderPane();
        settingRoot.setTop(settingPane);

        Scene settingScene = new Scene(settingRoot, 500, 250);
        settingScene.getStylesheets().add(Setting.class.getResource("css/Setting.css").toExternalForm());

        MyStage stg = new MyStage(settingStage,settingScene,stage);
        stg.show();

        //Метод для передвижения окна по экрану
        com.sun.glass.ui.Window.getWindows().get(1).setUndecoratedMoveRectangle(30);

    }

}