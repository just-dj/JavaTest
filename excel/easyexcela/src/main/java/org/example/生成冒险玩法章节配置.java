package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.example.excel.AdventureAwardExportDto;
import org.example.excel.AdventureImportDto;
import org.example.excel.ExcelParseResult;
import org.example.excel.ParseBatchHandleListener;

import com.alibaba.excel.EasyExcel;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import cn.hutool.json.JSONUtil;
import lombok.Data;

public class 生成冒险玩法章节配置 {

    /**
     * 分组名称
     */
    public static final String REWARD_BOX_ID = "1212215,1212226,1212237,1212248,1212259,1212270,1212281,1212292,1212303,1212314,1212325,1212336,1212347,1212358,1212369,1212380,1212391,1212402,1212413,1212424,1212435,1212446,1212457,1212468,1212479,1212490,1212501,1212512,1212523,1212534,1212545,1212556,1212567,1212578,1212589,1212600,1212611,1212622,1212633,1212644,1212655,1212666,1212677,1212688,1212699,1212710,1212721,1212732,1212743,1212754,1212765,1212776,1212787,1212798,1212809,1212820,1212831,1212842,1212853,1212864,1212875,1212886,1212897,1212908,1212919,1212930,1212941,1212952,1212963,1212974,1212985,1212996,1213007,1213018,1213029,1213040,1213051,1213062,1213073,1213084,1213095,1213106,1213117,1213128,1213139,1213150,1213161,1213172,1213183,1213194,4010005,4010016,4010027,4010038,4010049,4010060,4010071,4010082,4010093,4010104";


    private static Integer chapterNum = 0;

    public static void main(String[] args) {
        try {
            String[] rewardBoxId = REWARD_BOX_ID.split(",");
            List<ChapterConfig> chapterConfigs = new ArrayList<>();
            for (int i = 0; i < rewardBoxId.length; i++) {
                ChapterConfig chapterConfig = new ChapterConfig();
                chapterConfig.setSeasonIndex(0);
                chapterConfig.setChapterIndex(i + 1);
                chapterConfig.setChapterName("章节" + (i + 1));
                chapterConfig.setRewardBoxId(Integer.valueOf(rewardBoxId[i]));
                chapterConfigs.add(chapterConfig);
            }
            System.out.println(JSONUtil.toJsonStr(chapterConfigs));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    {
//        "seasonIndex": 0,
//            "chapterIndex": 1,
//            "chapterName": "章节1",
//            "rewardBoxId": 3267188
//    }

    @Data
    public static class ChapterConfig {
        private Integer seasonIndex;
        private Integer chapterIndex;
        private String chapterName;
        private Integer rewardBoxId;

    }

}