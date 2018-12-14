package com.kaka.project.biz.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
* @Description： pets
* @PackageName: com.kaka.project.biz.dataobject
* @ClassName: PetsDO
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
@Data
@TableName("pets")
public class PetsDO {
        /**
    * id
    */
    @TableId(value = "id", type = IdType.AUTO)
        private java.lang.Long id;

        /**
    * name
    */
        private java.lang.String name;

        /**
    * owner_id
    */
        private java.lang.Long ownerId;

        /**
    * 创建人
    */
        private java.lang.String createUser;

        /**
    * 最后修改人
    */
        private java.lang.String lastUser;

        /**
    * 创建时间
    */
        private java.lang.Long gmtCreate;

        /**
    * 修改时间
    */
        private java.lang.Long gmtModified;

        /**
    * 数据有效状态
    */
    @TableLogic
        private java.lang.String status;

    
}
