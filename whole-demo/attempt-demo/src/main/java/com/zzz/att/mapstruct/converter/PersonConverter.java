package com.zzz.att.mapstruct.converter;

import com.zzz.att.mapstruct.entity.Gender;
import com.zzz.att.mapstruct.entity.PersonDO;
import com.zzz.att.mapstruct.entity.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonConverter {

    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    @Mapping(source = "name", target = "userName") // 名称不对应
    @Mapping(target = "fixed", constant = "fixed") // 赋固定值
    @Mapping(target = "birthday",dateFormat = "yyyy-MM-dd HH:mm:ss") //时间转换
    PersonDTO do2dto(PersonDO personDO);

    /**
     * 自定义基本类型转成枚举
     * 注：一般情况下，对于以下情况可以做自动类型转换：
     * 1. 基本类型及其他们对应的包装类型。
     * 2. 基本类型的包装类型和String类型之间
     * 3. String类型和枚举类型之间
     * @param value
     * @return
     */
    default Gender integerToGender(int value){
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equals(value)){
                return gender;
            }
        }
        return null;
    }
}
