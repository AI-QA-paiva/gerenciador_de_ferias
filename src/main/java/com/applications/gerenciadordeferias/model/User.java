package com.applications.gerenciadordeferias.model;

import com.applications.gerenciadordeferias.enumeration.ProfileEnum;
import com.applications.gerenciadordeferias.enumeration.StatusUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="users")
public class User implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(unique = true, nullable = false, length = 45)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    private LocalDate hiringDate;

    private Integer daysBalance;

    @Enumerated(EnumType.STRING)
    private ProfileEnum profileEnum;

    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VacationRequest> vacationRequests;


    public User() {
    }

    public User(Long id, String name, String email, LocalDate birthDate,
                LocalDate hiringDate, Integer daysBalance, ProfileEnum profileEnum,
                StatusUser statusUser, List<VacationRequest> vacationRequests) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.daysBalance = daysBalance;
        this.profileEnum = profileEnum;
        this.statusUser = statusUser;
        this.vacationRequests = vacationRequests;
    }

    public User(String name, String email, LocalDate birthDate, LocalDate hiringDate,
                ProfileEnum profileEnum) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.profileEnum = profileEnum;
    }

    public User(String name, LocalDate birthDate, LocalDate hiringDate, ProfileEnum profileEnum,
                StatusUser statusUser, Integer daysBalance) {
        this.name = name;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.daysBalance = daysBalance;
        this.profileEnum = profileEnum;
        this.statusUser = statusUser;
    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Integer getDaysBalance() {
        return daysBalance;
    }

    public void setDaysBalance(Integer daysBalance) {
        this.daysBalance = daysBalance;
    }

    public ProfileEnum getProfileEnum() {
        return profileEnum;
    }

    public void setProfileEnum(ProfileEnum profileEnum) {
        this.profileEnum = profileEnum;
    }

    public StatusUser getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    public List<VacationRequest> getVacationRequests() {
        return vacationRequests;
    }

    public void setVacationRequests(List<VacationRequest> vacationRequests) {
        this.vacationRequests = vacationRequests;
    }
}
