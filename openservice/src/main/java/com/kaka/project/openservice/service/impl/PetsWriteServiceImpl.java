package com.kaka.project.openservice.service.impl;

import com.kaka.common.utils.BaseResultUtil;
import com.kaka.project.client.dto.PetsDTO;
import com.kaka.project.client.service.*;
import com.kaka.project.biz.service.*;

import com.kaka.common.base.BaseResult;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaka.common.utils.LogUtil;
import com.kaka.common.constants.Constants;
import com.kaka.common.enums.ResultCode;

/**
* @Description： pets 对外服务类
* @PackageName: com.kaka.project.openservice.service.impl
* @ClassName: PetsWriteImplWriteServiceImpl
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/

@Service(version = "1.0.0", timeout = 10000)
public class PetsWriteServiceImpl implements PetsWriteService{
    private final LogUtil logger = new LogUtil(Constants.OPEN_SERVICE);

    @Autowired
    private PetsService petsService;

    /**
    * 新增一条记录
    * @param petsDTO
    * @return
    */
    @Override
    public BaseResult<Integer> insert(PetsDTO petsDTO) {
        logger.info("新增数据petsDTO:"+petsDTO);
        BaseResult<Integer> result = new BaseResult<>();
        try{
            int insert = petsService.insert(petsDTO);
            BaseResultUtil.wrapSuccess(result, insert);
        } catch (Throwable e){
            logger.error("新增记录失败，petsDTO:"+petsDTO,e);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
    * 修改一条记录
    * @param petsDTO
    * @return
    */
    @Override
    public BaseResult<Integer> update(PetsDTO petsDTO) {
        logger.info("传入参数如下：petsDTO:"+petsDTO);
        BaseResult<Integer> result = new BaseResult<>();
        try{
            int update = petsService.update(petsDTO);
            BaseResultUtil.wrapSuccess(result, update);
        } catch (Throwable e){
            logger.error("更新记录失败，petsDTO:"+petsDTO,e);
            result.setMessage(e.getMessage());
        }
        return result;
    }

                                                                                                                        
        /**
    * 启用记录
    * @param id
    * @return
    */
    @Override
    public BaseResult<Integer> enable(java.lang.Long id ,String userId) {
        logger.info("传入参数如下：id:" + id);
        BaseResult<Integer> result = new BaseResult<>();
        try{
            int update = petsService.enable(id , userId);
            BaseResultUtil.wrapSuccess(result, update);
        } catch (Throwable e){
            logger.error("启用记录失败，id:" + id, e);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
    * 禁用记录
    * @param id
    * @return
    */
    @Override
    public BaseResult<Integer> disable(java.lang.Long id, String userId) {
        logger.info("传入参数如下：id:"+id);
        BaseResult<Integer> result = new BaseResult<>();
        try{
            int update = petsService.disable(id, userId);
            BaseResultUtil.wrapSuccess(result, update);
        } catch (Throwable e){
            logger.error("禁用记录失败，id:" + id, e);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
    * 批量新增记录
    * @param list
    * @return
    */
    @Override
    public BaseResult<Boolean> insertBatch(List<PetsDTO> list) {
        logger.info("传入参数如下：list:"+list);
        BaseResult<Boolean> baseResult = new BaseResult<>();
        try{
            petsService.insertBatch(list);
            BaseResultUtil.wrapSuccess(baseResult, Boolean.TRUE, "批量新增成功！");
        } catch (Throwable e){
            logger.error("批量新增记录失败，list:"+list,e);
            baseResult.setMessage(e.getMessage());
        }
        return baseResult;
    }
    /**
    * 批量修改记录
    * @param list
    * @return
    */
    @Override
    public BaseResult<Boolean> updateBatch(List<PetsDTO> list) {
        logger.info("传入参数如下：list:"+list);
        BaseResult<Boolean> result = new BaseResult<>();
        try{
            petsService.updateBatch(list);
            BaseResultUtil.wrapSuccess(result, Boolean.TRUE, "批量更新成功！");
        } catch (Throwable e){
            logger.error("批量更新记录失败，car:"+list,e);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
    * 批量新增、修改记录
    * @param insertList
    * @param updateList
    * @return
    */
    @Override
    public BaseResult<Boolean> insertAndUpdateBatch(List<PetsDTO> insertList, List<PetsDTO> updateList) {
        logger.info("传入参数如下：insertList:" + insertList);
        logger.info("传入参数如下：updateList:" + updateList);
        BaseResult<Boolean> baseResult = new BaseResult<>();
        try{
            petsService.insertAndUpdate(insertList,updateList);
            BaseResultUtil.wrapSuccess(baseResult, Boolean.TRUE, "批量新增、修改记录成功！");
        } catch (Throwable e){
            logger.error("批量新增/更新记录失败，insertList:" + insertList + ",updateList" + updateList,e);
            baseResult.setMessage(e.getMessage());
        }
        return baseResult;
    }
}