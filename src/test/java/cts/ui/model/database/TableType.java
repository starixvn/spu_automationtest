package cts.ui.model.database;

import static cts.ui.model.database.ColumnType.*;
import static cts.ui.model.database.DBType.*;

public enum TableType {
    CTSCustomerClassification_InputDataLog("CTSCustomerClassification_InputDataLog", new ColumnType[]{FunctionID, InputValue}, CTS_DataCenter),
    CTSCustomerClassification("CTSCustomerClassification", new ColumnType[]{CategoryID}, CTS_DataCenter),
    CTSCustomerClassification_History("CTSCustomerClassification_History", new ColumnType[]{CategoryID, SourceTypeID}, CTS_DataCenter),
    CTSCustomerClassification_Log("CTSCustomerClassification_Log", new ColumnType[]{CategoryID, SourceTypeID}, CTS_DataCenter),
    DBO_Custinfo("dbo.custinfo", new ColumnType[]{CustomerClass, Danger2}, CustDB),
    DBO_CustCategoryHistory("dbo.CustCategoryHistory", new ColumnType[]{CategoryID}, CustDB);

    private String tableName;
    private ColumnType[] columnTypes;
    public static String inputName = "Table";
    private DBType dbType;


    TableType(String tableName, ColumnType[] columnTypes, DBType dbType) {
        this.tableName = tableName;
        this.columnTypes = columnTypes;
        this.dbType = dbType;
    }

    public static TableType getTableTypeByName(String value) {
        for (TableType tableType : TableType.values()) {
            if (tableType.getTableName().equalsIgnoreCase(value)) {
                return tableType;
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

    public String getTableName() {
        return tableName;
    }

    public DBType getDbType() {
        return dbType;
    }
}
