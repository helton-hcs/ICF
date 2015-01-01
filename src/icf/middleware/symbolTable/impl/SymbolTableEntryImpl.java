package icf.middleware.symbolTable.impl;

import icf.middleware.symbolTable.SymbolTable;
import icf.middleware.symbolTable.SymbolTableEntry;
import icf.middleware.symbolTable.SymbolTableKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTableEntryImpl extends HashMap<SymbolTableKey, Object> implements SymbolTableEntry {
    private String name;
    private SymbolTable symbolTable;
    private List<Integer> lineNumbers;

    public SymbolTableEntryImpl(String name, SymbolTable symbolTable) {
        this.name = name;
        this.symbolTable = symbolTable;
        this.lineNumbers = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public void appendLineNumber(int lineNumber) {
        lineNumbers.add(lineNumber);
    }

    @Override
    public List<Integer> getLineNumbers() {
        return lineNumbers;
    }

    @Override
    public void setAttribute(SymbolTableKey key, Object value) {
        put(key, value);
    }

    @Override
    public Object getAttribute(SymbolTableKey key) {
        return get(key);
    }
}
