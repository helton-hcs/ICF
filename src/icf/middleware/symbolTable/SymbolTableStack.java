package icf.middleware.symbolTable;

public interface SymbolTableStack {
    int getCurrentNestingLevel();
    SymbolTable getLocalSymbolTable();
    SymbolTableEntry enterLocal(String name);
    SymbolTableEntry lookupLocal(String name);
    SymbolTableEntry lookup(String name);
}
