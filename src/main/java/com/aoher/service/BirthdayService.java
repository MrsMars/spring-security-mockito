package com.aoher.service;

import com.aoher.model.StarSign;
import com.aoher.model.Year;

import java.time.LocalDate;

public interface BirthdayService {

    LocalDate getValidBirthday(String birthdayString) ;

    String getBirthDOW(LocalDate birthday);

    Year getChineseZodiac(LocalDate birthday);

    StarSign getStarSign(LocalDate birthday) ;
}
