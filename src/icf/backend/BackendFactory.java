package icf.backend;

import icf.backend.compiler.CodeGenerator;
import icf.backend.interpreter.Executor;

public class BackendFactory {

    public static Backend createBackend(OperationType operationType) throws Exception {
        switch(operationType) {
            case COMPILE:
                return new CodeGenerator();
            case EXECUTE:
                return new Executor();
            default:
                throw new Exception("Backend factory: Invalid operation type");
        }
    }

}
