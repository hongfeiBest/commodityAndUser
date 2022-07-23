package cn.hongfei.commodity.entity;


import cn.hongfei.common.automaticTime.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CommodityType extends BaseEntity {
    /**
     * 商品类型主键
     */
    @TableId(type = IdType.UUID)
    private String commodityTypeId;

    /**
     *商品类型名称
     */
    private String commodityTypeName;

    /**
     *商品删除标志位
     */
    private Integer delFlag;
}
