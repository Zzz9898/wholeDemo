package com.zzz.att.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDO {
    private Integer id;

    private String name;

    private int age;

    private LocalDateTime birthday;

    private Integer gender;

    private String fixed;

}
