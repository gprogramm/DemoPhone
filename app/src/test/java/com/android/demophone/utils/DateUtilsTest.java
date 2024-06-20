package com.android.demophone.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkAndFormatDateString() {
    }

    @Test
    @DisplayName("Check the current time is in working hours or not")
    void checkCurrentTimeInBetweenDates() {
        boolean flag = DateUtils.checkCurrentTimeInBetweenDates("00:10","09:15");
        assertTrue(flag);
    }
}