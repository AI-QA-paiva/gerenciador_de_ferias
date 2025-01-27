package com.applications.gerenciadordeferias.service;

import com.applications.gerenciadordeferias.dto.VacationRequestDto;
import com.applications.gerenciadordeferias.dto.VacationResponseDto;
import com.applications.gerenciadordeferias.dto.VacationUpdateDto;
import com.applications.gerenciadordeferias.enumeration.StatusUser;
import com.applications.gerenciadordeferias.enumeration.StatusVacationRequest;
import com.applications.gerenciadordeferias.exception.ObjectNotFoundException;
import com.applications.gerenciadordeferias.exception.UnprocessableEntityException;
import com.applications.gerenciadordeferias.model.User;
import com.applications.gerenciadordeferias.model.VacationRequest;
import com.applications.gerenciadordeferias.repository.VacationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VacationRequestService {

    private final Integer rangeOfDay = 45;

    private final Integer itsSevenDays = 7;

    @Autowired
    private VacationRequestRepository vacationRequestRepository;

    @Autowired
    private UserService userService;

    private boolean validateIfTheDayOfTheWeekIsSaturday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY;
    }

    private boolean validateIfTheDayOfTheWeekIsSunday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    private LocalDate checkIfTheRoundTripIsNotABusinessDay(LocalDate date) {
        LocalDate newDate = date;
        if (validateIfTheDayOfTheWeekIsSaturday(newDate)) {
            newDate = newDate.plusDays(2);
            return newDate;
        } else if (validateIfTheDayOfTheWeekIsSunday(newDate)) {
            newDate = newDate.plusDays(1);
        }
        return newDate;
    }


    private boolean checkHolidayRequestBackground(LocalDate startAt) {
        LocalDate localDate = LocalDate.now().plusDays(rangeOfDay);
        return localDate.isBefore(startAt);
    }


    public VacationResponseDto registerVacationRequest(VacationRequestDto vacationRequestDto) throws Exception {
        try {
            User userFound = userService.checkUserActiveByEmail(vacationRequestDto.getEmail());
            LocalDate validateStartAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequestDto.getStartAt());
            VacationRequest vacationRequest = vacationRequestDto.convertToVacationRequest();
            vacationRequest.setUser(userFound);
            vacationRequest.setStartAt(validateStartAt);
            boolean validDate = checkHolidayRequestBackground(vacationRequest.getStartAt());
            if (validDate) {
                vacationRequest.setEndAt(vacationRequest.getStartAt().plusDays(vacationRequest.getVacationDays()));
                LocalDate validEndAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequest.getEndAt());
                vacationRequest.setEndAt(validEndAt);
                vacationRequest.setStatusVacationRequest(StatusVacationRequest.CREATED);
                userService.updateDaysBalance(vacationRequest.getUser(), vacationRequest.getVacationDays());
                VacationRequest vacation = vacationRequestRepository.save(vacationRequest);
                return VacationResponseDto.convertToVacationRequestResponse(vacation);
            } else {
                throw new UnprocessableEntityException("it was not possible to process this request, the request must be made at least " + rangeOfDay + " days in advance");
            }
        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    public List<VacationRequest> viewRegisteredVacations() {
        return vacationRequestRepository.findAllStatusVacationRequest();
    }

    public VacationRequest displayVacationRequestById(Long id) {

        Optional<VacationRequest> optionalVacationRequest = vacationRequestRepository.findById(id);
        if (optionalVacationRequest.isEmpty()) {
            throw new ObjectNotFoundException("no request with the id {id} was found in the system");
        }
        VacationRequest vacationRequestFound = optionalVacationRequest.get();
        if (vacationRequestFound.getUser().getStatusUser().equals(StatusUser.INACTIVE)) {
            throw new UnprocessableEntityException("Error, cannot access this user's data");
        }

        if (vacationRequestFound.getStatusVacationRequest().equals(StatusVacationRequest.CANCELED)) {
            throw new UnprocessableEntityException("Error, unable to access data for this vacation request");
        }


        return vacationRequestFound;
    }

    public VacationResponseDto changeRegisteredVacationRequest(Long id, VacationUpdateDto vacationUpdateDto) {
        VacationRequest requestFound = displayVacationRequestById(id);
        User userFound = userService.checkIfTheUserIsActive(requestFound.getUser().getId());
        userService.updateDaysBalancePlus(userFound, requestFound.getVacationDays());
        LocalDate startAt = vacationUpdateDto.getStartAt();
        LocalDate validateStartAt = checkIfTheRoundTripIsNotABusinessDay(startAt);
        requestFound.setStartAt(validateStartAt);
        if (checkHolidayRequestBackground(requestFound.getStartAt())) {
            requestFound.setEndAt(requestFound.getStartAt().plusDays(vacationUpdateDto.getVacationDays()));
            requestFound.setEndAt(checkIfTheRoundTripIsNotABusinessDay(requestFound.getEndAt()));
            requestFound.setVacationDays(vacationUpdateDto.getVacationDays());

            requestFound.setStatusVacationRequest(vacationUpdateDto.getStatusVacationRequest());
            userService.updateDaysBalance(userFound, requestFound.getVacationDays());
            VacationRequest vacation = vacationRequestRepository.save(requestFound);
            return VacationResponseDto.convertToVacationRequestResponse(vacation);
        } else {
            throw new UnprocessableEntityException("it was not possible to process this request, the request must be made at least " + rangeOfDay + " days in advance");
        }
    }

    private boolean checkItsSevenDaysBackground(VacationRequest vacationRequest) {
        LocalDate checkStartAt = vacationRequest.getStartAt();
        LocalDate localDate = LocalDate.now().plusDays(itsSevenDays);
        return localDate.isAfter(checkStartAt);
    }

    public void cancelRegisteredVacationRequest(Long id) {
        Optional<VacationRequest> optionalVacationRequest = vacationRequestRepository.findById(id);
        if (optionalVacationRequest.isEmpty()) {
            throw new ObjectNotFoundException("no request with the id {id} was found in the system");
        }

        VacationRequest requestFound = optionalVacationRequest.get();

        boolean validDateSevenDays = checkItsSevenDaysBackground(requestFound);
        if (validDateSevenDays) {
            throw new UnprocessableEntityException(
                    "It is not possible to process your vacation cancellation request, as you must have " + itsSevenDays + " days prior to the start date of the vacation.");
        }

        if (requestFound.getStatusVacationRequest().equals(StatusVacationRequest.CREATED)) {
            requestFound.setStatusVacationRequest(StatusVacationRequest.CANCELED);
            userService.updateDaysBalancePlus(requestFound.getUser(), requestFound.getVacationDays());

            vacationRequestRepository.save(requestFound);

        } else if (requestFound.getStatusVacationRequest().equals(StatusVacationRequest.CANCELED)) {
            throw new ObjectNotFoundException("Request is already canceled");
        }

    }

}
