package com.applications.gerenciadordeferias.controller;

import com.applications.gerenciadordeferias.dto.VacationRequestDto;
import com.applications.gerenciadordeferias.dto.VacationResponseDto;
import com.applications.gerenciadordeferias.dto.VacationUpdateDto;
import com.applications.gerenciadordeferias.model.VacationRequest;
import com.applications.gerenciadordeferias.service.VacationRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VacationRequestController {

    @Autowired
    VacationRequestService vacationRequestService;


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(path = "/user/vacationsrequest")
    public ResponseEntity<VacationResponseDto> registerVacationRequest(@RequestBody @Valid VacationRequestDto vacationRequestDto) throws Exception {
        try {
            VacationResponseDto vacationResponseDto = vacationRequestService.registerVacationRequest(vacationRequestDto);
            return new ResponseEntity<>(vacationResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/vacationsrequest")
    public ResponseEntity<List<VacationRequest>> viewRegisteredVacations() {
        return ResponseEntity.ok(vacationRequestService.viewRegisteredVacations());
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/vacationsrequest/{id}")
    public ResponseEntity<VacationRequest> displayVacationRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(vacationRequestService.displayVacationRequestById(id));
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping(path = "/vacationsrequest/{id}")
    public ResponseEntity<VacationResponseDto> changeRegisteredVacationRequest(@PathVariable Long id, @RequestBody @Valid VacationUpdateDto vacationUpdateDto) {
        return ResponseEntity.ok(vacationRequestService.changeRegisteredVacationRequest(id, vacationUpdateDto));
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "/vacationsrequest/cancel/{id}")
    public void cancelRegisteredVacationRequest(@PathVariable Long id) {
        vacationRequestService.cancelRegisteredVacationRequest(id);
    }

}
