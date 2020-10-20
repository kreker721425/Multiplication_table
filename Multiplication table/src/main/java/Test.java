import elements.*;
import exceptions.EmptyFieldException;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.text.SimpleDateFormat;
import java.util.*;


class Test {

    private final MyAlert invalidInputAlert = new MyAlert(Alert.AlertType.ERROR, "Строка должна содержать только цифры");
    private final ArrayList<Integer> mistakes = new ArrayList<>();
    private int a = (int) (Math.random() * 9);
    private int b = (int) (Math.random() * 9);
    private int number = 0;
    private int mistakesNumber = 0;
    private final Date timeNow = new Date();
    private final Timer timer = new Timer();


    void method(Window stage) {

        Stage testStage = new Stage();

        MyLabel lbText = new MyLabel("Введите правильный ответ", 30, 155, 30);
        MyLabel lbExample = new MyLabel(a + "  x  " + b + "  =", 90, 70, 100);
        MyLabel lbTime = new MyLabel("Время: ",20,275,230);

        //Создание таймера выполнения теста
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Date timeNew = new Date();
                timeNew.setHours(timeNew.getHours() - timeNow.getHours());
                timeNew.setMinutes(timeNew.getMinutes() - timeNow.getMinutes());
                timeNew.setSeconds(timeNew.getSeconds() - timeNow.getSeconds());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                        "HH:mm:ss", Locale.getDefault());
                final String strDate = simpleDateFormat.format(timeNew);
                Platform.runLater(() -> lbTime.setText("Время: " + strDate));
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

        MyTextField tfReturn = new MyTextField(170, 70, 450, 80);

        MyButton btnExit = new MyButton("Закончить", 20, 320, 25, 270);
        btnExit.btn.setOnAction(event -> {
            End end = new End();
            timer.cancel();

            //Если не нажимали кнопку "Принять", то выход из Test, не заходя в End
            if (number > 0) {
                end.method(testStage, number, mistakesNumber, mistakes, lbTime.getText());
            }
            testStage.close();
        });

        MyButton btnNext = new MyButton("Принять", 20, 320, 355, 270);
        btnNext.btn.setOnAction(event -> {
            try {

                //Если поле ввода пустое, считается за ошибку
                if (tfReturn.getText().equals(""))
                    throw new EmptyFieldException();

                //Проверка на правильность ответа
                if (a * b != Integer.parseInt(tfReturn.getText())) {
                    mistakesNumber++;
                    mistakes.add(a);
                    mistakes.add(b);
                    mistakes.add(Integer.parseInt(tfReturn.getText()));
                }
                number++;
            } catch (NumberFormatException e) {
                invalidInputAlert.showAndWait();
            } catch (EmptyFieldException e) {
                mistakesNumber++;
                number++;
            }
            a = (int) (Math.random() * 9);
            b = (int) (Math.random() * 9);
            lbExample.setText(a + "  x  " + b + "  =");
            tfReturn.setText("");
        });


        Pane testPane = new Pane();
        testPane.getChildren().addAll(lbText.getLb(), lbExample.getLb(), lbTime.getLb(),btnExit.getBtn(), btnNext.getBtn(), tfReturn.getTf());

        BorderPane testRoot = new BorderPane();
        testRoot.setTop(testPane);

        Scene testScene = new Scene(testRoot, 700, 350);
        testScene.getStylesheets().add(Test.class.getResource("css/Test.css").toExternalForm());

        MyStage stg = new MyStage(testStage, testScene, stage);
        stg.show();

        //Метод для передвижения окна по экрану
        com.sun.glass.ui.Window.getWindows().get(1).setUndecoratedMoveRectangle(30);
    }
}
