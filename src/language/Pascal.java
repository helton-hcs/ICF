package language;

import icf.backend.Backend;
import icf.backend.BackendFactory;
import icf.backend.OperationType;
import icf.frontend.FrontendFactory;
import icf.frontend.Parser;
import icf.frontend.Source;
import icf.frontend.types.LanguageType;
import icf.frontend.types.ParserType;
import icf.message.MessageType;
import icf.middleware.IntermediateCode;
import icf.middleware.SymbolTable;

import java.io.BufferedReader;
import java.io.FileReader;

public class Pascal {
    private Parser parser;
    private Source source;
    private IntermediateCode intermediateCode;
    private SymbolTable symbolTable;
    private Backend backend;

    public Pascal(OperationType operationType, String filePath, String flags)
    {
        try {
            boolean intermediate = flags.indexOf('i') > -1;
            boolean xref         = flags.indexOf('x') > -1;

            source = new Source(new BufferedReader(new FileReader(filePath)));
            source.addMessageListener(message -> {
                MessageType type = message.getType();
                Object body[] = (Object []) message.getBody();

                switch (type) {
                    case SOURCE_LINE: {
                        int lineNumber = (Integer) body[0];
                        String lineText = (String) body[1];

                        System.out.println(String.format(SOURCE_LINE_FORMAT,
                                lineNumber, lineText));
                        break;
                    }
                }
            });

            parser = FrontendFactory.createParser(LanguageType.PASCAL, ParserType.TOP_DOWN, source);
            parser.addMessageListener(message -> {
                MessageType type = message.getType();
                switch (type) {
                    case PARSER_SUMMARY: {
                        Number body[] = (Number[]) message.getBody();
                        int statementCount = (Integer) body[0];
                        int syntaxErrors = (Integer) body[1];
                        float elapsedTime = (Float) body[2];

                        System.out.printf(PARSER_SUMMARY_FORMAT,
                                statementCount,
                                syntaxErrors,
                                elapsedTime);
                        break;
                    }
                }
            });

            backend = BackendFactory.createBackend(operationType);
            backend.addMessageListener(message -> {
                MessageType type = message.getType();

                switch (type) {
                    case INTERPRETER_SUMMARY: {
                        Number body[] = (Number[]) message.getBody();
                        int executionCount = (Integer) body[0];
                        int runtimeErrors = (Integer) body[1];
                        float elapsedTime = (Float) body[2];

                        System.out.printf(INTERPRETER_SUMMARY_FORMAT,
                                executionCount,
                                runtimeErrors,
                                elapsedTime);
                        break;
                    }
                    case COMPILER_SUMMARY: {
                        Number body[] = (Number[]) message.getBody();
                        int instructionCount = (Integer) body[0];
                        float elapsedTime = (Float) body[1];

                        System.out.printf(COMPILER_SUMMARY_FORMAT,
                                instructionCount,
                                elapsedTime);
                        break;
                    }
                }
            });

            parser.parse();
            source.close();

            intermediateCode = parser.getIntermediateCode();
            symbolTable = parser.getSymbolTable();

            backend.process(intermediateCode, symbolTable);
        }
        catch (Exception ex) {
            System.out.println("***** Internal translator error. *****");
            ex.printStackTrace();
        }
    }

    private static final String FLAGS = "[-ix]";
    private static final String USAGE =
            "Usage: language.Pascal execute|compile " + FLAGS + " <source file path>";

    public static void main(String args[])
    {
        try {
            String operation = args[0];

            // Operation.
            if (OperationType.fromString(operation) == OperationType.UNKNOWN) {
                throw new Exception();
            }

            int i = 0;
            String flags = "";

            // Flags.
            while ((++i < args.length) && (args[i].charAt(0) == '-'))
                flags += args[i].substring(1);

            // Source path.
            if (i < args.length) {
                String path = args[i];
                new Pascal(OperationType.fromString(operation), path, flags);
            }
            else
                throw new Exception();
        }
        catch (Exception ex) {
            System.out.println(USAGE);
        }
    }

    private static final String SOURCE_LINE_FORMAT = "%03d %s";

    private static final String PARSER_SUMMARY_FORMAT =
            "\n%,20d source lines." +
                    "\n%,20d syntax errors." +
                    "\n%,20.2f seconds total parsing time.\n";

    private static final String INTERPRETER_SUMMARY_FORMAT =
            "\n%,20d statements executed." +
                    "\n%,20d runtime errors." +
                    "\n%,20.2f seconds total execution time.\n";

    private static final String COMPILER_SUMMARY_FORMAT =
            "\n%,20d instructions generated." +
                    "\n%,20.2f seconds total code generation time.\n";

}
