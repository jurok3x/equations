package com.yukotsiuba.equation.controller;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.service.IEquationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    
    @GetMapping
    public ResponseEntity<List<EquationDto>> findByRoots(@RequestParam String[] roots) {
        List<String> rootList = Arrays.asList(roots);
        return new ResponseEntity<>(equationService.findByRootValues(rootList), HttpStatus.OK);
    }

    @GetMapping("/roots/count/{count}")
    public ResponseEntity<List<EquationDto>> findByRootsCount(@PathVariable Integer count) {
        return new ResponseEntity<>(equationService.findByRootsCount(count), HttpStatus.OK);
    }
    
    @PutMapping("/{eqId}/add/roots")
    public ResponseEntity<EquationDto> addRoots(@PathVariable Integer eqId, @RequestBody List<String> values) {
        return new ResponseEntity<>(equationService.addRoots(eqId, values), HttpStatus.ACCEPTED);
    }
}
