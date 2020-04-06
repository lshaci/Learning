package com.lshaci.consumer.web.controller;

import com.lshaci.consumer.feign.MovieFeign;
import com.lshaci.consumer.model.vo.MovieVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieFeign movieFeign;

    @GetMapping("/list")
    public List<MovieVO> list(@RequestHeader(value = "AUTHENTICATION", required = false) String authentication) {
        System.err.println("AUTHENTICATION: " + authentication);
        List<MovieVO> data = movieFeign.list();
        return data;
    }
}
