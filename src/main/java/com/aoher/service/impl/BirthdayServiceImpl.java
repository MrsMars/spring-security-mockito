package com.aoher.service.impl;

import com.aoher.exceptions.BirthdayException;
import com.aoher.model.StarSign;
import com.aoher.model.Year;
import com.aoher.service.BirthdayService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.aoher.model.StarSign.*;
import static com.aoher.model.Year.*;

@Service
public class BirthdayServiceImpl implements BirthdayService {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate getValidBirthday(String birthdayString) {
        if (birthdayString == null) {
            throw new BirthdayException("Must include birthday");
        }

        try {
            return LocalDate.parse(birthdayString, formatter);
        } catch (Exception e) {
            throw new BirthdayException("Must include valid birthday in yyyy-MM-dd format");
        }
    }

    @Override
    public String getBirthDOW(LocalDate birthday) {
        return birthday.getDayOfWeek().toString();
    }

    @Override
    public Year getChineseZodiac(LocalDate birthday) {
        int year = birthday.getYear();

        switch (year % 12) {
            case 0:
                return MONKEY;
            case 1:
                return ROOSTER;
            case 2:
                return DOG;
            case 3:
                return PIG;
            case 4:
                return RAT;
            case 5:
                return OX;
            case 6:
                return TIGER;
            case 7:
                return RABBIT;
            case 8:
                return DRAGON;
            case 9:
                return SNAKE;
            case 10:
                return HORSE;
            case 11:
                return SHEEP;
            default: return null;
        }
    }

    @Override
    public StarSign getStarSign(LocalDate birthday) {
        int day = birthday.getDayOfMonth();
        int month = birthday.getMonthValue();

        if (month == 12 && day >= 22 || month == 1 && day < 20) return CAPRICORN;
        else if (month == 1 || month == 2 && day < 19) return AQUARIUS;
        else if (month == 2 || month == 3 && day < 21) return PISCES;
        else if (month == 3 || month == 4 && day < 20) return ARIES;
        else if (month == 4 || month == 5 && day < 21) return TAURUS;
        else if (month == 5 || month == 6 && day < 21) return GEMINI;
        else if (month == 6 || month == 7 && day < 23) return CANCER;
        else if (month == 7 || month == 8 && day < 23) return LEO;
        else if (month == 8 || month == 9 && day < 23) return VIRGO;
        else if (month == 9 || month == 10 && day < 23) return LIBRA;
        else if (month == 10 || month == 11 && day < 22) return SCORPIO;
        else return SAGITTARIUS;
    }
}
