package icf.middleware.symbolTable;

import icf.middleware.symbolTable.impl.SymbolTableEntryImpl;
import icf.middleware.symbolTable.impl.SymbolTableImpl;
import icf.middleware.symbolTable.impl.SymbolTableStackImpl;

public class SymbolTableFactory {

    public static SymbolTableStack createSymbolTableStack() {
        return new SymbolTableStackImpl();
    }

    public static SymbolTable createSymbolTable(int nestingLevel) {
        return new SymbolTableImpl(nestingLevel);
    }

    public static SymbolTableEntry createSymbolTableEntry(String name, SymbolTable symbolTable) {
        return new SymbolTableEntryImpl(name, symbolTable);
    }

}
