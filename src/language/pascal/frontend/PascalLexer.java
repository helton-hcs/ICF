package language.pascal.frontend;

import icf.frontend.EofToken;
import icf.frontend.Lexer;
import icf.frontend.Source;
import icf.frontend.Token;
import language.pascal.frontend.tokens.*;

public class PascalLexer extends Lexer {

    public PascalLexer(Source source) {
        super(source);
    }

    @Override
    protected Token extractToken() throws Exception {
        skipWhiteSpace();
        Token token;
        char currentChar = currentChar();

        if (currentChar == Source.EOF)
            token = new EofToken(source);
        else if (Character.isLetter(currentChar))
            token = new PascalWordToken(source);
        else if (Character.isDigit(currentChar))
            token = new PascalNumberToken(source);
        else if (currentChar == '\'')
            token = new PascalStringToken(source);
        else if (PascalTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar)))
            token = new PascalSpecialSymbolToken(source);
        else {
            token = new PascalErrorToken(source, PascalErrorCode.INVALID_CHARACTER, Character.toString(currentChar));
            nextChar();
        }

        return token;
    }

    private void skipWhiteSpace() throws Exception {
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar) || (currentChar == '{')) {
            if (currentChar == '{') {
                do {
                    currentChar = nextChar();
                } while ((currentChar != '}') && (currentChar != Source.EOF));
                if (currentChar == '}') {
                    currentChar = nextChar();
                }
            }
            else
                currentChar = nextChar();
        }
    }

}
