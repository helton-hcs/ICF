package icf.middleware.symbolTable.impl;

import icf.middleware.symbolTable.SymbolTableKey;

public enum SymbolTableKeyImpl implements SymbolTableKey {
    //Constants
    CONSTANT_VALUE,

    //Procedure or Function
    ROUTINE_CODE, ROUTINE_SYMBOL_TABLE, ROUTINE_INTERMEDIATE_CODE,
    ROUTINE_PARAMETERS, ROUTINE_ROUTINES,

    //Value or record field value
    DATA_VALUE
}
