package icf.middleware.symbolTable;

import java.util.List;

public interface SymbolTableEntry {
    String getName();
    SymbolTable getSymbolTable();
    void appendLineNumber(int lineNumber);
    List<Integer> getLineNumbers();
    void setAttribute(SymbolTableKey key, Object value);
    Object getAttribute(SymbolTableKey key);
}
