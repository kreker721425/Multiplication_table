package elements;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MyLabel {
    private final Label lb;

    public MyLabel(String str, int sizeFont, int x, int y){
        lb = new Label();
        lb.setText(str);                                                      //Установка текста
        lb.setFont(Font.font("Arial", FontWeight.BOLD, sizeFont));      //Настройка шрифта
        lb.setLayoutX(x);                                                     //Положение по x
        lb.setLayoutY(y);                                                     //Положение по y
    }

    public Label getLb() {
        return lb;
    }

    public void setText(String str) {
        this.lb.setText(str);
    }

    public String getText() {
        return lb.getText();
    }
}
