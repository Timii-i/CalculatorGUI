package de.hsworms.ztt.keidel.calculator.gui;
// class for gui tests


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
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
    Button equalsButton;
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
        postfixLabel = lookup("#postfixLabel").query();
        infixLabel = lookup("#infixLabel").query();
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
        equalsButton = lookup("#=").query();
        plusButton = lookup("#+").query();
        subtractButton = lookup("#-").query();
        dotButton = lookup("#dot").query();
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

    @Test
    public void test_dot_button(){
        clickOn(dotButton);
        Assertions.assertThat(resultLabel).hasText(".");
        clickOn(acButton);
    }
    @Test
    public void test_zero_button(){
        clickOn(zeroButton);
        Assertions.assertThat(resultLabel).hasText("0");
        clickOn(acButton);
    }
    @Test
    public void test_one_button(){
        clickOn(oneButton);
        Assertions.assertThat(resultLabel).hasText("1");
        clickOn(acButton);
    }
    @Test
    public void test_two_button(){
        clickOn(twoButton);
        Assertions.assertThat(resultLabel).hasText("2");
        clickOn(acButton);
    }
    @Test
    public void test_three_button(){
        clickOn(threeButton);
        Assertions.assertThat(resultLabel).hasText("3");
        clickOn(acButton);
    }
    @Test
    public void test_four_button(){
        clickOn(fourButton);
        Assertions.assertThat(resultLabel).hasText("4");
        clickOn(acButton);
    }
    @Test
    public void test_five_button(){
        clickOn(fiveButton);
        Assertions.assertThat(resultLabel).hasText("5");
        clickOn(acButton);
    }
    @Test
    public void test_six_button(){
        clickOn(sixButton);
        Assertions.assertThat(resultLabel).hasText("6");
        clickOn(acButton);
    }
    @Test
    public void test_seven_button(){
        clickOn(sevenButton);
        Assertions.assertThat(resultLabel).hasText("7");
        clickOn(acButton);
    }
    @Test
    public void test_eight_button(){
        clickOn(eightButton);
        Assertions.assertThat(resultLabel).hasText("8");
        clickOn(acButton);
    }
    @Test
    public void test_one_digit_infix_addition(){
        clickOn(fourButton);
        clickOn(plusButton);
        clickOn(fiveButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("9.0");
        clickOn(acButton);
    }
    @Test
    public void test_two_digit_infix_addition(){
        clickOn(oneButton);
        clickOn(threeButton);
        clickOn(plusButton);
        clickOn(twoButton);
        clickOn(sevenButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("40.0");
        clickOn(acButton);
    }
    @Test
    public void test_one_digit_infix_subtraction(){
        clickOn(fiveButton);
        clickOn(subtractButton);
        clickOn(threeButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("2.0");
        clickOn(acButton);
    }
    @Test
    public void test_two_digit_infix_subtraction(){
        clickOn(fiveButton);
        clickOn(nineButton);
        clickOn(subtractButton);
        clickOn(fourButton);
        clickOn(sixButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("13.0");
        clickOn(acButton);
    }
    @Test
    public void test_one_digit_infix_multiplication(){
        clickOn(eightButton);
        clickOn(multiplyButton);
        clickOn(twoButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("16.0");
        clickOn(acButton);
    }
    @Test
    public void test_two_digit_infix_multiplication(){
        clickOn(twoButton);
        clickOn(oneButton);
        clickOn(multiplyButton);
        clickOn(oneButton);
        clickOn(threeButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("273.0");
        clickOn(acButton);
    }
    @Test
    public void test_one_digit_infix_division(){
        clickOn(sixButton);
        clickOn(divideButton);
        clickOn(threeButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("2.0");
        clickOn(acButton);
    }
    @Test
    public void test_two_digit_infix_division(){
        clickOn(threeButton);
        clickOn(nineButton);
        clickOn(divideButton);
        clickOn(oneButton);
        clickOn(threeButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("3.0");
        clickOn(acButton);
    }
    @Test
    public void test_bracket_multiplication_order(){
        clickOn(leftBracketButton);
        clickOn(twoButton);
        clickOn(multiplyButton);
        clickOn(threeButton);
        clickOn(rightBracketButton);
        clickOn(plusButton);
        clickOn(leftBracketButton);
        clickOn(fourButton);
        clickOn(multiplyButton);
        clickOn(fiveButton);
        clickOn(rightBracketButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("26.0");
        clickOn(acButton);
    }
    @Test
    public void test_priority_addition_multiplication(){
        clickOn(sevenButton);
        clickOn(plusButton);
        clickOn(twoButton);
        clickOn(multiplyButton);
        clickOn(eightButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("23.0");
        clickOn(acButton);
    }
    @Test
    public void test_priority_addition_division(){
        clickOn(sevenButton);
        clickOn(plusButton);
        clickOn(eightButton);
        clickOn(divideButton);
        clickOn(fourButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("9.0");
        clickOn(acButton);
    }
    @Test
    public void test_priority_subtraction_multiplication(){
        clickOn(nineButton);
        clickOn(subtractButton);
        clickOn(oneButton);
        clickOn(multiplyButton);
        clickOn(sixButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("3.0");
        clickOn(acButton);
    }
    @Test
    public void test_priority_subtraction_division(){
        clickOn(fourButton);
        clickOn(subtractButton);
        clickOn(eightButton);
        clickOn(divideButton);
        clickOn(twoButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("0.0");
        clickOn(acButton);
    }
    @Test
    public void test_squareroot_(){
        clickOn(sqrtButton);
        clickOn(fiveButton);
        clickOn(rightBracketButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("2.236068");
        clickOn(acButton);
    }
    @Test
    public void test_exponential(){
        clickOn(fourButton);
        clickOn(exponentButton);
        clickOn(threeButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("64.0");
        clickOn(acButton);
    }
    @Test
    public void test_pi(){
        clickOn(piButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("3.1415927");
        clickOn(acButton);
    }
    @Test
    public void test_e(){
        clickOn(eButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("2.7182818");
        clickOn(acButton);
    }
    @Test
    public void test_modulo(){
        clickOn(oneButton);
        clickOn(zeroButton);
        clickOn(moduloButton);
        clickOn(twoButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("0.0");
        clickOn(acButton);
    }
    @Test
    public void test_fac(){
        clickOn(facButton);
        clickOn(twoButton);
        clickOn(rightBracketButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("2.0");
        clickOn(acButton);
    }
    @Test
    public void test_sin(){
        clickOn(sinButton);
        clickOn(fourButton);
        clickOn(rightBracketButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("-0.7568025");
        clickOn(acButton);
    }
    @Test
    public void test_cos(){
        clickOn(cosButton);
        clickOn(sevenButton);
        clickOn(rightBracketButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("0.7539023");
        clickOn(acButton);
    }
    @Test
    public void test_tan(){
        clickOn(tanButton);
        clickOn(threeButton);
        clickOn(rightBracketButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("-0.1425465");
        clickOn(acButton);
    }
    @Test
    public void test_all_buttons(){
        clickOn(zeroButton);
        clickOn(oneButton);
        clickOn(twoButton);
        clickOn(threeButton);
        clickOn(fourButton);
        clickOn(plusButton);
        clickOn(sixButton);
        clickOn(sevenButton);
        clickOn(eightButton);
        clickOn(nineButton);
        clickOn(multiplyButton);
        clickOn(leftBracketButton);
        clickOn(twoButton);
        clickOn(exponentButton);
        clickOn(twoButton);
        clickOn(rightBracketButton);
        clickOn(multiplyButton);
        clickOn(sqrtButton);
        clickOn(oneButton);
        clickOn(sixButton);
        clickOn(rightBracketButton);
        clickOn(multiplyButton);
        clickOn(fiveButton);
        clickOn(subtractButton);
        clickOn(eightButton);
        clickOn(moduloButton);
        clickOn(twoButton);
        clickOn(plusButton);
        clickOn(sqrtButton);
        clickOn(fourButton);
        clickOn(rightBracketButton);
        clickOn(divideButton);
        clickOn(logButton);
        clickOn(threeButton);
        clickOn(rightBracketButton);
        clickOn(plusButton);
        clickOn(sinButton);
        clickOn(sixButton);
        clickOn(rightBracketButton);
        clickOn(plusButton);
        clickOn(cosButton);
        clickOn(nineButton);
        clickOn(rightBracketButton);
        clickOn(plusButton);
        clickOn(tanButton);
        clickOn(twoButton);
        clickOn(rightBracketButton);
        clickOn(plusButton);
        clickOn(lnButton);
        clickOn(fourButton);
        clickOn(rightBracketButton);
        clickOn(plusButton);
        clickOn(piButton);
        clickOn(multiplyButton);
        clickOn(eButton);
        clickOn(divideButton);
        clickOn(twoButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("544360.4723824");
        clickOn(subtractButton);
        clickOn(fiveButton);
        clickOn(twoButton);
        clickOn(twoButton);
        clickOn(threeButton);
        clickOn(sixButton);
        clickOn(zeroButton);
        clickOn(dotButton);
        clickOn(fourButton);
        clickOn(sevenButton);
        clickOn(twoButton);
        clickOn(threeButton);
        clickOn(eightButton);
        clickOn(twoButton);
        clickOn(fourButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("22000.0");
        clickOn(subtractButton);
        clickOn(twoButton);
        clickOn(zeroButton);
        clickOn(zeroButton);
        clickOn(zeroButton);
        clickOn(zeroButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("2000.0");
        clickOn(subtractButton);
        clickOn(oneButton);
        clickOn(nineButton);
        clickOn(nineButton);
        clickOn(nineButton);
        clickOn(equalsButton);
        Assertions.assertThat(resultLabel).hasText("1.0");
        clickOn(acButton);
    }
    @Test
    public void test_postfixLabel(){
        clickOn(sevenButton);
        clickOn(plusButton);
        clickOn(eightButton);
        clickOn(equalsButton);
        Assertions.assertThat(postfixLabel).hasText("7 8 + ");
        clickOn(acButton);
    }
    @Test
    public void test_infixLabel(){
        clickOn(fiveButton);
        clickOn(plusButton);
        clickOn(sevenButton);
        clickOn(equalsButton);
        Assertions.assertThat(infixLabel).hasText("5 + 7");
        clickOn(acButton);
    }


}