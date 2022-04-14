package cts.ui.tasks.database;

import cts.ui.model.database.AssociationType;
import cts.ui.model.database.DBType;
import cts.ui.model.database.InputTaskType;
import cts.ui.model.database.RoleType;
import io.cucumber.datatable.DataTable;
import lib.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cts.ui.model.database.InputTaskType.ExistedAssociation;
import static cts.ui.model.database.InputTaskType.ExistedAssociationValue;

public class DataPrepareTask {
    private Map<InputTaskType, String> conditionList = new HashMap<>();
    private String queryForm =
            "SELECT ag.Site, ag.CTSCustID, ag.CustID, ag.Username, m.CustID AS MemberCTSCustID, m.CustID AS MemberCustID, m.Username AS MemberUsername\n" +
                    "FROM CTS_DataCenter.CTSCustomer AS ag\n" +
                    "INNER JOIN CTS_DataCenter.CTSCustomer AS m\n" +
                    "ON m.Recommend = ag.CustID\n" +
                    "AND m.RoleID = %s WHERE ag.Site = '%s' AND NOT EXISTS (SELECT 1 FROM CTS_DataCenter.AssociationByDevice AS ass WHERE ass.CTSCustID = ag.CTSCustID)\n" +
                    "LIMIT %d";
    public static ArrayList<HashMap> preList;

    public DataPrepareTask(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (InputTaskType.getDataType(key)) {
                    case RoleType:
                        this.conditionList.put(InputTaskType.RoleType, String.valueOf(RoleType.getRoleType((String) value).getRoleID()));
                        break;
                    case ExistedAssociation:
                        this.conditionList.put(ExistedAssociation, AssociationType.getAssociationType((String) value).getAssociationName());
                        break;
                    case ExistedAssociationValue:
                        this.conditionList.put(ExistedAssociationValue, (String) value);
                        break;
                    case GenerateAssociation:
                        this.conditionList.put(InputTaskType.GenerateAssociation, AssociationType.getAssociationType((String) value).getAssociationName());
                        break;
                    case GenerateAssociationValue:
                        this.conditionList.put(InputTaskType.GenerateAssociationValue, (String) value);
                        break;
                    case Site:
                        this.conditionList.put(InputTaskType.Site, (String) value);
                        break;
                    case Downline:
                        this.conditionList.put(InputTaskType.Downline, (String) value);
                    default:
                        break;
                }
            }
        }
        Database database = new Database(DBType.CTS_DataCenter);
        try {
            this.preList = database.exeQuery(String.format(queryForm, conditionList.get(InputTaskType.RoleType), conditionList.get(InputTaskType.Site), 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getData(String value) {
        List<String> out = new ArrayList<>();
        for (HashMap list : preList) {
            out.add(String.valueOf(list.get(value)));
        }
        return out;
    }
}