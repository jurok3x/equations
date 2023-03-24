package com.yukotsiuba.equation.controller;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.service.IEquationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/equations")
public class EquationController {

    private IEquationService equationService;

    @PostMapping
    public ResponseEntity<EquationDto> save(@RequestBody EquationDto equationDto) {
        return new ResponseEntity<>(equationService.save(equationDto), HttpStatus.CREATED);
    }

    @GetMapping("/root/count/{count}")
    public ResponseEntity<List<EquationDto>> findByRootsCount(@PathVariable Integer count) {
        return new ResponseEntity<>(equationService.findByRootsCount(count), HttpStatus.OK);
    }
}
