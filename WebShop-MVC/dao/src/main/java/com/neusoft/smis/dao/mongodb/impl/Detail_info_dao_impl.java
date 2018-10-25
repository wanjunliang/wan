package com.neusoft.smis.dao.mongodb.impl;

import com.neusoft.smis.common.entity.mongodb.Detail_info;
import com.neusoft.smis.dao.mongodb.Detail_info_dao;
import com.neusoft.smis.dao.mongodb.support.AbstractBaseMongoTemplete;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WanJunliang on 2017/5/16.
 */
@Repository
public class Detail_info_dao_impl extends AbstractBaseMongoTemplete implements Detail_info_dao {
    private long countByCondition=0;
    private Query getQuery(Detail_info detail_info) {
        if (detail_info == null) {
            detail_info = new Detail_info();
        }
        Query query = new Query();
        if (detail_info.getId() != null&&detail_info.getId()!="") {
            Criteria criteria = Criteria.where("id").is(detail_info.getId());
            query.addCriteria(criteria);
        }
        if (detail_info.getHouse_name() != null&&detail_info.getHouse_name() != "") {
            Criteria criteria = Criteria.where("house_name").regex("^.*" + detail_info.getHouse_name()+".*$");
            query.addCriteria(criteria);
        }
        return query;
    }

    @Override
    public List<Detail_info>  findAll() {
        return mongoTemplate.findAll(Detail_info.class);
    }
    @Override
    public long count(Detail_info detail_info) {
        Query query = getQuery(detail_info);
        return mongoTemplate.count(query, Detail_info.class);
    }
    @Override
    public List<Detail_info> find(Detail_info detail_info, String skip, String limit, double house_price_min, double house_price_max, boolean house_price_flag
            , String data_house_start_time_min, String data_house_start_time_max, boolean data_house_start_time_flag
    ) {
        Query query = getQuery(detail_info);
        if(house_price_flag==true) {
            Criteria criteria = Criteria.where("house_price").gte(house_price_min).lte(house_price_max);
            query.addCriteria(criteria);
        }
        if(data_house_start_time_min!=null&&data_house_start_time_max!=null&&data_house_start_time_flag==true){
            Criteria criteria = Criteria.where("house_start_time").gte(data_house_start_time_min).lte(data_house_start_time_max);
            query.addCriteria(criteria);
        }
        //统计当前条件下的数量
        setCountByCondition(mongoTemplate.count(query,Detail_info.class));
        try{
            if(skip!=null&&skip!=""){
                query.skip(Integer.parseInt(skip));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(limit!=null&&limit!=""){
                query.limit(Integer.parseInt(limit));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        query.with(new Sort(Sort.Direction.DESC, "house_start_time"));
        return mongoTemplate.find(query, Detail_info.class);
    }

    @Override
    public List<Detail_info> find2(Detail_info detail_info, int skip, int limit
            , String bargained_time_min_String, String bargained_time_max_String
            , double bargained_price_min, double bargained_price_max
            , double house_area_min, double house_area_max,boolean flag_house_area
            , double house_unit_price_min, double house_unit_price_max,boolean flag_house_unit_price
            ,String[] checkbox_house_region
            ,boolean flag_bargined_time,boolean flag_bargained_price
            ,String save_time_min,String save_time_max,boolean flag_save_time
            ,String[] checkbox_current_status
    ) {
        Query query = getQuery(detail_info);
        if(checkbox_current_status==null){
            Criteria criteria1 = Criteria.where("current_status").is(null);
            query.addCriteria(criteria1);
        }
        else{
            Criteria criteria1 = Criteria.where("current_status").in(checkbox_current_status);
            query.addCriteria(criteria1);
        }

        //入库时间
        if(flag_save_time==true){
            Criteria criteria2 = Criteria.where("save_time").gte(save_time_min).lte(save_time_max);
            query.addCriteria(criteria2);
        }

        if(flag_bargined_time==true){
            Criteria criteria3 = Criteria.where("bargained_time").gte(bargained_time_min_String).lte(bargained_time_max_String);
            query.addCriteria(criteria3);
        }
        if(flag_bargained_price==true){
            Criteria criteria4 = Criteria.where("bargained_price").gte(bargained_price_min).lte(bargained_price_max);
            query.addCriteria(criteria4);
        }
        //        面积
        if(flag_house_area==true){
            Criteria criteria5 =Criteria.where("house_area").ne("").ne(null).gte(house_area_min).lte(house_area_max);
            query.addCriteria(criteria5);
        }
//        单价
        if(flag_house_unit_price==true){
            Criteria criteria6 =Criteria.where("house_unit_price").ne("").ne(null).gte(house_unit_price_min).lte(house_unit_price_max);
            query.addCriteria(criteria6);
        }
//        地区
        if(checkbox_house_region==null){
            Criteria criteria7 = Criteria.where("house_region").is(null);
            query.addCriteria(criteria7);
        }
        else {
            Criteria criteria7 = Criteria.where("house_region").in(checkbox_house_region);
            query.addCriteria(criteria7);
        }

        //统计当前条件下的数量
        setCountByCondition(mongoTemplate.count(query,Detail_info.class));
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, Detail_info.class);
    }

    @Override
    public void insert(Detail_info detail_info) {
        mongoTemplate.insert(detail_info,"detail_info");

    }
    public void setCountByCondition(long countByCondition){
        this.countByCondition=countByCondition;
    }
    @Override
    public long getCountByCondition() {
        return this.countByCondition;
    }

    @Override
    public List<Detail_info> selectDetail_infoByTransactionDate(String startDay, String endDay
                          ,String save_time_min,String save_time_max
    ) {
        Query query = new Query();
        System.out.println("dao:startDay="+startDay+"  "+endDay);
//        入库时间
        Criteria criteria0 = Criteria.where("save_time").gte(save_time_min).lte(save_time_max);
        query.addCriteria(criteria0);
//        成交价不能是空
        Criteria criteria1 = Criteria.where("bargained_price").ne("").ne(null);
        query.addCriteria(criteria1);
//        成交时间
        Criteria criteria2 = Criteria.where("bargained_time").ne("").ne(null).gte(startDay).lte(endDay+" 24:00:00");
        query.addCriteria(criteria2);
        return mongoTemplate.find(query, Detail_info.class);
    }

    @Override
    public List<Detail_info> selectDetail_infoByBargained_timeSave_timeHouse_region(
            String startDay,String endDay
            ,String save_time_min,String save_time_max
            ,String[] checkbox_house_region
    ) {
        Query query = new Query();
        //        入库时间
        Criteria criteria0 = Criteria.where("save_time").gte(save_time_min).lte(save_time_max);
        query.addCriteria(criteria0);
//        成交价不能是空
        Criteria criteria1 = Criteria.where("bargained_price").ne("").ne(null);
        query.addCriteria(criteria1);
//        成交时间
        Criteria criteria2 = Criteria.where("bargained_time").ne("").ne(null).gte(startDay).lte(endDay+" 24:00:00");
        query.addCriteria(criteria2);
//        地区
        Criteria criteria3 = Criteria.where("house_region").in(checkbox_house_region);
        query.addCriteria(criteria3);

        return mongoTemplate.find(query, Detail_info.class);
    }

    @Override
    public Detail_info selectDetail_infoById(Detail_info detail_info) {
        Query query=new Query();
        Criteria criteria = Criteria.where("id").is(detail_info.getId());
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, Detail_info.class);
    }
}
