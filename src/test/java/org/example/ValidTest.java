package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class ValidTest {

    @Test
    public void loginValidationPos() {
            String login = "galikgal";
            Assertions.assertTrue(Valid.checkLogin(login));
    }
    @Test
    public void loginValidationNeg() {
            String login = "gal";
            Assertions.assertFalse(Valid.checkLogin(login));
    }


    @Test
    public void passwordValidationPos() {
        String password = "1234567";
        Assertions.assertTrue(Valid.checkPassword(password));
    }

    @Test
    public void passwordValidationNeg() {
        String password = "123";
        Assertions.assertFalse(Valid.checkPassword(password));
    }

    @Disabled
    public void passwordValidationNeg22() {
        String password = "123";
        Assertions.assertTrue(true);
    }
}