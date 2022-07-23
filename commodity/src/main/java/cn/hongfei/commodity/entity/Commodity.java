package cn.hongfei.commodity.entity;

import cn.hongfei.common.automaticTime.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Commodity extends BaseEntity {
    /**
     *主键ID
     */
    @TableId(type = IdType.UUID)
    private String commodityId;

    /**
     *商品类型ID
     */
    private String commodityTypeId;

    /**
     *商品名称
     */
    private String name;

    /**
     *商品规格
     */
    private String standard;

    /**
     *商品价格
     */
    private Integer money;

    /**
     *商品库存
     */
    private Integer stock;

}
