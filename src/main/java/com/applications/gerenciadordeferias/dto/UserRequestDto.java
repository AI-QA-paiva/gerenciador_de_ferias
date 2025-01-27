package com.applications.gerenciadordeferias.dto;

import com.applications.gerenciadordeferias.enumeration.ProfileEnum;
import com.applications.gerenciadordeferias.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

//@NoArgsConstructor
//@Getter
public class UserRequestDto {

    @Length(min = 5, message = "Error, the number of characters informed must be greater than 5 characters")
    @Length(max = 60, message = "Error, the number of characters informed must be less than or equal to 60 characters")
    private String name;

    @Email(message = "Error, invalid email")
    @NotBlank(message = "Error, 'email' field not informed")
    private String email;

    @NotNull(message = "Error, the field 'date of birth' was not informed")
    private LocalDate birthDate;

    @NotNull(message = "Error, the field 'hiring date' was not informed")
    private LocalDate hiringDate;
    @NotNull(message = "Error, the profile field was not informed")
    private ProfileEnum profileEnum;

    //constructs

    public UserRequestDto() {
    }

    //getter and setter

    public @Length(min = 5, message = "Error, the number of characters informed must be greater than 5 characters") @Length(max = 60, message = "Error, the number of characters informed must be less than or equal to 60 characters") String getName() {
        return name;
    }

    public @Email(message = "Error, invalid email") @NotBlank(message = "Error, 'email' field not informed") String getEmail() {
        return email;
    }

    public @NotNull(message = "Error, the field 'date of birth' was not informed") LocalDate getBirthDate() {
        return birthDate;
    }

    public @NotNull(message = "Error, the field 'hiring date' was not informed") LocalDate getHiringDate() {
        return hiringDate;
    }

    public @NotNull(message = "Error, the profile field was not informed") ProfileEnum getProfileEnum() {
        return profileEnum;
    }

    //para converter objeto para DTO

    public User convertToUserRequestDto() {
        return new User(name, email, birthDate, hiringDate, profileEnum);
    }

}
