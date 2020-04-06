package com.lshaci.consumer.feign.hystrix;

import com.lshaci.consumer.feign.MovieFeign;
import com.lshaci.consumer.model.vo.MovieVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieHystrix implements MovieFeign {

    @Override
    public List<MovieVO> list() {
        return new ArrayList<>();
    }
}
