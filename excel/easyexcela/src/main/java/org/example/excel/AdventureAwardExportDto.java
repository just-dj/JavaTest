package org.example.excel;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 冒险奖励导出
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(25)
public class AdventureAwardExportDto extends ExcelRow implements Serializable {

    private static final long serialVersionUID = 8287356393439935534L;

    @ExcelProperty("场景名称")
    private String sceneName;

    @ExcelProperty("奖励包名称")
    private String awardName;

    @ExcelProperty("资源发放归属")
    private String resourceBelong;

    @ExcelProperty("奖励类型")
    private String awardType;

    @ExcelProperty("资源id")
    private String resourceId;

    @ExcelProperty("数量")
    private String number;

    @ExcelProperty("有效时长")
    private String validTime;

    @ExcelProperty("有效时长类型")
    private String validTimeType;

}