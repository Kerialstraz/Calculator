package Calculator;

public enum Token {
    ADD,
    SUB,
    MUL,
    DIV,
    POW,
    LPAR,
    RPAR,
    NUMBER;

    @Override
    public String toString() {
        return switch (this.ordinal()) {
            case 0 -> "+";
            case 1 -> "-";
            case 2 -> "*";
            case 3 -> "/";
            case 4 -> "^";
            case 5 -> "(";
            case 6 -> ")";
            case 7 -> this.name();
            default -> "null";
        };
    }

    public static Token fromString(String s){
        return switch (s) {
            case "+" -> Token.ADD;
            case "-" -> Token.SUB;
            case "*" -> Token.MUL;
            case "/" -> Token.DIV;
            case "^" -> Token.POW;
            case "(" -> Token.LPAR;
            case ")" -> Token.RPAR;
            default -> Token.NUMBER;
        };
    }
}