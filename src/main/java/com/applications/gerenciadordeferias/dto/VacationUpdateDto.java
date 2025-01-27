package com.applications.gerenciadordeferias.dto;

import com.applications.gerenciadordeferias.enumeration.StatusVacationRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Validated
public class VacationUpdateDto {

    @NotNull(message = "Error: the field 'vacation days' was not informed")
    @Min(value = 5, message = "error, it is not possible to request less than five days of vacation")
    @Max(value = 30, message = "error, it is not possible to request more than thirty days of vacation")
    private Integer vacationDays;

    @NotNull(message = "Error: the 'holiday start' field was not informed")
    private LocalDate startAt;

    @NotNull(message = "field 'statusRequestVacation' cannot be empty")
    private StatusVacationRequest statusVacationRequest;

    //Construtores

    public VacationUpdateDto() {
    }

    //Getter


    public @NotNull(message = "Error: the field 'vacation days' was not informed") @Min(value = 5, message = "error, it is not possible to request less than five days of vacation") @Max(value = 30, message = "error, it is not possible to request more than thirty days of vacation") Integer getVacationDays() {
        return vacationDays;
    }

    public @NotNull(message = "Error: the 'holiday start' field was not informed") LocalDate getStartAt() {
        return startAt;
    }

    public @NotNull(message = "field 'statusRequestVacation' cannot be empty") StatusVacationRequest getStatusVacationRequest() {
        return statusVacationRequest;
    }
}
