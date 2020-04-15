package com.aoher.controller;

import com.aoher.service.BirthdayService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.aoher.model.Year.*;
import static java.time.DayOfWeek.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {BirthdayInfoController.class, BirthdayService.class})
@WebMvcTest
class BirthdayInfoControllerTest {

    private static final String TEST_USER_ID = "user-id-123";

    private static String bd1;
    private static String bd2;
    private static String bd3;
    private static String bd4;
    private static String bd5;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp() {
        bd1 = LocalDate.of(1979, 7, 14).format(DateTimeFormatter.ISO_DATE);
        bd2 = LocalDate.of(2018, 1, 23).format(DateTimeFormatter.ISO_DATE);
        bd3 = LocalDate.of(1972, 3, 17).format(DateTimeFormatter.ISO_DATE);
        bd4 = LocalDate.of(1945, 12, 2).format(DateTimeFormatter.ISO_DATE);
        bd5 = LocalDate.of(2003, 8, 4).format(DateTimeFormatter.ISO_DATE);
    }

    @Test
    void testGetBirthdayDOW() throws Exception {
        testDOW(bd1, SATURDAY.toString());
        testDOW(bd2, TUESDAY.toString());
        testDOW(bd3, FRIDAY.toString());
        testDOW(bd4, SUNDAY.toString());
        testDOW(bd5, MONDAY.toString());
    }

    @Test
    public void testGetBirthdayChineseSign() throws Exception {
        testZodiak(bd1, SHEEP.toString());
        testZodiak(bd2, DOG.toString());
        testZodiak(bd3, RAT.toString());
        testZodiak(bd4, ROOSTER.toString());
        testZodiak(bd5, SHEEP.toString());
    }

    @Test
    public void testGetBirthdayTestStarSign() throws Exception {
        testStarSign(bd1, "Cancer");
        testStarSign(bd2, "Aquarius");
        testStarSign(bd3, "Pisces");
        testStarSign(bd4, "Sagittarius");
        testStarSign(bd5, "Leo");
    }

    private void testDOW(String birthday, String dow) throws Exception {
        MvcResult result = mockMvc.perform(post("/birthday/dayOfWeek")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .content(birthday)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertEquals(dow, resultDOW);
    }

    private void testZodiak(String birthday, String czs) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/chineseZodiac")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .content(birthday)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultCZ = result.getResponse().getContentAsString();
        assertNotNull(resultCZ);
        assertEquals(czs, resultCZ);
    }

    private void testStarSign(String birthday, String ss) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/birthday/starSign")
                .with(user(TEST_USER_ID))
                .with(csrf())
                .content(birthday)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultSS = result.getResponse().getContentAsString();
        assertNotNull(resultSS);
        assertEquals(ss, resultSS);
    }
}