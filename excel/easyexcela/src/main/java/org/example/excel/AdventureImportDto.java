package org.example.excel;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 冒险数值
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdventureImportDto extends ExcelRow implements Serializable {

    private static final long serialVersionUID = 8287356393439935534L;

    @ExcelProperty("关卡")
    private String round;

    @ExcelProperty("怪物等级")
    private String level;

    @ExcelProperty("通关奖励-宝箱")
    private String rewardBox;

    @ExcelProperty("奖励-宝箱升级石")
    private String rewardSoldierTicket;

    @ExcelProperty("通关奖励-通关次数")
    private String rewardRound;

    @ExcelProperty("章节奖励-宝箱")
    private String chapterRewardBox;

    @ExcelProperty("章节奖励-道兵抽奖券")
    private String chapterRewardSoldierTicket;

    @ExcelProperty("章节奖励-法宝抽奖券")
    private String chapterRewardMagicTicket;

    public boolean haveChapterReward() {
        return StringUtils.isNotBlank(chapterRewardBox) && Long.parseLong(chapterRewardBox) > 0
                || StringUtils.isNotBlank(chapterRewardSoldierTicket) && Long.parseLong(chapterRewardSoldierTicket) > 0
                || StringUtils.isNotBlank(chapterRewardMagicTicket) && Long.parseLong(chapterRewardMagicTicket) > 0;
    }

}