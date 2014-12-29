package language.pascal.frontend.tokens;

import icf.frontend.Source;
import language.pascal.frontend.PascalErrorCode;
import language.pascal.frontend.PascalToken;
import language.pascal.frontend.PascalTokenType;

public class PascalErrorToken extends PascalToken {

    public PascalErrorToken(Source source, PascalErrorCode errorCode, String lexeme) throws Exception
    {
        super(source);
        this.lexeme = lexeme;
        this.type = PascalTokenType.ERROR;
        this.value = errorCode;
    }

    protected void extract() throws Exception {
    }

}
