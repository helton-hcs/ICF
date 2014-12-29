package icf.frontend.pascal;

import icf.frontend.EofToken;
import icf.frontend.Lexer;
import icf.frontend.Source;
import icf.frontend.Token;

public class PascalLexer extends Lexer {

    public PascalLexer(Source source) {
        super(source);
    }

    @Override
    protected Token extractToken() throws Exception {
        Token token;
        char currentChar = currentChar();

        if (currentChar == Source.EOF)
            token = new EofToken(source);
        else
            token = new Token(source);

        return token;
    }
}
