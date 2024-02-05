package Calculator;

public class RecursiveDescentParser
{
    private MathLexer lexer;

    private abstract class TreeNote
    {
        public abstract double eval();
        public abstract void print();
    }

    private class Add extends TreeNote
    {
        private TreeNote left;
        private TreeNote right;

        public Add(TreeNote left, TreeNote right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval()
        {
            return left.eval() + right.eval();
        }

        @Override
        public void print()
        {
            System.out.println("(" + left.eval() + " + " + right.eval() + ")");
        }
    }

    private class Subtract extends TreeNote
    {
        private TreeNote left;
        private TreeNote right;

        public Subtract(TreeNote left, TreeNote right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval()
        {
            return left.eval() - right.eval();
        }

        @Override
        public void print()
        {
            System.out.println("(" + left.eval() + " - " + right.eval() + ")");
        }
    }

    private class Multiply extends TreeNote
    {
        private TreeNote left;
        private TreeNote right;

        public Multiply(TreeNote left, TreeNote right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval()
        {
            return left.eval() * right.eval();
        }

        @Override
        public void print()
        {
            System.out.println("(" + left.eval() + " * " + right.eval() + ")");
        }
    }

    private class Divide extends TreeNote
    {
        private TreeNote left;
        private TreeNote right;

        public Divide(TreeNote left, TreeNote right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval()
        {
            return left.eval() / right.eval();
        }

        @Override
        public void print()
        {
            System.out.println("(" + left.eval() + " / " + right.eval() + ")");
        }
    }

    private class Number extends TreeNote
    {
        private double n;

        public Number(String n)
        {
            this.n = Integer.parseInt(n);
        }

        @Override
        public double eval()
        {
            return n;
        }

        @Override
        public void print()
        {
            System.out.println(n);
        }
    }

    public double evalute(String s)
    {
        lexer = new MathLexer(s);
        lexer.scanToken();
        TreeNote result = parseExpression();
        return result.eval();
    }

    private TreeNote parseExpression()
    {
        TreeNote term = parseTerm();
        while (true) {
            switch (lexer.nextToken()) {
                case "+":
                    lexer.scanToken();
                    term = new Add(term, parseTerm());
                    break;
                case "-":
                    lexer.scanToken();
                    term = new Subtract(term, parseTerm());
                    break;
                default:
                    return term;
            };
        }
    }

    private TreeNote parseTerm()
    {
        TreeNote factor = parseFactor();
        while (true) {
            switch (lexer.nextToken()) {
                case "*":
                    lexer.scanToken();
                    factor = new Multiply(factor, parseFactor());
                    break;
                case "/":
                    lexer.scanToken();
                    factor = new Divide(factor, parseFactor());
                    break;
                default:
                    return factor;
            };
        }
    }

    private TreeNote parseFactor()
    {
        if (Token.fromString(lexer.nextToken()) == Token.LPAR) {
            lexer.scanToken();
            TreeNote expr = parseExpression();
            if (expr == null) return null;
            if (Token.fromString(lexer.nextToken()) == Token.RPAR) {
                lexer.scanToken();
                return expr;
            } else {
                return null;
            }
        } else if (Token.fromString(lexer.nextToken()) == Token.NUMBER){
            Number n = new Number(lexer.nextToken());
            lexer.scanToken();
            return n;
        } else {
            return null;
        }
    }
}

