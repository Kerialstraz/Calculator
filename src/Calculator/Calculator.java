package Calculator;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Calculator
{
    private final Stage window;
    private final CalculatorUI ui;
    private final CalculatorLogic logic;

    private final int[] minSize = {450, 600};

    private String calculationStatement;
    private String result;
    private boolean clearCalculationStatement;

    public Calculator()
    {
        this.ui = new CalculatorUI();
        this.logic = new CalculatorLogic();

        this.window = new Stage();
        window.setTitle("Calculator for bitches by bitches ~");
        window.setMinWidth(minSize[0]);
        window.setMinHeight(minSize[1]);
        window.setScene(this.ui.getCalculatorScene());
        window.show();
        window.heightProperty().addListener((obs, oldV, newV) -> {
            if(newV != oldV) update();
        });

        window.widthProperty().addListener((obs, oldV, newV) -> {
            if(newV != oldV) update();
        });

        this.calculationStatement = "";
        this.result = "";

        setHandles();
        update();
    }

    public void update()
    {
        ui.update(calculationStatement, result);
    }

    private void setHandles()
    {
        for (Button b : ui.getNumberButtons())
        {
            b.setOnAction(e -> {
                if (logic.checkValidInput(b.getText())) {
                    if (clearCalculationStatement) {
                        this.calculationStatement = "";
                        clearCalculationStatement = false;
                    }
                    calculationStatement += b.getText();
                    update();
                }
            });
        }

        ui.getAdditionButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getAdditionButton().getText())) {
                if (clearCalculationStatement) {
                    this.calculationStatement = "";
                    clearCalculationStatement = false;
                }
                calculationStatement += ui.getAdditionButton().getText();
                logic.setNewNumberStarted(true);
                update();
            }
        });

        ui.getSubtractionButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getSubtractionButton().getText())) {
                if (clearCalculationStatement) {
                    this.calculationStatement = "";
                    clearCalculationStatement = false;
                }
                calculationStatement += ui.getSubtractionButton().getText();
                logic.setNewNumberStarted(true);
                update();
            }
        });

        ui.getDivisionButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getDivisionButton().getText())) {
                if (clearCalculationStatement) {
                    this.calculationStatement = "";
                    clearCalculationStatement = false;
                }
                calculationStatement += ui.getDivisionButton().getText();
                logic.setNewNumberStarted(true);
                update();
            }
        });

        ui.getMultiplicationButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getMultiplicationButton().getText())) {
                if (clearCalculationStatement) {
                    this.calculationStatement = "";
                    clearCalculationStatement = false;
                }
                calculationStatement += ui.getMultiplicationButton().getText();
                logic.setNewNumberStarted(true);
                update();
            }
        });

        ui.getPointButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getPointButton().getText())) {
                if (clearCalculationStatement) {
                    this.calculationStatement = "";
                    clearCalculationStatement = false;
                }
                calculationStatement += ui.getPointButton().getText();
                logic.setNewNumberStarted(false);
                update();
            }
        });

        ui.getClearButton().setOnAction(e -> {
            this.calculationStatement = "";
            this.result = "";
            logic.clearLastInput();
            logic.setNewNumberStarted(true);
            update();
        });

        ui.getOpenBracketButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getOpenBracketButton().getText())) {
                if (clearCalculationStatement) {
                    this.calculationStatement = "";
                    clearCalculationStatement = false;
                }
                calculationStatement += ui.getOpenBracketButton().getText();
                update();
            }
        });

        ui.getClosingBracketButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getClosingBracketButton().getText())) {
                if (clearCalculationStatement) {
                    this.calculationStatement = "";
                    clearCalculationStatement = false;
                }
                calculationStatement += ui.getClosingBracketButton().getText();
                update();
            }
        });

        ui.getComputeButton().setOnAction(e -> {
            if (logic.checkValidInput(ui.getComputeButton().getText())) {
                this.result = logic.evaluateCalculationStatement(this.calculationStatement);
                if(this.result.equals("69.0")) this.result = "You pervert!"; // REMOVE THIS SHIT IF YOU RELEASE YOU FUCKING IDIOT
                if(this.result.equals("1.0")) this.result = "Life is a dark mess that will never make you happy, go kill yourself while you still can and end this useless life";
                this.clearCalculationStatement = true;
                logic.setNewNumberStarted(true);
                logic.clearLastInput();
                update();
            }
        });

    }
}
