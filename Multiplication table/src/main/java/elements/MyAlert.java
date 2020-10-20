package elements;

import javafx.scene.control.Alert;

public class MyAlert {

    private Alert alert;

    public MyAlert(Alert.AlertType alertType, String ContentText){
        alert = new Alert(alertType);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(ContentText);
    }

    public void showAndWait() {
        alert.showAndWait();
    }
}
