package com.lshaci.consumer.controller;

import com.lshaci.consumer.vo.MovieVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @GetMapping("/list")
    public List<MovieVO> list() {
        MovieVO vo = new MovieVO()
                .setName("黑暗迷宫")
                .setStartTime(LocalDateTime.now())
                .setEndTime(LocalDateTime.now().plusHours(2));
        return Arrays.asList(vo);
    }
}
