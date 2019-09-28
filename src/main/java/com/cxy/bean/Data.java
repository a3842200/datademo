package com.cxy.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Data {
    private Integer id;
    private String key;
    private String value;
    private Integer sourceId;
    private Integer parentId;
    private Integer sort;
    private List<Data> children;
}
