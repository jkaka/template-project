package com.kaka.project.client.service;

import com.kaka.project.client.dto.*;
import com.kaka.common.base.BaseResult;
import java.util.List;

/**
* @Description： pets 对外服务类
* @PackageName: com.kaka.project.client.service
* @ClassName: PetsWriteWriteService
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
public interface PetsWriteService {
                                                                                                                                                                                                                                    /**
    * 启用记录
    * @param id
    * @return
    */
    BaseResult<Integer> enable(java.lang.Long id ,String userId );
    /**
    * 禁用记录
    * @param id
    * @return
    */
    BaseResult<Integer> disable(java.lang.Long id ,String userId );
        /**
    * 新增记录
    *
    * @param DTO
    * @return
    */
    BaseResult<Integer> insert(PetsDTO DTO);
    /**
    * 更新记录
    *
    * @param DTO
    * @return
    */
    BaseResult<Integer> update(PetsDTO DTO);
    /**
    * 批量新增记录
    *
    * @param list
    * @return
    */
    BaseResult<Boolean> insertBatch(List<PetsDTO> list);
    /**
    * 批量更新记录
    *
    * @param list
    * @return
    */
    BaseResult<Boolean> updateBatch(List<PetsDTO> list);
    /**
    * 批量新增/修改记录
    *
    * @param insertList
    * @param updateList
    * @return
    */
    BaseResult<Boolean> insertAndUpdateBatch(List<PetsDTO> insertList,List<PetsDTO> updateList);



}
