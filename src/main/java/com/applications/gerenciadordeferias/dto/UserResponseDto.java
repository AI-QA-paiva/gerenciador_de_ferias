package com.applications.gerenciadordeferias.dto;

import com.applications.gerenciadordeferias.enumeration.ProfileEnum;
import com.applications.gerenciadordeferias.enumeration.StatusUser;
import com.applications.gerenciadordeferias.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private LocalDate hiringDate;
    private Integer daysBalance;
    private ProfileEnum profileEnum;
    private StatusUser statusUser;


    //Construtor

    public UserResponseDto(Long id, String name, String email, LocalDate birthDate, LocalDate hiringDate, Integer daysBalance, ProfileEnum profileEnum, StatusUser statusUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.daysBalance = daysBalance;
        this.profileEnum = profileEnum;
        this.statusUser = statusUser;
    }

    //Getter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public Integer getDaysBalance() {
        return daysBalance;
    }

    public ProfileEnum getProfileEnum() {
        return profileEnum;
    }

    public StatusUser getStatusUser() {
        return statusUser;
    }

    //construtores para converter DTO e model
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
        this.hiringDate = user.getHiringDate();
        this.daysBalance = user.getDaysBalance();
        this.profileEnum = user.getProfileEnum();
        this.statusUser = user.getStatusUser();
    }

    public static UserResponseDto convertToUser(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getHiringDate(),
                user.getDaysBalance(),
                user.getProfileEnum(),
                user.getStatusUser());
    }

}
