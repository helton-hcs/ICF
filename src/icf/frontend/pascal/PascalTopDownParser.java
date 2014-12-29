package icf.frontend.pascal;

import icf.frontend.EofToken;
import icf.frontend.Lexer;
import icf.frontend.Parser;
import icf.frontend.Token;
import icf.message.Message;
import icf.message.MessageType;

public class PascalTopDownParser extends Parser {

    public PascalTopDownParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();

        while(!((token = nextToken()) instanceof EofToken));

        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        sendMessage(
                new Message(MessageType.PARSER_SUMMARY,
                        new Number[] {
                                token.getLineNumber(),
                                getErrorCount(),
                                elapsedTime
                        }
                )
        );
    }

    @Override
    public int getErrorCount() throws Exception {
        return 0;
    }

}
