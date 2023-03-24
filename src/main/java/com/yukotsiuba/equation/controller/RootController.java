package com.yukotsiuba.equation.controller;

import com.yukotsiuba.equation.dto.RootDto;
import com.yukotsiuba.equation.service.IRootService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roots")
public class RootController {

    private IRootService rootService;

    @PostMapping("/add")
    public ResponseEntity<RootDto> addRoot(){
        return null;
    }
}
