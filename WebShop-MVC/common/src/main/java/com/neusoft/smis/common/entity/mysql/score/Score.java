package com.neusoft.smis.common.entity.mysql.score;

import lombok.Data;

/**
 * Created by Administrator on 2018/3/20.
 * 计分表的实体类
 */
@Data
public class Score {
    private int score_id;
    private int score_user_id;//得分人的ID=user表的ID
    private int score_by_user_id;//谁的打的分数的人的id=user表的ID
    private int score;//分数
    private String reason;//得分理由
    private String time;//得分的时间
    private String state;//状态:正常,撤销

}
