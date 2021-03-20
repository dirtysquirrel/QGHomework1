package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import java.io.File;
import java.util.UUID;
import java.util.Random;

public class TextBoxTests {

    private static final int PHONE_NUMBER_LENGTH = 10;

    public static String getRandomPhone() {
        String s = "1234567890";
        StringBuffer phoneNumber = new StringBuffer();

        for (int i = 0; i < PHONE_NUMBER_LENGTH; i++) {
            phoneNumber.append(s.charAt(new Random().nextInt(s.length())));
        }
        return phoneNumber.toString();
    }

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }


    @Test
    void checkRegistrationForm() {

        String name = UUID.randomUUID().toString();
        String lastname = UUID.randomUUID().toString();
        String mail = UUID.randomUUID().toString() + "@" + UUID.randomUUID().toString() + ".com";
        String phoneNumber = getRandomPhone();
        String address = UUID.randomUUID().toString();


        open("https://demoqa.com/automation-practice-form");

        $(byId("firstName")).setValue(name);

        $(byId("lastName")).setValue(lastname);

        $(byId("userEmail")).setValue(mail);

        $("label[for='gender-radio-2']").click();

        $(byId("userNumber")).setValue(phoneNumber);

        // set date
        $(byId("dateOfBirthInput")).click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1904");
        $(".react-datepicker__day--026").click();

        $(byId("subjectsInput")).setValue("Ma");
        $(byId("react-select-2-option-0")).click();
        String subject = $(".subjects-auto-complete__multi-value__label").getText();

        $("label[for='hobbies-checkbox-3']").click();

        File file = new File(".gitignore");
        $("#uploadPicture").uploadFile(file);

        $(byId("currentAddress")).setValue(address);

        $(byText("Select State")).click();
        $(byText("Haryana")).click();

        $(byText("Select City")).click();
        $(byText("Karnal")).click();

        $(byId("submit")).click();

        //check result
        $(".modal-body").shouldHave(text(name),
                text(lastname),
                text(mail),
                text(phoneNumber),
                text("Female"),
                text("26 April,1904"),
                text(subject),
                text("Music"),
                text(".gitignore"),
                text(address),
                text("Haryana Karnal")
        );

    }

}





