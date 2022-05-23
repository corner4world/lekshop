package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsSearchForm {

	private Integer shopId;

	private Integer catePid;

	private Integer cateTid;

	private Integer cateId;
	
	private Integer shopCateId;
	
	private String title;

	private Integer type;

	@ApiModelProperty(value = "销量:sell_num desc,新上架:add_time desc,价格高:price desc,价格低:price asc")
	private String sort;

	private Integer status;

}