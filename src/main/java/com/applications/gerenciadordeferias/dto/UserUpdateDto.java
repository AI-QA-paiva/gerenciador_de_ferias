package com.applications.gerenciadordeferias.dto;

import com.applications.gerenciadordeferias.enumeration.ProfileEnum;
import com.applications.gerenciadordeferias.enumeration.StatusUser;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
public class UserUpdateDto {

    @Length(min = 5, message = "Error, the number of characters informed must be greater than 5 characters")
    @Length(max = 60, message = "Error, the number of characters informed must be less than or equal to 60 characters")
    private String name;

    @NotNull(message = "Error, the field 'date of birth' was not informed")
    private LocalDate birthDate;

    @NotNull(message = "Error, the field 'hiring date' was not informed")
    private LocalDate hiringDate;

    @Max(value = 30, message = "the maximum amount of days balance is 30")
    private Integer daysBalance;


    @NotNull(message = "Error, the profile field was not informed")
    private ProfileEnum profileEnum;

    @NotNull(message = "Error, the status field was not informed")
    private StatusUser statusUser;


    //Construtores

    public UserUpdateDto() {
    }

    public UserUpdateDto(String name, LocalDate birthDate, LocalDate hiringDate, Integer daysBalance, ProfileEnum profileEnum, StatusUser statusUser) {
        this.name = name;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.daysBalance = daysBalance;
        this.profileEnum = profileEnum;
        this.statusUser = statusUser;
    }

    // Getter


    public @Length(min = 5, message = "Error, the number of characters informed must be greater than 5 characters") @Length(max = 60, message = "Error, the number of characters informed must be less than or equal to 60 characters") String getName() {
        return name;
    }

    public @NotNull(message = "Error, the field 'date of birth' was not informed") LocalDate getBirthDate() {
        return birthDate;
    }

    public @NotNull(message = "Error, the field 'hiring date' was not informed") LocalDate getHiringDate() {
        return hiringDate;
    }

    public @Max(value = 30, message = "the maximum amount of days balance is 30") Integer getDaysBalance() {
        return daysBalance;
    }

    public @NotNull(message = "Error, the profile field was not informed") ProfileEnum getProfileEnum() {
        return profileEnum;
    }

    public @NotNull(message = "Error, the status field was not informed") StatusUser getStatusUser() {
        return statusUser;
    }
}
