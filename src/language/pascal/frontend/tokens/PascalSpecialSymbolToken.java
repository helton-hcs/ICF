package language.pascal.frontend.tokens;

import icf.frontend.Source;
import language.pascal.frontend.PascalErrorCode;
import language.pascal.frontend.PascalToken;
import language.pascal.frontend.PascalTokenType;

public class PascalSpecialSymbolToken extends PascalToken {

    public PascalSpecialSymbolToken(Source source) throws Exception {
        super(source);
    }

    @Override
    protected void extract() throws Exception {
        char currentChar = currentChar();

        lexeme = Character.toString(currentChar);
        type = null;

        switch (currentChar) {

            // Single-character special symbols.
            case '+':  case '-':  case '*':  case '/':  case ',':
            case ';':  case '\'': case '=':  case '(':  case ')':
            case '[':  case ']':  case '{':  case '}':  case '^': {
                nextChar();  // consume character
                break;
            }

            // : or :=
            case ':': {
                currentChar = nextChar();  // consume ':';

                if (currentChar == '=') {
                    lexeme += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }

            // < or <= or <>
            case '<': {
                currentChar = nextChar();  // consume '<';

                if (currentChar == '=') {
                    lexeme += currentChar;
                    nextChar();  // consume '='
                }
                else if (currentChar == '>') {
                    lexeme += currentChar;
                    nextChar();  // consume '>'
                }

                break;
            }

            // > or >=
            case '>': {
                currentChar = nextChar();  // consume '>';

                if (currentChar == '=') {
                    lexeme += currentChar;
                    nextChar();  // consume '='
                }

                break;
            }

            // . or ..
            case '.': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '.') {
                    lexeme += currentChar;
                    nextChar();  // consume '.'
                }

                break;
            }

            default: {
                nextChar();  // consume bad character
                type = PascalTokenType.ERROR;
                value = PascalErrorCode.INVALID_CHARACTER;
            }
        }

        // Set the type if it wasn't an error.
        if (type == null)
            type = PascalTokenType.SPECIAL_SYMBOLS.get(lexeme);
    }

}
