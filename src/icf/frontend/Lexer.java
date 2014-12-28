package icf.frontend;

import icf.frontend.token.Token;

public abstract class Lexer {
    protected Source source;
    private Token currentToken;

    public Lexer(Source source) {
        this.source = source;
    }

    public Token currentToken() {
        return currentToken;
    }

    public Token nextToken() throws Exception {
        currentToken = extractToken();
        return currentToken;
    }

    public abstract Token extractToken() throws Exception;

    public char currentChar() throws Exception {
        return source.currentChar();
    }

    public char nextChar() throws Exception {
        return source.nextChar();
    }

}
