package com.kaka.project.boot.controller;

import com.kaka.common.base.BaseResult;
import com.kaka.project.client.dto.*;
import com.kaka.project.config.properties.AppProperties;
import com.kaka.common.utils.exception.KakaException;
import com.kaka.project.biz.service.PetsService;
import com.kaka.project.biz.service.token.KakaTokenService;
import com.kaka.common.utils.*;
import com.kaka.common.enums.*;
import com.kaka.common.base.*;
import com.kaka.common.constants.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import org.springframework.util.CollectionUtils;


/**
* @apiDefine pets pets
* @Description： pets
* @PackageName: com.kaka.project.boot.controller
* @ClassName: PetsController
* @project： tsp-project
* @author: jsk
* @dep: 99技术部
* @company: 99技术有限公司
* @address: 国际大路,中原谷
* @date： Fri Dec 14 12:42:46 CST 2018
*/
@RestController
public class PetsController {
    private  final LogUtil logger = new LogUtil(this.getClass());

    @Autowired
    private PetsService petsService;
    @Autowired
    private KakaTokenService kakaTokenService;

    @Autowired
    private AppProperties appConfig;

    /**
    * @apiIgnore Not finished Method
    * @api {get} /fota/pets/{id} 根据id查找信息
    * @apiVersion 1.0.0
    * @apiName /fota/pets/{id}
    * @apiGroup pets
    * @apiHeader {String} AUTHORIZATION 认证用的的令牌
        * @apiSuccess (200) {java.lang.Long}   id    id
        * @apiSuccess (200) {java.lang.String}  [name]  name
        * @apiSuccess (200) {java.lang.Long}  [ownerId]  owner_id
        * @apiSuccess (200) {java.lang.String}   createUser    创建人
        * @apiSuccess (200) {java.lang.String}   lastUser    最后修改人
        * @apiSuccess (200) {java.lang.Long}   gmtCreate    创建时间
        * @apiSuccess (200) {java.lang.Long}   gmtModified    修改时间
        * @apiSuccess (200) {java.lang.String}   status    数据有效状态
        * @apiError (400) {String} RequestParamsError 请求中的参数不符合要求
    * @apiError (500) {String} ServiceInternalError 服务器内部错误
    */
    @RequestMapping(value="/fota/pets/{id}",method = RequestMethod.GET)
    public ResponseEntity<?>  selectOne(HttpServletRequest request,@PathVariable java.lang.Long id) throws KakaException {
        if(Objects.isNull(id)){
            logger.info("传入主键为空!");
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST, "传入主键为空!");
        }
        PetsDTO objectDTO= petsService.selectOne(id);
        return new ResponseEntity(BaseResultUtil.handlerSuccess(objectDTO), HttpStatus.OK);
    }

    /**
    * @apiIgnore Not finished Method
    * @api {post} /fota/petss 根据传入条件查询list列表
    * @apiVersion 1.0.0
    * @apiName /fota/petss
    * @apiGroup pets
    *
    * @apiHeader {String} AUTHORIZATION 认证用的的令牌
    * @apiParam (Body) {java.lang.Long}   [pageIndex] 当前页码 默认为0
    * @apiParam (Body) {java.lang.Long}   [pageSize]  页大小  默认为20
              * @apiParam (Body) {java.lang.Long}  [id]  id
                   * @apiParam (Body) {java.lang.String}  [name]  name
                   * @apiParam (Body) {java.lang.Long}  [ownerId]  owner_id
                   * @apiParam (Body) {java.lang.String}  [createUser]  创建人
              
              
              
                   * @apiParam (Body) {java.lang.String}  [status]  数据有效状态
             * @apiSuccess (200) {java.lang.Long}   pageSize  页大小
    * @apiSuccess (200) {java.lang.Long}   pageIndex  当前页码
    * @apiSuccess (200) {java.lang.Long}   totalPageCount  总页数
    * @apiSuccess (200) {java.lang.Long}   record  记录总数
    * @apiSuccess (200) {Object[]}   data 列表数据
        * @apiSuccess (200) {java.lang.Long}   data.id    id
        * @apiSuccess (200) {java.lang.String}  [data.name]  name
        * @apiSuccess (200) {java.lang.Long}  [data.ownerId]  owner_id
        * @apiSuccess (200) {java.lang.String}   data.createUser    创建人
        * @apiSuccess (200) {java.lang.String}   data.lastUser    最后修改人
        * @apiSuccess (200) {java.lang.Long}   data.gmtCreate    创建时间
        * @apiSuccess (200) {java.lang.Long}   data.gmtModified    修改时间
        * @apiSuccess (200) {java.lang.String}   data.status    数据有效状态
        * @apiError (400) {String} RequestParamsError 请求中的参数不符合要求
    * @apiError (500) {String} ServiceInternalError 服务器内部错误
    */
    @RequestMapping(value="/fota/petss",method = RequestMethod.POST)
    public ResponseEntity<?> selectList(HttpServletRequest request,@RequestBody Map<String,Object> data) throws KakaException {
        PageResult<List<PetsDTO>> result = petsService.selectPage(data);
        BaseResultUtil.wrapSuccess(result);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
    * @apiIgnore Not finished Method
    * @api {post} /fota/pets/insert 新增数据
    * @apiVersion 1.0.0
    * @apiName /fota/pets/insert
    * @apiGroup pets
    *
    * @apiHeader {String} AUTHORIZATION 认证用的的令牌
            
                                * @apiParam (Body) {java.lang.String}  [name]  name
                                * @apiParam (Body) {java.lang.Long}  [ownerId]  owner_id
                                * @apiParam (Body) {java.lang.String}   createUser    创建人
                    
                    
                    
                                * @apiParam (Body) {java.lang.String}   status    数据有效状态
                * @apiSuccess (200) {String} data 插入记录条数
    * @apiError (400) {String} RequestParamsError 请求中的参数不符合要求
    * @apiError (500) {String} ServiceInternalError 服务器内部错误
    */
    @PostMapping(value="/fota/pets/insert")
    public ResponseEntity<?>  insert(HttpServletRequest request,@RequestBody PetsDTO petsDTO) throws KakaException {
        if(Objects.isNull(petsDTO)){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"传入对象为空!");
        }
        if(null != petsDTO.getId()){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"新增数据主键必需为空!"+petsDTO.toString());
        }
                                                                                                                    petsDTO.setLastUser(this.getLoginUserName(request));
                                                                                        int update= petsService.insert(petsDTO);
        if(update<=0){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"更新失败!+petsDTO"+petsDTO);
        }
        return  new ResponseEntity(BaseResultUtil.handlerSuccess(update), HttpStatus.OK);
    }



    /**
    * @apiIgnore Not finished Method
    * @api {post} /fota/pets/update 更新指定id信息
    * @apiVersion 1.0.0
    * @apiName /fota/pets/update
    * @apiGroup pets
    *
    * @apiHeader {String} AUTHORIZATION 认证用的的令牌
            * @apiParam (Body) {java.lang.Long}   id    id
                * @apiParam (Body) {java.lang.String}  [name]  name
                * @apiParam (Body) {java.lang.Long}  [ownerId]  owner_id
                * @apiParam (Body) {java.lang.String}   createUser    创建人
            
            
            
                * @apiParam (Body) {java.lang.String}   status    数据有效状态
            * @apiSuccess (200) {String} data 更新记录条数
    * @apiError (400) {String} RequestParamsError 请求中的参数不符合要求
    * @apiError (500) {String} ServiceInternalError 服务器内部错误
    */
    @RequestMapping(value="/fota/pets/update",method = RequestMethod.POST)
    public ResponseEntity<?>  update(HttpServletRequest request,@RequestBody PetsDTO petsDTO) throws KakaException {
        if(Objects.isNull(petsDTO)){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"传入对象为空!");
        }
        if(Objects.isNull(petsDTO.getId())){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"更新操作主键不能为空!"+petsDTO.toString());
        }
                                                                                            petsDTO.setLastUser(this.getLoginUserName(request));
                                                                        int update= petsService.update(petsDTO);
        if(update<=0){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"更新失败!+petsDTO"+petsDTO);
        }
        return new ResponseEntity(BaseResultUtil.handlerSuccess(update), HttpStatus.OK);
    }


                                                                                                                                                                                                                              /**
     * @apiIgnore Not finished Method
     * @api {post} /fota/pets/enable/{id} 生效数据
     * @apiVersion 1.0.0
     * @apiName /fota/pets/enable/{id}
     * @apiGroup pets
     *
     * @apiHeader {String} AUTHORIZATION 认证用的的令牌
     * @apiSuccess (200) {String} data 修改记录条数
     * @apiError (400) {String} RequestParamsError 请求中的参数不符合要求
     * @apiError (500) {String} ServiceInternalError 服务器内部错误
     */
    @RequestMapping(value="/fota/pets/enable/{id}",method = RequestMethod.POST)
    public ResponseEntity<?>  enable(HttpServletRequest request,@PathVariable java.lang.Long id) throws KakaException {
        if(Objects.isNull(id)){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"传入对象为空!id="+id);
        }
        int update= petsService.enable(id ,this.getLoginUserName(request) );
        if(update<=0){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"更新失败!+update"+update);
        }
        return  new ResponseEntity(BaseResultUtil.handlerSuccess(update), HttpStatus.OK);
    }

     /**
     * @apiIgnore Not finished Method
     * @api {post} /fota/pets/disable/{id} 禁用数据
     * @apiVersion 1.0.0
     * @apiName /fota/pets/disable/{id}
     * @apiGroup pets
     *
     * @apiHeader {String} AUTHORIZATION 认证用的的令牌
     * @apiSuccess (200) {String} data 修改记录条数
     * @apiError (400) {String} RequestParamsError 请求中的参数不符合要求
     * @apiError (500) {String} ServiceInternalError 服务器内部错误
     */
    @RequestMapping(value="/fota/pets/disable/{id}",method = RequestMethod.POST)
    public ResponseEntity<?>  disable(HttpServletRequest request,@PathVariable java.lang.Long id) throws KakaException {
        if(null == id){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"传入对象为空!id="+id);
        }
        int update= petsService.disable(id,this.getLoginUserName(request));
        if(update<=0){
            throw new KakaException(ResultCode.FAILURE.getCode(),HttpStatus.BAD_REQUEST,"更新失败!+update"+update);
        }
      return  new ResponseEntity(BaseResultUtil.handlerSuccess(update), HttpStatus.OK);
    }
    
    /**
    * 获取登入用户信息
    * @param request
    * @return
    * @throws KakaException
    */
    private String getLoginUserName(HttpServletRequest request) throws KakaException {
        String authToken = request.getHeader(appConfig.getTokenHeader());
        if (StringUtils.isNotBlank(authToken)) {
        return kakaTokenService.getUsernameFromToken(authToken);
        }
        throw new KakaException(ResultCode.FAILURE.getCode(), HttpStatus.BAD_REQUEST, "传入对象为空!通过authToken获取用户id失败!");
    }
}
