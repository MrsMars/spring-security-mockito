package com.aoher.service.impl;

import com.aoher.model.StarSign;
import com.aoher.model.Year;
import com.aoher.service.BirthdayService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirthdayServiceImplTest {

    private BirthdayService birthdayService = new BirthdayServiceImpl();

    @Test
    void testGetBirthdayDOW() {
        String dow = birthdayService.getBirthDOW(LocalDate.of(1979, 7, 14));
        assertEquals("SATURDAY", dow);
        dow = birthdayService.getBirthDOW(LocalDate.of(2018, 1, 23));
        assertEquals("TUESDAY", dow);
        dow = birthdayService.getBirthDOW(LocalDate.of(1972, 3, 17));
        assertEquals("FRIDAY", dow);
        dow = birthdayService.getBirthDOW(LocalDate.of(1945, 12, 2));
        assertEquals("SUNDAY", dow);
        dow = birthdayService.getBirthDOW(LocalDate.of(2003, 8, 4));
        assertEquals("MONDAY", dow);
    }

    @Test
    void testGetBirthdayChineseSign() {
        Year dow = birthdayService.getChineseZodiac(LocalDate.of(1979, 7, 14));
        assertEquals(Year.SHEEP, dow);

        dow = birthdayService.getChineseZodiac(LocalDate.of(2018, 1, 23));
        assertEquals(Year.DOG, dow);

        dow = birthdayService.getChineseZodiac(LocalDate.of(1972, 3, 17));
        assertEquals(Year.RAT, dow);

        dow = birthdayService.getChineseZodiac(LocalDate.of(1945, 12, 2));
        assertEquals(Year.ROOSTER, dow);

        dow = birthdayService.getChineseZodiac(LocalDate.of(2003, 8, 4));
        assertEquals(Year.SHEEP, dow);
    }

    @Test
    void testGetBirthdayStarSign() {
        StarSign dow = birthdayService.getStarSign(LocalDate.of(1979, 7, 14));
        assertEquals(StarSign.CANCER, dow);

        dow = birthdayService.getStarSign(LocalDate.of(2018, 1, 23));
        assertEquals(StarSign.AQUARIUS, dow);

        dow = birthdayService.getStarSign(LocalDate.of(1972, 3, 17));
        assertEquals(StarSign.PISCES, dow);

        dow = birthdayService.getStarSign(LocalDate.of(1945, 12, 2));
        assertEquals(StarSign.SAGITTARIUS, dow);

        dow = birthdayService.getStarSign(LocalDate.of(2003, 8, 4));
        assertEquals(StarSign.LEO, dow);
    }
}