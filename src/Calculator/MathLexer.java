package Calculator;

public class MathLexer
{
    private String stringToIterateThrough;
    private String next;
    private int nextIndex;

    public MathLexer(String stringToIterateThrough)
    {
        if (!stringToIterateThrough.isEmpty())
        {
            this.stringToIterateThrough = stringToIterateThrough;
            this.nextIndex = 0;
        }
    }

    public String nextToken()
    {
        return next;
    }

    public void scanToken()
    {
        if (hasNextToken() && Token.fromString(String.valueOf(stringToIterateThrough.charAt(nextIndex))) == Token.NUMBER) {
            StringBuilder builder = new StringBuilder();
            while (hasNextToken() && Token.fromString(String.valueOf(stringToIterateThrough.charAt(nextIndex))) == Token.NUMBER) {
                builder.append(stringToIterateThrough.charAt(nextIndex));
                nextIndex++;
            }
            next = builder.toString();
        } else if (hasNextToken()) {
            String s = String.valueOf(stringToIterateThrough.charAt(nextIndex));
            nextIndex++;
            next = s;
        } else {
            // do nothing
        }
    }

    public boolean hasNextToken()
    {
        return nextIndex < stringToIterateThrough.length();
    }
}
