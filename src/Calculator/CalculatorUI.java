package Calculator;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CalculatorUI
{
    private Button[] numberButtons;
    private Button additionButton;
    private Button subtractionButton;
    private Button divisionButton;
    private Button multiplicationButton;
    private Button pointButton;
    private Button computeButton;
    private Button clearButton;
    private Button openBracketButton;
    private Button closingBracketButton;

    private Menu menu;

    private Scene calculatorScene;
    private GridPane calculatorGridLayout;
    private MenuBar menuBar;
    private Label inputHistory;
    private Label result;
    private GridPane buttonGridLayout;

    private final Paint fontColor = Paint.valueOf("#787878");
    private final Paint backgroundColor = Paint.valueOf("#0f0f0f");
    private final Paint darkButtonColor = Paint.valueOf("#141414");
    private final Paint lightButtonColor = Paint.valueOf("#1c1c1c");
    private final Paint clearButtonColor = Paint.valueOf("#632c63"); // red #7a3935
    private final Paint computeButtonColor = Paint.valueOf("#632c63");

    public CalculatorUI()
    {
        initializeAttributes();
        setCalculatorLayout();
    }

    private void initializeAttributes()
    {
        this.numberButtons = new Button[10];
        for (int i = 0; i < this.numberButtons.length; i++)
        {
            numberButtons[i] = createButton(String.valueOf(i), this.lightButtonColor);
        }

        this.additionButton = createButton("+", this.darkButtonColor);
        this.subtractionButton = createButton("-", this.darkButtonColor);
        this.divisionButton = createButton("/", this.darkButtonColor);
        this.multiplicationButton = createButton("*", this.darkButtonColor);

        this.clearButton = createButton("Clear", this.clearButtonColor);
        this.openBracketButton = createButton("(", this.darkButtonColor);
        this.closingBracketButton = createButton(")", this.darkButtonColor);
        this.pointButton = createButton(".", this.darkButtonColor);

        this.computeButton = createButton("=", this.computeButtonColor);

        this.menu = new Menu("Menü");

        this.calculatorGridLayout = new GridPane();
        this.calculatorScene = new Scene(this.calculatorGridLayout, 400, 600);
        this.menuBar = new MenuBar();
        this.inputHistory = new Label();
        this.result = new Label();
        this.buttonGridLayout = new GridPane(4, 4);
    }

    private void setCalculatorLayout()
    {
        this.menuBar.getMenus().add(this.menu);
        this.menuBar.setBackground(Background.fill(this.backgroundColor));

        this.calculatorGridLayout.add(this.menuBar, 0, 0);
        this.calculatorGridLayout.add(this.inputHistory, 0, 1);
        this.calculatorGridLayout.add(this.result, 0, 2);
        this.calculatorGridLayout.add(this.buttonGridLayout, 0, 3);

        this.calculatorGridLayout.setBackground(Background.fill(this.backgroundColor));

        for (int i = 0; i < calculatorGridLayout.getRowCount(); i++)
        {
            RowConstraints rc = new RowConstraints();
            switch(i)
            {
                case 0: // Menu Bar
                    rc.setVgrow(Priority.NEVER);
                    rc.setFillHeight(true);
                    this.calculatorGridLayout.getRowConstraints().add(rc);
                    break;
                case 1: // History
                    rc.setVgrow(Priority.ALWAYS);
                    rc.setFillHeight(true);
                    rc.setPrefHeight(calculatorScene.getHeight() * 0.1);
                    this.calculatorGridLayout.getRowConstraints().add(rc);
                    break;
                case 2: // Result
                    rc.setVgrow(Priority.ALWAYS);
                    rc.setFillHeight(true);
                    rc.setPrefHeight(calculatorScene.getHeight() * 0.2);
                    this.calculatorGridLayout.getRowConstraints().add(rc);
                    break;
                case 3: // Button Grid
                    rc.setVgrow(Priority.ALWAYS);
                    rc.setFillHeight(true);
                    rc.setPrefHeight(calculatorScene.getHeight() * 0.7);
                    this.calculatorGridLayout.getRowConstraints().add(rc);
                    break;
                default:
                    rc.setVgrow(Priority.ALWAYS);
                    rc.setFillHeight(true);
                    this.calculatorGridLayout.getRowConstraints().add(rc);
            }
        }

        for (int i = 0; i < calculatorGridLayout.getColumnCount(); i++)
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            cc.setFillWidth(true);
            this.calculatorGridLayout.getColumnConstraints().add(cc);
        }

        this.inputHistory.setTextAlignment(TextAlignment.RIGHT);
        this.inputHistory.setAlignment(Pos.CENTER_RIGHT);
        this.inputHistory.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.inputHistory.setTextFill(this.fontColor);

        this.result.setTextAlignment(TextAlignment.RIGHT);
        this.result.setAlignment(Pos.CENTER_RIGHT);
        this.result.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.result.setTextFill(this.fontColor);

        this.buttonGridLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        this.buttonGridLayout.add(this.clearButton,         0, 0);
        this.buttonGridLayout.add(this.openBracketButton,   1, 0);
        this.buttonGridLayout.add(this.closingBracketButton,2, 0);
        this.buttonGridLayout.add(this.additionButton,      3, 0);

        this.buttonGridLayout.add(this.numberButtons[7], 0, 1);
        this.buttonGridLayout.add(this.numberButtons[8], 1, 1);
        this.buttonGridLayout.add(this.numberButtons[9], 2, 1);
        this.buttonGridLayout.add(this.subtractionButton,3, 1);

        this.buttonGridLayout.add(this.numberButtons[4],     0, 2);
        this.buttonGridLayout.add(this.numberButtons[5],     1, 2);
        this.buttonGridLayout.add(this.numberButtons[6],     2, 2);
        this.buttonGridLayout.add(this.multiplicationButton, 3, 2);

        this.buttonGridLayout.add(this.numberButtons[1], 0, 3);
        this.buttonGridLayout.add(this.numberButtons[2], 1, 3);
        this.buttonGridLayout.add(this.numberButtons[3], 2, 3);
        this.buttonGridLayout.add(this.divisionButton,   3, 3);

        this.buttonGridLayout.add(this.pointButton,      0, 4);
        this.buttonGridLayout.add(this.numberButtons[0], 1, 4);
        this.buttonGridLayout.add(this.computeButton,    2, 4);

        GridPane.setColumnSpan(this.computeButton, 2);

        for (int i = 0; i < buttonGridLayout.getRowCount(); i++)
        {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            rc.setFillHeight(true);
            this.buttonGridLayout.getRowConstraints().add(rc);
        }

        for (int i = 0; i < buttonGridLayout.getColumnCount(); i++)
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            cc.setFillWidth(true);
            cc.setMaxWidth(calculatorScene.getWidth() * 0.25);
            this.buttonGridLayout.getColumnConstraints().add(cc);
        }

    }

    public Button createButton(String text, Paint color)
    {
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);
        button.setBackground(Background.fill(color));
        button.setTextFill(this.fontColor);
        return button;
    }

    public void update(String historyText, String result)
    {
        this.inputHistory.setText(historyText);
        this.inputHistory.setFont(new Font(this.inputHistory.getHeight()/2));
        this.result.setText(result);
        this.result.setFont(new Font(this.result.getHeight()/2.5));

        for (Node b : buttonGridLayout.getChildren())
        {
            if (b instanceof Button)
            {
                ((Button) b).setFont(new Font(((Button) b).getHeight()/4));
            }
        }

        for(ColumnConstraints cc : this.buttonGridLayout.getColumnConstraints())
        {
            cc.setMaxWidth(calculatorScene.getWidth() * 0.25);
            cc.setPrefWidth(calculatorScene.getWidth() * 0.25);
        }

        for(RowConstraints rc : this.buttonGridLayout.getRowConstraints())
        {
            rc.setMaxHeight(calculatorScene.getHeight() * 0.2);
            rc.setPrefHeight(calculatorScene.getHeight() * 0.2);
        }

        for (int i = 0; i < this.calculatorGridLayout.getRowCount(); i++)
        {
            RowConstraints rc = this.calculatorGridLayout.getRowConstraints().get(i);
            switch(i)
            {
                case 0: // Menu Bar
                    break;
                case 1: // History
                    rc.setPrefHeight(calculatorScene.getHeight() * 0.1);
                    break;
                case 2: // Result
                    rc.setPrefHeight(calculatorScene.getHeight() * 0.2);
                    break;
                case 3: // Button Grid
                    rc.setPrefHeight(calculatorScene.getHeight() * 0.7);
                    break;
                default:
                    break;
            }
        }
    }


    // ####################################################################################################
    // ####################################################################################################
    // ####################################################################################################
    // ####################################################################################################
    // ####################################################################################################

    public Scene getCalculatorScene()
    {
        return this.calculatorScene;
    }

    public Button[] getNumberButtons()
    {
        return numberButtons;
    }

    public Button getAdditionButton()
    {
        return additionButton;
    }

    public Button getSubtractionButton()
    {
        return subtractionButton;
    }

    public Button getDivisionButton()
    {
        return divisionButton;
    }

    public Button getMultiplicationButton()
    {
        return multiplicationButton;
    }

    public Button getPointButton()
    {
        return pointButton;
    }

    public Button getComputeButton()
    {
        return computeButton;
    }

    public Button getClearButton()
    {
        return clearButton;
    }

    public Button getOpenBracketButton()
    {
        return openBracketButton;
    }

    public Button getClosingBracketButton()
    {
        return closingBracketButton;
    }

    public Label getInputHistory()
    {
        return inputHistory;
    }

    public Label getResult()
    {
        return result;
    }
}
