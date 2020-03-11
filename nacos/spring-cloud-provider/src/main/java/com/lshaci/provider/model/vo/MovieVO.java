package com.lshaci.provider.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MovieVO {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
