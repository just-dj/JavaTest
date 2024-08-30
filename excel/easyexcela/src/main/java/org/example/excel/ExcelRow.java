package org.example.excel;

import com.alibaba.excel.annotation.ExcelIgnore;

import lombok.Data;

@Data
public class ExcelRow {
    @ExcelIgnore
    private int lineNo;
}
