package org.example.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public class ParseBatchHandleListener<T extends ExcelRow> extends AnalysisEventListener<T> {

    private static final Integer BATCH_SIZE = 200;

    private final List<T> objectBatch;

    private final ExcelParseResult excelParseResult;

    private final Consumer<List<T>> consumer;

    public ParseBatchHandleListener(ExcelParseResult excelParseResult, Consumer<List<T>> consumer) {
        Preconditions.checkNotNull(consumer);
        this.excelParseResult = excelParseResult;
        this.consumer = consumer;
        objectBatch = new ArrayList<>(BATCH_SIZE);
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        t.setLineNo(analysisContext.readRowHolder().getRowIndex());
        objectBatch.add(t);
        if (BATCH_SIZE <= objectBatch.size()) {
            handleAndClear();
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        excelParseResult.setColumnNames(Sets.newHashSet(headMap.values()));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        handleAndClear();
        excelParseResult.setTotal(context.readRowHolder().getRowIndex());
    }

    private void handleAndClear() {
        if (!CollectionUtils.isEmpty(objectBatch)) {
            consumer.accept(objectBatch);
            objectBatch.clear();
        }
    }
}
