package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.InsertTree;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.InsertTreeByPId;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ListTreeByPId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 设备分类
 * @author Yazhe
 * Created at 14:57 2018/8/7
 */
@Data
@ApiModel("设备分类")
public class Category {

    @NotNull(message = "id不能为空", groups = {InsertTree.class})
    @Pattern(regexp = "\\d+", message = "category的id只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    @Size(min = 19, max = 19, message = "category的id只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String id;

    /**
     * 父级地点id
     */
    @Pattern(regexp = "\\d+", message = "category的parentId只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    @Size(min = 19, max = 19, message = "category的parentId只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String parentId;

    /**
     * 地名
     */
    @NotNull(message = "名称不能为空", groups = {InsertTree.class, InsertTreeByPId.class})
    @Pattern(regexp = "[\\u4e00-\\u9fa5\\w]+", message = "category的name只能是汉字和英文", groups = CommonValidatedGroup.LegalityGroup.class)
    private String name;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 路径
     */
    @Pattern(regexp = "^/[\\d/]+", message = "category的path必须从“/”开始，且只能包含数字、和“/” ",groups = CommonValidatedGroup.LegalityGroup.class)
    private String path;
}
