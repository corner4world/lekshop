package com.chengyu.core.mapper;

import com.chengyu.core.domain.form.GoodsImportForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.result.GoodsStockResult;
import com.chengyu.core.model.PmsGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomGoodsMapper {

    /**
     * 商品库存列表
     * @author LeGreen
     * @date   2022/9/18
     * @param  form 查询表单
     * @return List<GoodsStockResult>
     */
    List<GoodsStockResult> getGoodsStockList(@Param("form") GoodsSearchForm form);

    void insertGoodsList(List<GoodsImportForm> goodsList);

    List<PmsGoods> getGoodsList(@Param("form")GoodsSearchForm form);
}