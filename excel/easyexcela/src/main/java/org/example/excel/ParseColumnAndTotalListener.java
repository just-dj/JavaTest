package org.example.excel;

import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Sets;

public class ParseColumnAndTotalListener<T extends ExcelRow> extends AnalysisEventListener<T> {

    private final ExcelParseResult excelParseResult;

    private Integer total;

    /**
     * 是否需要统计总行数, 为false时只返回行头解析信息
     */
    private final boolean isNeedTotalLine;

    /**
     * @param excelParseResult
     * @param isNeedTotalLine  是否需要统计总行数，只需要行头时传false，对性能有一定提升
     */
    public ParseColumnAndTotalListener(ExcelParseResult excelParseResult, boolean isNeedTotalLine) {
        this.excelParseResult = excelParseResult;
        this.isNeedTotalLine = isNeedTotalLine;
        total = 0;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        total++;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        excelParseResult.setColumnNames(Sets.newHashSet(headMap.values()));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        excelParseResult.setTotal(total);
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        if (!isNeedTotalLine) {
            return false;
        }

        return super.hasNext(context);
    }
}
