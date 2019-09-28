package com.cxy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Type {
    private Integer id;
    private String type;
    private Integer parentId;
    private Integer sort;
    private List<Type> chiledren;
}
