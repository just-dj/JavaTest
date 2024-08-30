package org.example;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

import org.example.excel.AdventureImportDto;
import org.example.excel.ExcelParseResult;
import org.example.excel.ParseBatchHandleListener;

import com.alibaba.excel.EasyExcel;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

public class Main {
    public static void main(String[] args) {
        try {
            File targetFile = new File("/Users/justdj_1/Desktop/语音房宝箱数值.xlsx");

            List<AdventureImportDto> adventureImportDtos = Lists.newArrayList();
            readBatch(targetFile, AdventureImportDto.class, batch -> {
                adventureImportDtos.addAll(MoreObjects.firstNonNull(batch, Lists.newArrayList()));
            });



            System.out.println("Hello world!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static <T> ExcelParseResult readBatch(File file, Class<T> clazz, Consumer<List<T>> function) {
        ExcelParseResult parseResult = new ExcelParseResult();
        ParseBatchHandleListener listener = new ParseBatchHandleListener(parseResult, function);
        EasyExcel.read(file, clazz, listener).sheet(25).doRead();
        return parseResult;
    }
}