package com.applications.gerenciadordeferias.dto;

import com.applications.gerenciadordeferias.enumeration.StatusVacationRequest;
import com.applications.gerenciadordeferias.model.User;
import com.applications.gerenciadordeferias.model.VacationRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@NoArgsConstructor
//@Getter
public class VacationResponseDto {

    private Long id;
    private Integer vacationDays;
    private LocalDate startAt;
    private LocalDate endAt;

    private StatusVacationRequest statusVacationRequest;

    private User user;

    // construtores

    public VacationResponseDto() {
    }

    public VacationResponseDto(Long id, Integer vacationDays, LocalDate startAt, LocalDate endAt, StatusVacationRequest statusVacationRequest, User user) {
        this.id = id;
        this.vacationDays = vacationDays;
        this.startAt = startAt;
        this.endAt = endAt;
        this.statusVacationRequest = statusVacationRequest;
        this.user = user;
    }

    // Getter

    public Long getId() {
        return id;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public StatusVacationRequest getStatusVacationRequest() {
        return statusVacationRequest;
    }

    public User getUser() {
        return user;
    }


    // metodos para converter DTO

    public VacationResponseDto(VacationRequest vacationRequest) {
        this.id = vacationRequest.getId();
        this.vacationDays = vacationRequest.getVacationDays();
        this.startAt = vacationRequest.getStartAt();
        this.endAt = vacationRequest.getEndAt();
        this.statusVacationRequest = vacationRequest.getStatusVacationRequest();
        this.user = vacationRequest.getUser();
    }

    public static VacationResponseDto convertToVacationRequestResponse(VacationRequest vacationRequest) {
        return new VacationResponseDto(vacationRequest.getId(), vacationRequest.getVacationDays(),
                vacationRequest.getStartAt(), vacationRequest.getEndAt(), vacationRequest.getStatusVacationRequest(), vacationRequest.getUser());
    }

}
