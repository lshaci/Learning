package com.lshaci.consumer.feign;

import com.lshaci.consumer.model.vo.MovieVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("provider")
public interface MovieFeign {

    @GetMapping("/movie/list")
    List<MovieVO> list();
}
