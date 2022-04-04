package cts.ui.model.database;

import static cts.ui.model.database.ColumnType.*;
import static cts.ui.model.database.DBType.*;

public enum TableType {
    CTSCustomerClassification_InputDataLog(new ColumnType[]{FunctionID, InputValue}, CTS_DataCenter),
    CTSCustomerClassification(new ColumnType[]{CategoryID}, CTS_DataCenter),
    CTSCustomerClassification_History(new ColumnType[]{CategoryID, SourceTypeID}, CTS_DataCenter),
    CTSCustomerClassification_Log(new ColumnType[]{CategoryID, SourceTypeID}, CTS_DataCenter);

    private ColumnType[] columnTypes;
    public static String inputName = "Table";
    private DBType dbType;


    TableType(ColumnType[] columnTypes, DBType dbType) {
        this.columnTypes = columnTypes;
        this.dbType = dbType;
    }

    public static TableType getDataType(String value) {
        for (TableType dataType : TableType.values()) {
            if (dataType.toString().equalsIgnoreCase(value)) {
                return dataType;
            }
        }
        return null;
    }

    public ColumnType[] getColumnTypes() {
        return columnTypes;
    }

    public static String getInputName() {
        return inputName;
    }

    public DBType getDbType() {
        return dbType;
    }
}
