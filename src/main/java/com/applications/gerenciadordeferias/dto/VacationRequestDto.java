package com.applications.gerenciadordeferias.dto;

import com.applications.gerenciadordeferias.model.VacationRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Validated

public class VacationRequestDto {

    @NotNull(message = "Error: the field 'vacation days' was not informed")
    @Min(value = 5, message = "error, it is not possible to request less than five days of vacation")
    @Max(value = 30, message = "error, it is not possible to request more than thirty days of vacation")
    private Integer vacationDays;

    @NotNull(message = "Error: the field 'email' was not informed")
    private String email;

    @NotNull(message = "Error: the 'holiday start' field was not informed")
    private LocalDate startAt;

    //construtores

    public VacationRequestDto() {
    }

    public VacationRequestDto(Integer vacationDays, String email, LocalDate startAt) {
        this.vacationDays = vacationDays;
        this.email = email;
        this.startAt = startAt;
    }

    // Getter and Setter


    public @NotNull(message = "Error: the field 'vacation days' was not informed") @Min(value = 5, message = "error, it is not possible to request less than five days of vacation") @Max(value = 30, message = "error, it is not possible to request more than thirty days of vacation") Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(@NotNull(message = "Error: the field 'vacation days' was not informed") @Min(value = 5, message = "error, it is not possible to request less than five days of vacation") @Max(value = 30, message = "error, it is not possible to request more than thirty days of vacation") Integer vacationDays) {
        this.vacationDays = vacationDays;
    }

    public @NotNull(message = "Error: the field 'email' was not informed") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Error: the field 'email' was not informed") String email) {
        this.email = email;
    }

    public @NotNull(message = "Error: the 'holiday start' field was not informed") LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(@NotNull(message = "Error: the 'holiday start' field was not informed") LocalDate startAt) {
        this.startAt = startAt;
    }

    //    metrodo para converter Dto
    public VacationRequest convertToVacationRequest() {
        return new VacationRequest(vacationDays, startAt);
    }

}
