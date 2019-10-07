package com.cxy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TypeDetail {
    private Integer id;
    private String type;
    private Integer parentId;
    private Integer sort;
}
