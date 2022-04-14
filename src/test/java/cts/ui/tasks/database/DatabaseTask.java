package cts.ui.tasks.database;

import cts.ui.model.database.*;
import io.cucumber.datatable.DataTable;
import lib.Database;

import java.sql.SQLException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


public class DatabaseTask {
    private String sqlForm =
            "SELECT %s\n" +
                    "FROM %s.%s\n" +
                    "WHERE CustID = '%s'";
    private List<InputVerify> inputVerifiesList = new ArrayList<>();

    public DatabaseTask(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        TableType table;
        ColumnType column;
        String expect;
        for (Map<String, String> map : list) {
            table = TableType.getTableTypeByName(map.get(TableType.inputName));
            column = ColumnType.valueOf(map.get(ColumnType.inputName));
            expect = String.valueOf(map.get("exp"));
            this.inputVerifiesList.add(new InputVerify(table, column, expect));
        }
    }

    public void performDBTask(String CustID) {
        String sql;
        List<String> custIDList = DataPrepareTask.getData(CustID);

        for (String custIDIterm : custIDList) {
            for (InputVerify list : inputVerifiesList) {
                Database database = new Database(list.table.getDbType());
                sql = String.format(sqlForm, list.column.toString(), list.table.getDbType(), list.table, String.join("", custIDIterm));
                List<String> actList = new ArrayList<>();
                ArrayList<HashMap<String, String>> act = new ArrayList<>();

                try {
                    act = database.exeQuery(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (HashMap hashMap : act) {
                    actList.add(String.valueOf(hashMap.get(list.column.toString())));
                }
                assertThat(actList).contains(list.expect);
            }
        }
    }
}
