package icf.middleware.symbolTable;

import java.util.List;

public interface SymbolTable {
    int getNestingLevel();
    SymbolTableEntry enter(String name);
    SymbolTableEntry lookup(String name);
    List<SymbolTableEntry> sortedEntries();
}
