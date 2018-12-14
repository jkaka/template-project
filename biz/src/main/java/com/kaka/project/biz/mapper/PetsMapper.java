package com.kaka.project.biz.mapper;

import com.kaka.project.biz.dataobject.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @Description： pets
* @PackageName: com.kaka.project.biz.mapper
* @ClassName: PetsMapper
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
public interface PetsMapper extends BaseMapper<PetsDO> {

    int updateStatus(PetsDO object);
}
