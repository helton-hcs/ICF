package icf.backend.interpreter;

import icf.backend.Backend;
import icf.message.Message;
import icf.message.MessageType;
import icf.middleware.intermediateCode.IntermediateCode;
import icf.middleware.symbolTable.SymbolTableStack;

public class Executor extends Backend {

    @Override
    public void process(IntermediateCode intermediateCode, SymbolTableStack symbolTableStack) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        int executionCount = 0;
        int runtimeErrors = 0;

        sendMessage(
                new Message(MessageType.INTERPRETER_SUMMARY,
                        new Number[] {
                                executionCount,
                                runtimeErrors,
                                elapsedTime
                        }
                )
        );
    }

}