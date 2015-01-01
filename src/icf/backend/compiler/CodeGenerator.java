package icf.backend.compiler;

import icf.backend.Backend;
import icf.message.Message;
import icf.message.MessageType;
import icf.middleware.intermediateCode.IntermediateCode;
import icf.middleware.symbolTable.SymbolTableStack;

public class CodeGenerator extends Backend {

    @Override
    public void process(IntermediateCode intermediateCode, SymbolTableStack symbolTableStack) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        int instructionCount = 0;

        sendMessage(
                new Message(MessageType.COMPILER_SUMMARY,
                        new Number[] {
                                instructionCount,
                                elapsedTime
                        }
                )
        );
    }

}
