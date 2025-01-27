package com.applications.gerenciadordeferias.model;

import com.applications.gerenciadordeferias.enumeration.StatusVacationRequest;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "vacation_request")
public class VacationRequest implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer vacationDays;

    @Column(nullable = false)
    private LocalDate startAt;

    @Column(nullable = false)
    private LocalDate endAt;

    @Enumerated(EnumType.STRING)
    private StatusVacationRequest statusVacationRequest;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    //construtores

    public VacationRequest() {
    }

    public VacationRequest(Long id, Integer vacationDays, LocalDate startAt, LocalDate endAt, StatusVacationRequest statusVacationRequest, User user) {
        this.id = id;
        this.vacationDays = vacationDays;
        this.startAt = startAt;
        this.endAt = endAt;
        this.statusVacationRequest = statusVacationRequest;
        this.user = user;
    }


    //Getter and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public StatusVacationRequest getStatusVacationRequest() {
        return statusVacationRequest;
    }

    public void setStatusVacationRequest(StatusVacationRequest statusVacationRequest) {
        this.statusVacationRequest = statusVacationRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    //construtores para validar requisição do periodo de férias

    public VacationRequest(Integer vacationDays, LocalDate startAt) {
        this.vacationDays = vacationDays;
        this.startAt = startAt;
    }

    public VacationRequest(Integer vacationDays, LocalDate startAt, StatusVacationRequest statusVacationRequest) {
        this.vacationDays = vacationDays;
        this.startAt = startAt;
        this.statusVacationRequest = statusVacationRequest;
    }

}
