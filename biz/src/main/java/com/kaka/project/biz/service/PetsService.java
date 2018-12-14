package com.kaka.project.biz.service;
import com.kaka.common.base.PageResult;
import com.kaka.project.client.dto.*;
import java.util.Map;
import java.util.List;

/**
* @Description： pets
* @PackageName: com.kaka.project.biz.service
* @ClassName: PetsService
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
public interface PetsService {
    /**
     * 根据主键查询信息
     * @param id
     * @return
     */
    PetsDTO selectOne(java.lang.Long id);
    /**
     * 根据传入参数查询列表 支持分页查询
     * @param param
     * @return
     */
PageResult<List<PetsDTO>> selectPage(Map<String,Object> param);
    /**
     * 根据条件查询记录总条数
     * @param param
     * @return
     */
    Integer countList(Map<String,Object> param);
    /**
     * 新增一条记录
     * @param petsDTO
     * @return
     */
    int insert(PetsDTO petsDTO);
    /**
     * 更新一条记录
     * @param petsDTO
     * @return
     */
    int update(PetsDTO petsDTO);
    /**
     * 删除一条记录
     * @param id
     * @return
     */
    int delete(java.lang.Long id);

                                                                                                                                                                                                                                                                                                         
     /**
     * 启用数据
     * @param id
              * @param userId 操作人
                  * @return
     */
    int enable(java.lang.Long id ,String userId );

      /**
      * 禁用数据
      * @param id
               * @param userId 操作人
               * @return
      */
      int disable(java.lang.Long id ,String userId );
      
    /**
    * 批量新增记录
    *
    * @param list
    * @return
    */
    void insertBatch(List<PetsDTO> list)throws Exception;

    /**
    * 批量新增/更新记录
    *
    * @param insertList
    * @param updateList
    * @return
    */
    void insertAndUpdate(List<PetsDTO> insertList, List<PetsDTO> updateList)throws Exception;

    /**
    * 批量更新记录
    *
    * @param list
    * @return
    */
    void updateBatch(List<PetsDTO> list)throws Exception;
}
