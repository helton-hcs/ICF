package icf.frontend;

public class Token {
    protected TokenType type;
    protected String lexeme;
    protected Object value;
    protected Source source;
    protected int lineNumber;
    protected int position;

    public Token(Source source) throws Exception {
        this.source = source;
        this.lineNumber = source.getLineNumber();
        this.position = source.getPosition();
        extract();
    }

    protected void extract() throws Exception {
        lexeme = Character.toString(currentChar());
        value = null;
        nextChar();
    }

    protected char currentChar() throws Exception {
        return source.currentChar();
    }

    protected char nextChar() throws Exception {
        return source.nextChar();
    }

    protected char peekChar() throws Exception {
        return source.peekChar();
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Object getValue() {
        return value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPosition() {
        return position;
    }
}
