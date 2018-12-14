package com.kaka.project.client.service;

import com.kaka.common.base.BaseResult;
import com.kaka.common.base.PageResult;
import com.kaka.project.client.dto.*;
import java.util.List;
import java.util.Map;

/**
* @Description： pets 对外服务类
* @PackageName: com.kaka.project.client.service
* @ClassName: PetsReadReadService
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
public interface PetsReadService {
    /**
     * 根据主键查询信息
     * @param id
     * @return
     */
    BaseResult<PetsDTO>  selectOne(java.lang.Long id);
    /**
     * 根据传入参数查询列表 支持分页查询
     * @param param
    * key: pageIndex 当前页码 默认为0
    * key: pageSize  页大小  默认为20
                        * key:  id  id
                                * key:  name  name
                                * key:  ownerId  owner_id
                                * key:  createUser  创建人
                    
                    
                    
                                * key:  status  数据有效状态
                 * @return
     */
    PageResult<List<PetsDTO>> selectList(Map<String,Object> param);
    /**
    * 根据传入参数查询列表 支持分页查询
    * @param param
    * key: pageIndex 当前页码 默认为0
    * key: pageSize  页大小  默认为20
                        * key:  id  id
                                * key:  name  name
                                * key:  ownerId  owner_id
                                * key:  createUser  创建人
                    
                    
                    
                                * key:  status  数据有效状态
                * @return
    */
    BaseResult<Integer> countList(Map<String,Object> param);




}
