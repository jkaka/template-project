package com.kaka.project.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaka.common.base.PageResult;
import com.kaka.common.constants.Constants;
import com.kaka.common.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import com.kaka.project.biz.dataobject.*;
import com.kaka.project.biz.service.PetsService;import com.kaka.project.client.dto.*;
import com.kaka.project.biz.mapper.*;
import org.apache.commons.collections.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

                                                                        
import com.kaka.common.enums.StatusEnum;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @Description： pets
* @PackageName: com.kaka.project.biz.service.impl
* @ClassName: PetsServiceImpl
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
@Service
public class PetsServiceImpl extends ServiceImpl<PetsMapper, PetsDO> implements PetsService {
    private  final LogUtil logger = new LogUtil(this.getClass());

                                                                                                                                
    /**
     * 根据主键查询信息
     * @param id
     * @return
     */
    @Override
    public PetsDTO selectOne(java.lang.Long id) {
        if(Objects.isNull(id)){
            logger.error("传入主键为空！");
            return null;
        }
        PetsDO petsDO = baseMapper.selectById(id);
        if(Objects.isNull(petsDO)){
            logger.error("查询结果为空！id="+id);
            return null;
        }
        PetsDTO petsDTO = new PetsDTO();
        petsDTO.setId(petsDO.getId());
        petsDTO.setName(petsDO.getName());
        petsDTO.setOwnerId(petsDO.getOwnerId());
        petsDTO.setCreateUser(petsDO.getCreateUser());
        petsDTO.setLastUser(petsDO.getLastUser());
        petsDTO.setGmtCreate(petsDO.getGmtCreate());
        petsDTO.setGmtModified(petsDO.getGmtModified());
        petsDTO.setStatus(petsDO.getStatus());
        
        return petsDTO;
    }

    /**
     * 根据传入参数查询列表 支持分页查询
     * @param param
     * @return
     */
    @Override
    public PageResult<List<PetsDTO>> selectPage(Map<String, Object> param) {
        if(Objects.isNull(param)){
            param = new HashMap<>(8);
        }
        long currentPage = param.get(Constants.PAGE_INDEX) == null ? 0 : new Integer(param.get(Constants.PAGE_INDEX).toString());
        long pageSize = param.get(Constants.PAGE_SIZE) == null ? 500 : new Integer(param.get(Constants.PAGE_SIZE).toString());
        pageSize = pageSize > 5000 ? 5000 : pageSize;

        Page page = new Page(currentPage, pageSize);
        PetsDO petsParam = JSONObject.parseObject(JSONObject.toJSONString(param), PetsDO.class);
        QueryWrapper<PetsDO> queryWrapper = new QueryWrapper<>(petsParam);
        IPage iPage = baseMapper.selectPage(page, queryWrapper);

        List<PetsDO> dataList = iPage.getRecords();
        List<PetsDTO> valueList = new ArrayList<>();
        if(CollectionUtils.isEmpty(dataList)){
            logger.error("查询结果为空！param="+param);
            return null;
        }
        for(int i=0;i<dataList.size();i++){
            PetsDO petsDO = dataList.get(i);
            PetsDTO petsDTO = new PetsDTO();
            petsDTO.setId(petsDO.getId());
            petsDTO.setName(petsDO.getName());
            petsDTO.setOwnerId(petsDO.getOwnerId());
            petsDTO.setCreateUser(petsDO.getCreateUser());
            petsDTO.setLastUser(petsDO.getLastUser());
            petsDTO.setGmtCreate(petsDO.getGmtCreate());
            petsDTO.setGmtModified(petsDO.getGmtModified());
            petsDTO.setStatus(petsDO.getStatus());
            
            valueList.add(petsDTO);
        }
        return new PageResult<>(new Long(iPage.getCurrent()).intValue(),
                new Long(iPage.getSize()).intValue(),
                new Long(iPage.getTotal()).intValue(),
                valueList);
    }

    /**
     * 根据条件查询记录总条数
     * @param param
     * @return
     */
    @Override
    public Integer countList(Map<String, Object> param) {
        PetsDO petsParam = JSONObject.parseObject(JSONObject.toJSONString(param), PetsDO.class);
        QueryWrapper<PetsDO> queryWrapper = new QueryWrapper<>(petsParam);
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * 新增一条记录
     * @param petsDTO
     * @return
     */
    @Override
    public int insert(PetsDTO petsDTO) {
        if(Objects.isNull(petsDTO)){
             logger.error("查询对象为空！");
             return -1;
        }
        PetsDO petsDO = new PetsDO();
                    petsDO.setId(petsDTO.getId());
                                petsDO.setName(petsDTO.getName());
                                petsDO.setOwnerId(petsDTO.getOwnerId());
                                                petsDO.setCreateUser(StringUtils.isEmpty(petsDO.getCreateUser()) ? "system" : petsDO.getCreateUser());
                                                petsDO.setLastUser(StringUtils.isEmpty(petsDO.getLastUser()) ? "system" : petsDO.getLastUser());
                                                petsDO.setGmtCreate(System.currentTimeMillis());
                                                petsDO.setGmtModified(System.currentTimeMillis());
                                petsDO.setStatus(petsDTO.getStatus());
                    
        int insert= baseMapper.insert(petsDO);
        if(insert > 0){
            petsDTO.setId(petsDO.getId());
        }
        return insert;
    }

    /**
     * 修改一条记录
     * @param petsDTO
     * @return
     */
    @Override
    public int update(PetsDTO petsDTO) {
        if(Objects.isNull(petsDTO)){
             logger.error("查询对象为空！");
             return -1;
        }
        PetsDO petsDO = new PetsDO();
                    petsDO.setId(petsDTO.getId());
                                petsDO.setName(petsDTO.getName());
                                petsDO.setOwnerId(petsDTO.getOwnerId());
                                                                                petsDO.setLastUser(StringUtils.isEmpty(petsDO.getLastUser()) ? "system" : petsDO.getLastUser());
                                                                                petsDO.setGmtModified(System.currentTimeMillis());
                                petsDO.setStatus(petsDTO.getStatus());
                    
        return baseMapper.updateById(petsDO);
    }

    /**
     * 删除一条记录
     * @param id
     * @return
     */
    @Override
    public int delete(java.lang.Long id) {
        return baseMapper.deleteById(id);
    }

   /**
    * 禁用数据
    * @param id
            * @param userId 操作人
        * @return
    */
    @Override
    public int disable(java.lang.Long id,String userId ){
        PetsDO petsDO = new PetsDO();
        petsDO.setId(id);
        petsDO.setLastUser(userId);
        petsDO.setStatus(StatusEnum.STATUS_N.getCode());
        return baseMapper.updateStatus(petsDO);
    }

    /**
     * 启用数据
     * @param id
         * @param userId 操作人
          * @return
     */
    @Override
    public int enable(java.lang.Long id,String userId ){
        PetsDO petsDO = new PetsDO();
        petsDO.setId(id);
        petsDO.setLastUser(userId);
        petsDO.setStatus(StatusEnum.STATUS_Y.getCode());
        return baseMapper.updateStatus(petsDO);
    }

    /**
    * 批量新增记录
    *
    * @param list
    * @return
    */
    @Override
    public void insertBatch(List<PetsDTO> list)throws Exception{
        if (CollectionUtils.isEmpty(list)) {
            logger.error("传对象为空,本次批量新增操作无效!");
            return;
        }
        List<PetsDO> petsList = new ArrayList<>();
        for (PetsDTO petsDTO : list) {
            PetsDO petsDO = new PetsDO();
                            petsDO.setId(petsDTO.getId());
                                            petsDO.setName(petsDTO.getName());
                                            petsDO.setOwnerId(petsDTO.getOwnerId());
                                                                petsDO.setCreateUser(StringUtils.isEmpty(petsDO.getCreateUser()) ? "system" : petsDO.getCreateUser());
                                                                petsDO.setLastUser(StringUtils.isEmpty(petsDO.getLastUser()) ? "system" : petsDO.getLastUser());
                                                                petsDO.setGmtCreate(System.currentTimeMillis());
                                                                petsDO.setGmtModified(System.currentTimeMillis());
                                            petsDO.setStatus(petsDTO.getStatus());
                            
            petsList.add(petsDO);
        }
        try{
            this.saveBatch(petsList, petsList.size());
        }catch(Exception e){
            logger.error("批量插入失败；message="+e.getMessage());
            throw new Exception("批量插入失败");
        }
    }

    /**
    * 批量更新记录
    *
    * @param list
    * @return
    */
    @Override
    public void updateBatch(List<PetsDTO> list)throws Exception{
        if (CollectionUtils.isEmpty(list)) {
            logger.error("传对象为空,本次批量更新操作无效!");
            return;
        }
        List<PetsDO> petsList = new ArrayList<>();
        for (PetsDTO petsDTO : list) {
            PetsDO petsDO = new PetsDO();
                            petsDO.setId(petsDTO.getId());
                                            petsDO.setName(petsDTO.getName());
                                            petsDO.setOwnerId(petsDTO.getOwnerId());
                                                                                                            petsDO.setLastUser(StringUtils.isEmpty(petsDO.getLastUser()) ? "system" : petsDO.getLastUser());
                                                                                                            petsDO.setGmtModified(System.currentTimeMillis());
                                            petsDO.setStatus(petsDTO.getStatus());
                            
            petsList.add(petsDO);
        }
        try{
            this.updateBatchById(petsList, petsList.size());
        }catch(Exception e){
            logger.error("批量更新失败；message="+e.getMessage());
            throw new Exception("批量更新失败");
        }
    }


    /**
    * 批量新增/更新记录
    *
    * @param insertList
    * @param updateList
    * @return
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 3600, rollbackFor = Exception.class)
    public void insertAndUpdate(List<PetsDTO> insertList, List<PetsDTO> updateList)throws Exception{
        if(CollectionUtils.isNotEmpty(updateList)) {
            this.updateBatch(updateList);
        }
        if(CollectionUtils.isNotEmpty(insertList)) {
            this.insertBatch(insertList);
        }
    }
}
