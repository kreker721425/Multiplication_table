package elements;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class MyStage {

    private Stage stg;

    public MyStage(Stage stage, Scene scene){
        stg = stage;
        stg.setResizable(false);                    //Отключение растягивания окна
        stg.setScene(scene);                        //Подключение Scene к Stage
        stg.initStyle(StageStyle.TRANSPARENT);      //Отключает панель управления(свернуть, развернуть, закрыть)
    }

    public MyStage(Stage stage, Scene scene, Window window){
        stg = stage;
        stg.setResizable(false);
        stg.setScene(scene);
        stg.initStyle(StageStyle.TRANSPARENT);
        stg.initOwner(window);
        stg.initModality(Modality.WINDOW_MODAL);    //Привязка дочернего окна к родительскому, без возможности закрытия родительского
    }

    public void show() {
        stg.show();
    }

    public void showAndWait() {
        stg.showAndWait();
    }
}
