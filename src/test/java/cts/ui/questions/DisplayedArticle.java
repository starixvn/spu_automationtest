package cts.ui.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class DisplayedArticle {
    public static Question<String> articleText(Target target) {
        return Text.of(target);
    }
}
