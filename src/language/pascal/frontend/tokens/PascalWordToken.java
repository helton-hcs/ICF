package language.pascal.frontend.tokens;

import icf.frontend.Source;
import language.pascal.frontend.PascalToken;
import language.pascal.frontend.PascalTokenType;

public class PascalWordToken extends PascalToken {

    public PascalWordToken(Source source) throws Exception {
        super(source);
    }

    @Override
    protected void extract() throws Exception {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();

        // Get the word characters (letter or digit).  The scanner has
        // already determined that the first character is a letter.
        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar();  // consume character
        }

        lexeme = textBuffer.toString();

        // Is it a reserved word or an identifier?
        type = (PascalTokenType.RESERVED_WORDS.contains(lexeme.toLowerCase()))
                ? PascalTokenType.valueOf(lexeme.toUpperCase())  // reserved word
                : PascalTokenType.IDENTIFIER;                    // identifier
    }
}
