package language.pascal.frontend;

import icf.frontend.Parser;
import icf.frontend.Token;
import icf.message.Message;
import icf.message.MessageType;

public class PascalErrorHandler {
    private static final int MAX_ERRORS = 25;
    private static int errorCount = 0;

    public int getErrorCount() {
        return errorCount;
    }

    public void flag(Token token, PascalErrorCode errorCode, Parser parser) {
        parser.sendMessage(
                new Message(
                    MessageType.SYNTAX_ERROR,
                    new Object[] {
                            token.getLineNumber(),
                            token.getPosition(),
                            token.getLexeme(),
                            errorCode.toString()
                    }
                )
        );
        if (++errorCount > MAX_ERRORS)
            abortTranslation(PascalErrorCode.TOO_MANY_ERRORS, parser);
    }

    public void abortTranslation(PascalErrorCode errorCode, Parser parser)
    {
        String fatalText = "FATAL ERROR: " + errorCode.toString();
        parser.sendMessage(
                new Message(
                        MessageType.SYNTAX_ERROR,
                        new Object[] {
                                0,
                                0,
                                "",
                                fatalText
                        }
                )
        );
        System.exit(errorCode.getStatus());
    }
}
