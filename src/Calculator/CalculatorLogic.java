package Calculator;

public class CalculatorLogic
{
    private String lastInput = "";
    private boolean newNumberStarted = true;
    private final String[] specialCharacters = {"+", "-", "/", "*"};
    private int numberOfOpenParanthesis;
    private int numberOfClosedParanthesis;


    public CalculatorLogic()
    {

    }

    public boolean checkValidInput(String input)
    {
        if((checkIfSpecialCharacter(lastInput) && checkIfSpecialCharacter(input)) || (lastInput.isEmpty() && checkIfSpecialCharacter(input))) return false;
        if(input.equals(".") && !newNumberStarted) return false;
        this.lastInput = input;
        return true;
    }

    public String evaluateCalculationStatement(String calculationStatement)
    {
        RecursiveDescentParser parser = new RecursiveDescentParser();
        parser.evalute(calculationStatement);
        return String.valueOf(parser.evalute(calculationStatement));
    }

    private boolean checkIfSpecialCharacter(String symbol)
    {
        for (String s : specialCharacters) {
            if (symbol.equals(s)) return true;
        }
        return false;
    }

    public void clearLastInput()
    {
        this.lastInput = "";
    }

    public void setNewNumberStarted(boolean value)
    {
        this.newNumberStarted = value;
    }
}
