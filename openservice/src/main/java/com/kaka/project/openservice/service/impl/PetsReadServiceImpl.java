package com.kaka.project.openservice.service.impl;

import com.kaka.common.base.BaseResult;
import com.kaka.common.base.PageResult;
import com.kaka.project.client.dto.*;
import com.kaka.project.client.service.*;
import com.kaka.project.biz.service.*;
import com.kaka.common.utils.LogUtil;
import com.kaka.common.utils.BaseResultUtil;
import com.kaka.common.enums.*;
import com.kaka.common.constants.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* @Description： pets 对外服务类
* @PackageName: com.kaka.project.openservice.service.impl
* @ClassName: PetsReadReadServiceImpl
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
@Service(version = "1.0.0", timeout=10000)
public class PetsReadServiceImpl implements PetsReadService {
    private  final LogUtil logger = new LogUtil(Constants.OPEN_SERVICE);

    @Autowired
    private PetsService petsService;

    /**
    * 根据主键查询信息
    *
    * @param id
    * @return
    */
    @Override
    public BaseResult<PetsDTO> selectOne(java.lang.Long id){
        BaseResult<PetsDTO> petsResult = new BaseResult<>();
        if(Objects.isNull(id)){
            logger.error("传入主键为空！");
            petsResult.setMessage("传入主键为空！");
            return petsResult;
        }
        logger.info("根据主键查询信息,id:"+id);
        try {
            PetsDTO pets= petsService.selectOne(id);
            BaseResultUtil.wrapSuccess(petsResult, pets);
        } catch (Exception e){
            logger.info("根据主键查询信息失败,id:"+id,e);
            petsResult.setMessage(e.getMessage());
        }
        return petsResult;
     }

    /**
    * 根据传入参数查询列表 支持分页查询
    *
    * @param param
    * key:   pageIndex 当前页码 默认为0
    * key:   pageSize  页大小  默认为20
                        * key:    id id
                                * key:    name name
                                * key:    ownerId owner_id
                                * key:    createUser 创建人
                    
                    
                    
                                * key:    status 数据有效状态
                * @return
    */
    @Override
    public PageResult<List<PetsDTO>> selectList(Map<String, Object> param){
        logger.info("根据传入参数查询列表 支持分页查询,param:"+param);
        PageResult<List<PetsDTO>> petsResult = new PageResult<>();
        try {
            petsResult = petsService.selectPage(param);
            BaseResultUtil.wrapSuccess(petsResult);
        } catch (Exception e){
            logger.info("根据传入参数查询列表 支持分页查询失败,param:"+param);
            petsResult.setMessage(e.getMessage());
        }
        return petsResult;
    }

    /**
    * 根据传入参数查询记录总数
    *
    * @param param
    * key:   pageIndex 当前页码 默认为0
    * key:   pageSize  页大小  默认为20
                        * key:    id id
                                * key:    name name
                                * key:    ownerId owner_id
                                * key:    createUser 创建人
                    
                    
                    
                                * key:    status 数据有效状态
                * @return
    */
    @Override
    public BaseResult<Integer> countList(Map<String, Object> param){
        logger.info("根据传入参数查询记录总数,param:" + param);
        BaseResult<Integer> petsResult = new BaseResult<>();
        try {
            Integer count = petsService.countList(param);
            BaseResultUtil.wrapSuccess(petsResult, count);
        } catch (Exception e){
            logger.info("根据传入参数查询记录总数失败,param:"+param);
            petsResult.setMessage(e.getMessage());
        }
        return petsResult;
    }
}
