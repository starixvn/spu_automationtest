package cts.ui.model.database;

public class InputVerify {
    public TableType table;
    public ColumnType column;
    public String expect;

    public InputVerify(TableType table, ColumnType column, String expect) {
        this.table = table;
        this.column = column;
        this.expect = expect;
    }
}
