package com.zzz.att.mapstruct;

import com.zzz.att.mapstruct.converter.PersonConverter;
import com.zzz.att.mapstruct.entity.Gender;
import com.zzz.att.mapstruct.entity.PersonDO;
import com.zzz.att.mapstruct.entity.PersonDTO;

import java.time.LocalDateTime;

public class MapStructDemoMain {
    public static void main(String[] args) {
        PersonDO personDO = new PersonDO(1, "do", 21, LocalDateTime.now(), Gender.MAN.getValue(), "position");
        PersonDTO personDTO = PersonConverter.INSTANCE.do2dto(personDO);
        System.out.println(personDTO);
        /* 控制台:
          PersonDTO(userName=do, age=21, birthday=2020-11-19 16:32:08, gender=MAN, fixed=fixed)
         */
    }
}
