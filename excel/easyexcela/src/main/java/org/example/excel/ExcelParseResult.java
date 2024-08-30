package org.example.excel;


import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

import lombok.Data;

@Data
public class ExcelParseResult {

    private Integer total;

    private Set<String> columnNames;

    private String error;

    public ExcelParseResult() {
        total = 0;
        columnNames = Sets.newHashSet();
    }

    public boolean isError(){
        return !StringUtils.isEmpty(error);
    }
}

