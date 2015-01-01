package icf.middleware.symbolTable.impl;

import icf.middleware.symbolTable.SymbolTable;
import icf.middleware.symbolTable.SymbolTableEntry;
import icf.middleware.symbolTable.SymbolTableFactory;
import icf.middleware.symbolTable.SymbolTableStack;

import java.util.ArrayList;

public class SymbolTableStackImpl extends ArrayList<SymbolTable> implements SymbolTableStack {
    private int currentNestingLevel;

    public SymbolTableStackImpl() {
        this.currentNestingLevel = 0;
        add(SymbolTableFactory.createSymbolTable(currentNestingLevel));
    }

    @Override
    public int getCurrentNestingLevel() {
        return currentNestingLevel;
    }

    @Override
    public SymbolTable getLocalSymbolTable() {
        return get(currentNestingLevel);
    }

    @Override
    public SymbolTableEntry enterLocal(String name) {
        return get(currentNestingLevel).enter(name);
    }

    @Override
    public SymbolTableEntry lookupLocal(String name) {
        return get(currentNestingLevel).lookup(name);
    }

    @Override
    public SymbolTableEntry lookup(String name) {
        return lookupLocal(name);
    }
}
