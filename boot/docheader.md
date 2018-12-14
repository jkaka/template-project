<table>
<tr><th>版本</th><th>发布日期</th><th>修改人</th><th>注释</th></tr>
<tr><td>见右上角</td><td>2018-09-20</td><td> 贾双凯 </td><td> </td></tr>
</table>

## 前言 ##
本文档为***内部设计文档,内容严格保密.


## 概述 ##
本文档主要涉及云端与后台页面的交互.具体参考开放平台手册.

## 注意事项 ##

稳定域名：http://***.*****.cn

头部统一添加如下数据：
<table>
<tr><th>字段 </th> <th>类型 </th><th> 描述</th></tr>
<tr><td>AUTHORIZATION </td><td> String </td><td> 认证用的的令牌</td></tr>
<tr><td>X-APP-ID </td><td> String </td><td> 平台下发的应用标识</td></tr>
<tr><td>X-ENV-TYPE </td><td> String </td><td>  环境类型，根据此字段，前置机会路由到不同的服务集群 允许值:"testing", "staging", "production"</td></tr>
</table>
 
头部Content-type需要设置为application/json，标识请求body中的数据类型为json

头部Accept必须设置为application/json，标识需要服务器返回json类型的数据，默认是html/text

对于目前body返回的数据格式，为了兼容之前已有的格式，又保持一定的灵活性，在Accept属性上扩展如下：

当Accept传入application/json;responseformat=1时，response的body格式为

	处理成功
    {
		// 直接在这里输出返回的数据
	}

	处理错误
	{
	    "error": {
	        "code": xxx, // 后续会给出code码表
	        "messsage": xxxx // 服务器处理结果，会根据头部Accept-Language本地化
	    }
	}

当Accept传入application/json;responseformat=2时，response的body格式为

	处理成功
    {
		// 直接在这里输出返回的数据
	}

	处理错误
	{
	    "execResult": {
	        "errorCode": xxx, // 后续会给出code码表
	        "errorDesp": xxxx // 服务器处理结果，会根据头部Accept-Language本地化
	    }
	}

当Accept传入application/json;responseformat=3时，response的body格式为

	{
	    "code": xxx, // 后续会给出code码表
        "message": xxxx, // 服务器处理结果，会根据头部Accept-Language本地化
        "data": {
			// 正确时，返回数据，错误时，无数据
        }
	}

如果需要获取国际化的消息，需要使用头部的Accept-Language，默认为英文en，中文为zh-CN
