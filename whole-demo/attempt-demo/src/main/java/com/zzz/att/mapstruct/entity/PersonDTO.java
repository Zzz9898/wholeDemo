package com.zzz.att.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String userName;

    private Integer age;

    private String birthday;

    private Gender gender;

    private String fixed;

}
