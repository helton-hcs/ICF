package language.pascal.frontend;

import icf.frontend.*;
import icf.message.Message;
import icf.message.MessageType;
import icf.middleware.symbolTable.SymbolTableEntry;

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
                if (tokenType == PascalTokenType.IDENTIFIER) {
                    String name = token.getLexeme().toLowerCase();
                    SymbolTableEntry entry = symbolTableStack.lookup(name);
                    if (entry == null)
                        entry = symbolTableStack.enterLocal(name);
                    entry.appendLineNumber(token.getLineNumber());
                }
                else if (tokenType == PascalTokenType.ERROR)
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
