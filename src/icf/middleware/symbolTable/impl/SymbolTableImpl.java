package icf.middleware.symbolTable.impl;

import icf.middleware.symbolTable.SymbolTable;
import icf.middleware.symbolTable.SymbolTableEntry;
import icf.middleware.symbolTable.SymbolTableFactory;

import java.util.*;

public class SymbolTableImpl extends TreeMap<String, SymbolTableEntry> implements SymbolTable {
    private int nestingLevel;

    public SymbolTableImpl(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    @Override
    public int getNestingLevel() {
        return nestingLevel;
    }

    @Override
    public SymbolTableEntry enter(String name) {
        SymbolTableEntry entry = SymbolTableFactory.createSymbolTableEntry(name, this);
        put(name, entry);
        return entry;
    }

    @Override
    public SymbolTableEntry lookup(String name) {
        return get(name);
    }

    @Override
    public List<SymbolTableEntry> sortedEntries() {
        Collection<SymbolTableEntry> entries = values();
        Iterator<SymbolTableEntry> iterator = entries.iterator();
        List<SymbolTableEntry> list = new ArrayList<>(size());

        while (iterator.hasNext())
            list.add(iterator.next());

        return list;
    }
}
