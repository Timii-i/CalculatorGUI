package de.hsworms.ztt.keidel.calculator.gui;
// class for gui tests

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;


public class GUITest extends ApplicationTest {

    Label postfixLabel;
    Label infixLabel;
    Label resultLabel;
    Button zeroButton;
    Button oneButton;
    Button twoButton;
    Button threeButton;
    Button fourButton;
    Button fiveButton;
    Button sixButton;
    Button sevenButton;
    Button eightButton;
    Button nineButton;
    Button sumButton;
    Button plusButton;
    Button subtractButton;
    Button dotButton;
    Button acButton;
    Button multiplyButton;
    Button divideButton;
    Button logButton;
    Button lnButton;
    Button piButton;
    Button eButton;
    Button sqrtButton;
    Button leftBracketButton;
    Button rightBracketButton;
    Button moduloButton;
    Button exponentButton;
    Button sinButton;
    Button cosButton;
    Button tanButton;
    Button facButton;

    @Override
    public void start(Stage stage) throws Exception {
        MainLayout mainLayout = new MainLayout();
        mainLayout.start(stage);

        // Lookup Buttons and Labels to use for testing
        postfixLabel = lookup("#resultLabel").query();
        infixLabel = lookup("#resultLabel").query();
        resultLabel = lookup("#resultLabel").query();
        zeroButton = lookup("#0").query();
        oneButton = lookup("#1").query();
        twoButton = lookup("#2").query();
        threeButton = lookup("#3").query();
        fourButton = lookup("#4").query();
        fiveButton = lookup("#5").query();
        sixButton = lookup("#6").query();
        sevenButton = lookup("#7").query();
        eightButton = lookup("#8").query();
        nineButton = lookup("#9").query();
        sumButton = lookup("#=").query();
        plusButton = lookup("#+").query();
        subtractButton = lookup("#-").query();
        dotButton = lookup("#.").query();
        acButton = lookup("#AC").query();
        multiplyButton = lookup("#*").query();
        divideButton = lookup("#/").query();
        logButton = lookup("#log()").query();
        lnButton = lookup("#ln()").query();
        piButton = lookup("#\u03C0").query();
        eButton = lookup("#e").query();
        sqrtButton = lookup("#\u221A").query();
        leftBracketButton = lookup("#(").query();
        rightBracketButton = lookup("#)").query();
        moduloButton = lookup("#%").query();
        exponentButton = lookup("#^").query();
        sinButton = lookup("#sin()").query();
        cosButton = lookup("#cos()").query();
        tanButton = lookup("#tan()").query();
        facButton = lookup("#fac()").query();

    }

}
