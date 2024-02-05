package Calculator;

import javafx.application.Application;
import javafx.stage.*;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage window)
    {
        Calculator calc = new Calculator();
    }
}
