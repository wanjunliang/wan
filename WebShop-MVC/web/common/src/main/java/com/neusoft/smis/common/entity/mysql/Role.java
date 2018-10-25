package com.neusoft.smis.common.entity.mysql;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by WanJunliang on 2017/5/19.
 */
@Data
public class Role {

    private int role_id;                   //id
    private String role_name;        //角色名字
    private List<Privs> privsList;
}
