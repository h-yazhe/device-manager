
## 设备维修

 ### 设备
 
  #### 1.报修设备，提交报修设备订单
  
    url:`submit-repair-order`
    
  
  #### 2.根据状态码查找保修设备
      
    url:`/select-repair-order-statusCode`
      
    data:
    
      {
      	"statusCode": 2,
      	"queryPage": {
      		"pageNum": 1,
      		"pageSize": 3
      	}
      }
      
    字段说明：
      
     字段名 | 描述 | 参数类型 | 必要
     :-----: | :------: | :-----: | :-----:
     statusCode | 状态代码 | tinyint | 1 |
     queryPage | 分页参数 | obj | 1 | 
     pageNum | 页码 | int | 1 |
     pageSize | 每页数量 | int | 1 |
      
     返回值示例：
      
      {
        "code": 0,
        "msg": "成功",
        "data": {
            "pageNum": 1,
            "pageSize": 3,
            "size": 3,
            "startRow": 1,
            "endRow": 3,
            "total": 6,
            "pages": 2,
            "list": [
                {
                    "deviceId": "1539070039650377108",
                    "applyUserId": "123456",
                    "dealUserId": "123456789",
                    "description": "test",
                    "statusCode": 2,
                    "expectedTime": 1539863479000
                },
                {
                    "deviceId": "1539070039650377109",
                    "applyUserId": "123456",
                    "dealUserId": "123456789",
                    "description": "test",
                    "statusCode": 2,
                    "expectedTime": 1539863479000
                },
                {
                    "deviceId": "1539070039650377159",
                    "applyUserId": "123456",
                    "dealUserId": "123456789",
                    "description": "test",
                    "statusCode": 2,
                    "expectedTime": 1539863479000
                }
            ],
            "prePage": 0,
            "nextPage": 2,
            "isFirstPage": true,
            "isLastPage": false,
            "hasPreviousPage": false,
            "hasNextPage": true,
            "navigatePages": 8,
            "navigatepageNums": [
                1,
                2
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 2,
            "firstPage": 1,
            "lastPage": 2
        }
      }
    
  #### 3.根据userId查找保修设备
      
     url:`/select-repair-order-userId`
      
     data:
     
       {
    	 "userId": "1526467363362171844",
    	 "queryPage": {
    		"pageNum": 1,
    		"pageSize": 3
    	    }
       }
       
     字段说明：
      
     字段名 | 描述 | 参数类型 | 必要
     :-----: | :------: | :-----: | :-----:
     userId | 用户id| string | 1 |
     queryPage | 分页参数 | obj | 1 | 
     pageNum | 页码 | int | 1 |
     pageSize | 每页数量 | int | 1 |
      
     返回值示例：
      
        {
        "code": 0,
        "msg": "成功",
        "data": {
            "pageNum": 1,
            "pageSize": 3,
            "size": 1,
            "startRow": 1,
            "endRow": 1,
            "total": 1,
            "pages": 1,
            "list": [
                {
                    "deviceId": "1529668662622323236",
                    "applyUserId": "1526467363362171844",
                    "dealUserId": "123456789",
                    "description": "hhhhh",
                    "statusCode": 2,
                    "expectedTime": 1539867619000
                }
            ],
            "prePage": 0,
            "nextPage": 0,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 1,
            "lastPage": 1,
            "firstPage": 1
        }
    }
    
 ### 订单
 
  #### 1.删除维修订单(申请用户可删除自己的，管理员都可以删除)
  
    url:`delete-repair-order`
  
    data: 
    ```
    {
       "id": "123"
    }
    ```
    字段名 | 描述 | 备注
    :-----: | :-------: | :-------:
    id | 维修订单的id | 通过订单id删除相应的订单 
 
    返回值示例:
    ```
    {
    "code": 0,
    "msg": "成功"
    }
    ```

 #### 2.修改订单(申请维修的用户调用)
    
   url: `modify-repair-order` (POST方法)
    
   data: 
   
    {
         "id": 1,
         "description":"Oops，Something Wrong!",
         "deviceId":"1"
    }
   
   字段名 | 描述 | 备注 
   :-----: | :-------: | :-------: 
   id | 维修订单的id | 
   description | 设备故障问题描述 
   deviceId | 故障设备id
   
  返回值示例:
  ```
  {
      "code": 0,
      "msg": "成功"
  }
  ```
   
 #### 3.根据设备id获取订单
      
   url:`get-orders-by-device-id`(GET方法)
      
   data: 
      
      deviceId=1
      
     
   字段名 | 描述 | 备注 
   :-----: | :-------: | :-------: 
   deviceId | 要查询的设备id |
     
   返回值示例:
    
    {
        "code": 0,
        "msg": "成功",
        "data": [
            {
                "id": 7,
                "deviceId": "1529668662622323236",
                "applyUserId": "1526467363362171844",
                "dealUserId": "教务处",
                "description": "hhhhh",
                "statusCode": 2,
                "expectedTime": 1539016055000,
                "createTime": 1540196850000,
                "updateTime": 1540196850000
            }
        ]
    }
 
 
 #### 4.管理员（维修人员）调用来完结订单
       
   url:`finish-order-admin` (GET方法)
       
   data: 
       
       orderId=2&orderStatus=2
       
      
   字段名 | 描述 | 备注 
   :-----: | :-------: | :-------: 
   orderId | 要完结的订单的id |
   orderStatus | 完结后的状态(已维修或失败)
      
   返回值示例:
     
     {
         "code": 0,
         "msg": "成功"
     }
   
 #### 5.申请人员调用来完结订单
          
   url:`finish-order-user` (GET方法)
          
   data: 

    orderId=2&deviceStatus=2
          
         
   字段名 | 描述 | 备注 
   :-----: | :-------: | :-------: 
   orderId | 要完结的订单的id |
   deviceStatus | 完结后的设备状态(使用中、报废等等)
         
   返回值示例:
        
        {
            "code": 0,
            "msg": "成功"
        }