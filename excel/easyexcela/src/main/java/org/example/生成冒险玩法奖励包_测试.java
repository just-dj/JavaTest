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

public class 生成冒险玩法奖励包_测试 {

    /**
     * 分组名称
     */
    public static final String SCENE_NAME = "冒险玩法关卡奖励0906";

    /**
     * 标签id
     */
    public static final String RESOURCE_BELONG = "920003";

    public static final Map<String, Pair<String, String>> columnToRewardType = new HashMap<>();

    static {
        columnToRewardType.put("通关奖励-宝箱", Pair.of("心遇宝箱养成宝箱(8065)", null));
        columnToRewardType.put("奖励-宝箱升级石", Pair.of("心遇宝箱升级石(8066)", null));
        columnToRewardType.put("通关奖励-通关次数", Pair.of("冒险闯关次数(8071)", null));
        columnToRewardType.put("章节奖励-宝箱", Pair.of("心遇宝箱养成宝箱(8065)", null));
        columnToRewardType.put("章节奖励-道兵抽奖券", Pair.of("数值资产代币(9007)", "3695283"));
        columnToRewardType.put("章节奖励-法宝抽奖券", Pair.of("数值资产代币(9007)", "26007"));
    }

    private static Integer chapterNum = 0;

    public static void main(String[] args) {
        try {
            File targetFile = new File("/Users/justdj_1/Desktop/语音房宝箱数值-最新09-03.xlsx");

            List<AdventureImportDto> adventureImportDtos = Lists.newArrayList();
            readBatch(targetFile, AdventureImportDto.class, batch -> {
                adventureImportDtos.addAll(MoreObjects.firstNonNull(batch, Lists.newArrayList()));
            });

            List<AdventureAwardExportDto> exportDtos = new ArrayList<>();
            AdventureAwardExportDto exportDto;
            for (AdventureImportDto adventureImportDto : adventureImportDtos) {
                // 1.关卡奖励包
                //通关奖励-宝箱
                String rewardBoxName = "关卡" + adventureImportDto.getLevel();
                exportDto = buildReward(
                        rewardBoxName, "通关奖励-宝箱", adventureImportDto.getRewardBox(), adventureImportDto);
                if (exportDto != null) {
                    exportDtos.add(exportDto);
                }

                //奖励-宝箱升级石
                exportDto = buildReward(rewardBoxName,
                        "奖励-宝箱升级石", adventureImportDto.getRewardSoldierTicket(), adventureImportDto);
                if (exportDto != null) {
                    exportDtos.add(exportDto);
                }

                //通关奖励-通关次数
                exportDto = buildReward(rewardBoxName,
                        "通关奖励-通关次数", adventureImportDto.getRewardRound(), adventureImportDto);
                if (exportDto != null) {
                    exportDtos.add(exportDto);
                }

                // 2.章节奖励包
                if (adventureImportDto.haveChapterReward()) {
                    chapterNum++;

                    //章节奖励-宝箱
                    String chapterName = "章节" + chapterNum;
                    exportDto = buildReward(chapterName,
                            "章节奖励-宝箱", adventureImportDto.getChapterRewardBox(), adventureImportDto);
                    if (exportDto != null) {
                        exportDtos.add(exportDto);
                    }

                    //章节奖励-道兵抽奖券
                    exportDto = buildReward(chapterName,
                            "章节奖励-道兵抽奖券", adventureImportDto.getChapterRewardSoldierTicket(), adventureImportDto);
                    if (exportDto != null) {
                        exportDtos.add(exportDto);
                    }

                    //章节奖励-法宝抽奖券
                    exportDto = buildReward(chapterName,
                            "章节奖励-法宝抽奖券", adventureImportDto.getChapterRewardMagicTicket(), adventureImportDto);
                    if (exportDto != null) {
                        exportDtos.add(exportDto);
                    }
                }
            }

            String fileName = "/Users/justdj_1/Desktop/冒险玩法奖励包批量导入.xlsx";
            EasyExcel.write(fileName, AdventureAwardExportDto.class).sheet(0).doWrite(exportDtos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Nullable
    public static AdventureAwardExportDto buildReward(
            String rewardBoxName,
            String awardType, String originValue, AdventureImportDto adventureImportDto) {

        if (StringUtils.isBlank(originValue)
                || !NumberUtils.isDigits(originValue.trim())) {
            System.out.println(awardType + " 异常记录 " + JSONUtil.toJsonStr(adventureImportDto));
            throw new IllegalArgumentException("");
        }

        AdventureAwardExportDto exportDto = new AdventureAwardExportDto();
        exportDto.setLineNo(adventureImportDto.getLineNo());
        exportDto.setSceneName(SCENE_NAME);
        exportDto.setAwardName(rewardBoxName);
        exportDto.setResourceBelong(RESOURCE_BELONG);
        if (Long.parseLong(originValue.trim()) > 0) {
            Preconditions.checkState(
                    columnToRewardType.containsKey(awardType), "奖励类型不存在{}", awardType);
            Pair<String, String> rewardTypeReadIdPair = columnToRewardType.get(awardType);
            exportDto.setAwardType(rewardTypeReadIdPair.getLeft());
            exportDto.setResourceId(rewardTypeReadIdPair.getRight());
            exportDto.setNumber(originValue);
            return exportDto;
        }

        return null;
    }

    public static <T> ExcelParseResult readBatch(File file, Class<T> clazz, Consumer<List<T>> function) {
        ExcelParseResult parseResult = new ExcelParseResult();
        ParseBatchHandleListener listener = new ParseBatchHandleListener(parseResult, function);
        EasyExcel.read(file, clazz, listener).sheet("PVE").doRead();
        return parseResult;
    }

}