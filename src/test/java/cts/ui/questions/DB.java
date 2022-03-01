package cts.ui.questions;

import lib.Database;
import net.serenitybdd.screenplay.Question;

import java.sql.SQLException;

public class DB {
    static Database db = new Database();
    public static Question<String> queryData(String query) {
        return Question.about("").answeredBy(
                actor -> {
                    try {
                        return db.getData(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }
}
