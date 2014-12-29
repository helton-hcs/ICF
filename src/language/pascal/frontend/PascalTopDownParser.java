package language.pascal.frontend;

import icf.frontend.*;
import icf.message.Message;
import icf.message.MessageType;

import java.io.IOException;

public class PascalTopDownParser extends Parser {
    protected static PascalErrorHandler errorHandler;

    static {
        errorHandler = new PascalErrorHandler();
    }

    public PascalTopDownParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();

        try {
            while(!((token = nextToken()) instanceof EofToken)) {
                TokenType tokenType = token.getType();
                if (tokenType == PascalTokenType.ERROR) {
                    sendMessage(
                            new Message(
                                MessageType.TOKEN,
                                new Object[] {
                                        token.getLineNumber(),
                                        token.getPosition(),
                                        tokenType,
                                        token.getLexeme(),
                                        token.getValue()
                                }
                            )
                    );
                }
                else
                    errorHandler.flag(token, (PascalErrorCode)token.getValue(), this);
            }

            float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
            sendMessage(
                    new Message(
                            MessageType.PARSER_SUMMARY,
                            new Number[] {
                                    token.getLineNumber(),
                                    getErrorCount(),
                                    elapsedTime
                            }
                    )
            );
        }
        catch(IOException ex) {
            errorHandler.abortTranslation(PascalErrorCode.IO_ERROR, this);
        }
    }

    @Override
    public int getErrorCount() throws Exception {
        return errorHandler.getErrorCount();
    }

}
