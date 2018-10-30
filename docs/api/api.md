# API文档
api前缀：`http://39.108.97.103:8080/dev-manager/api_v1/`

tips:公共api前使用*标注,非公共api需在header中设置`token`参数，token由登录接口获取，
所有接口均使用post方法，数据类型采用json格式

## 设备管理
### 设备
#### 1.添加设备

url:`device/add`

data: 
```
{
	"name": "测试zzz",
	"locationId": "1536914080429756317",
	"nationalId": "zzzzz",
	"serialNumber": "1234567",
	"deviceModelId": "2",
	"workNatureId": "1",
	"custodianId": "1",
	"unitPrice": "250",
	"brandId": "1527745606203104480",
	"categoryIds": ["1536859612504237330"],
	"description": "设备描述"
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
name | 设备名 | str | 1 | 设备名只能为汉字、英文字母、数字、下划线的组合
locationId | 地点id | str | 1 |
nationalId | 国资编号 | str | 0 | 国资编号只能为英文字母、数字的组合
serialNumber | 序列号 | str | 1 | 序列号只能为英文字母、数字的组合
deviceModelId | 设备型号id | int | 1 | 
workNatureId | 工作性质id | str | 1 |
custodianId | 保管人id | str | 1 |
unitPrice | 单价 | num | 0 | 预留字段，暂不需要
brandId | 品牌id | str | 1 |
categoryIds | 分类id列表 | str | 1 |
description | 设备描述 | str | 0 | 设备的详细信息，如配置信息等

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:

#### 2.更新设备
url:`device/update`

data: 
```
{
	"id": "1",
	"name": "测试zzz",
	"locationId": "1536914080429756317",
	"nationalId": "zzzzz",
	"serialNumber": "1234567",
	"deviceModelId": "2",
	"workNatureId": "1",
	"custodianId": "1",
	"unitPrice": "250",
	"brandId": "1527745606203104480",
	"categoryIds": ["1536859612504237330"],
    "description": "设备描述"
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
id | 设备id | str | 1 | 
name | 设备名 | str | 1 | 设备名只能为汉字、英文字母、数字、下划线的组合
locationId | 地点id | str | 1 |
nationalId | 国资编号 | str | 0 | 国资编号只能为英文字母、数字的组合
serialNumber | 序列号 | str | 1 | 序列号只能为英文字母、数字的组合
deviceModelId | 设备型号id | int | 1 | 
workNatureId | 工作性质id | str | 1 |
custodianId | 保管人id | str | 1 |
unitPrice | 单价 | num | 0 | 预留字段，暂不需要
brandId | 品牌id | str | 1 |
categoryIds | 分类id列表 | str | 1 |
description | 设备描述 | str | 0 | 设备的详细信息，如配置信息等

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:

#### 3.根据条件查询设备列表
tips:若有多个参数，匹配结果取交集
url:`device/list`

data: 
```
{
	"workNatureId": "111",
	"custodianId": "2222",
	"deviceModelId": 111,
	"amountUnitId": "asdz",
	"brandId": "123",
	"categoryId": "aaa",
	"locationId": "zzz",
	"nationalId": "ada123",
	"statusId": 1,
	"startTime": 1537112303701,
	"endTime": 1537112303704,
	"queryKey": "测试",
	"queryPage": {
		"pageNum": 1,
		"pageSize": 20
	}
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
workNatureId | 工作性质id | str | 0 | 
custodianId | 保管人id | str | 0 | 
deviceModelId | 设备型号id | int | 0 |
amountUnitId | 计量单位id | str | 
brandId | 品牌id | str | 0| 序列号只能为英文字母、数字的组合
categoryId | 分类id | str | 0 | 匹配该分类及其子分类下的设备
locationId | 地点d | str | 0 | 匹配该地点及其子地点下的设备
nationalId | 国资编号 | str | 0 | 
statusId | 状态码 | num | 0 |  
startTime | 领用时间范围的的起始时间 | 时间戳 | 0 |
endTime | 领用时间范围的结束时间 | 时间戳 | 0 |
queryKey | 搜索键 | str | 0 | 模糊匹配设备id、名称、序列号
queryPage | 分页参数 | obj | 1 | 

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "total": 6,
        "pages": 3,
        "list": [
            {
                "id": "1533799349223338772",
                "name": "测试设备1",
                "nationalId": "a111345423",
                "serialNumber": "44444444",
                "useTime": 1536657241000,
                "unitPrice": 100.4,
                "description": "",
                "statusId": 2,
                "createTime": 1533770481000,
                "updateTime": 1536628442000,
                "brand": {
                    "id": "2",
                    "name": "苹果"
                },
                "locationStr": "雅安",
                "categoryStr": "电脑/台式机/神舟",
                "workNature": "自用",
                "custodian": "李四",
                "amountUnit": "个"
            },
            {
                "id": "1536216465902587143",
                "name": "测试设备4",
                "nationalId": "a111345423",
                "serialNumber": "44444444",
                "useTime": 1536662272000,
                "unitPrice": 100.4,
                "description": "",
                "statusId": 3,
                "createTime": 1536187669000,
                "updateTime": 1536634036000,
                "brand": {
                    "id": "2",
                    "name": "苹果"
                },
                "locationStr": "都江堰",
                "categoryStr": "电脑/台式机/神舟",
                "workNature": "自用",
                "custodian": "李四",
                "amountUnit": "个"
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
            2,
            3
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 3,
        "firstPage": 1,
        "lastPage": 3
    }
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:
name | 设备名称 | 
nationalId | 国资编号 |
serialNumber | 序列号 |
useTime | 领用时间 | 
unitPrice | 单价 | 
description | 设备描述 | 
statusId | 设备状态码 | 1:入库，2：使用中，3：报废，4：报修
createTime | 入库时间 |
updateTime | 更新时间 |
brand.name | 品牌名称 | 
locationStr | 设备所属地点 | 
categoryStr | 分类 |
workNature | 工作性质 |
custodian | 保管人名字 |
amountUnit | 计量单位 | 

#### 4.获取搜索的选项卡第一页数据
url:`device-search-selection/{pageSize}`

data: 
```
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
pageSize | 每页数据量 | int | 1 | 

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:

#### 5.获取搜索的选项卡第一页数据
url:`device/repair/{deviceId}`

data: 
```
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
deviceId | 设备id | str | 1 | 

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:

#### 6.根据设备id查询设备状态变更记录
url:`device/get-status-record-by-deviceId`

data: 
```
{
	"deviceId": "1536510045396168455",
	"queryPage": {
		"pageNum": 1,
		"pageSize": 20
	}
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
deviceId | 设备id | str | 1 | 
queryPage | 分页参数 | obj |

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "pageNum": 1,
        "pageSize": 20,
        "size": 1,
        "startRow": 1,
        "endRow": 1,
        "total": 1,
        "pages": 1,
        "list": [
            {
                "id": "1537970859852284993",
                "fromStatus": -1,
                "toStatus": 1,
                "operateTime": 1537942059000,
                "operateUserId": "1526467363362171844",
                "fromLocation": "十教",
                "toLocation": "温江",
                "operateUserRealName": "黄雅哲"
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
        "firstPage": 1,
        "lastPage": 1
    }
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:
fromStatus | 本来的状态 | 若为-1，则表示新添加的设备 
toStatus | 改变的状态 | 
fromLocation | 变更前的地点 | 
toLocation | 变更后的地点 |
operateUserId | 操作的用户id |
operateUserRealName | 操作用户的真实姓名 | 
operateTime | 改变设备状态的操作时间 |

#### 7 .根据设备id更改设备维护状态
url:`/update-repair-status-by-deviceId`

data: 
```
{
	"id": "1536914080429756317",
	"statusId": "1"
}
```


参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
id | 设备id | String | s | 
statusId | 希望修改后设备的状态值 | Integer |s

返回值示例:
```
{
    "code": 0,
    "msg": "成功"
}
```

#### 8.添加设备型号
 url:`/device-model-submit`
 data:
 ```$xslt
    {
    	"name":"小米"
    }
 ```
 参数说明：

 字段名 | 描述 | 参数类型 | 必要 | 备注
 :-----: | :-------: | :-------: | :------: | :-------:
 name | 型号名称 | String | 1 | 

 返回值示例:
 ```
 {
    "code": 0,
    "msg": "成功",
    "data": true
 }
 ```

#### 9.查找所有设备型号
    url:`/device-model-listAll`
    data:无
    返回值示例：
    ```
    {
        "code": 0,
        "msg": "成功",
        "data": {
            "pageNum": 1,
            "pageSize": 10,
            "size": 4,
            "startRow": 1,
            "endRow": 4,
            "total": 4,
            "pages": 1,
            "list": [
                {
                    "id": 1,
                    "name": "联想Y50",
                    "createTime": 1536534725000,
                    "updateTime": 1536534725000
                },
                {
                    "id": 2,
                    "name": "iphoneX",
                    "createTime": 1536639295000,
                    "updateTime": 1536639295000
                },
                {
                    "id": 3,
                    "name": "华为",
                    "createTime": 1540889889000,
                    "updateTime": 1540889889000
                },
                {
                    "id": 4,
                    "name": "中兴",
                    "createTime": 1540908728000,
                    "updateTime": 1540908728000
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
    ```

### 分类
#### 1.根据父id查询分类

url:`list-category-by-pId`

data: 
```
{
     "parentId": "123",
     "queryPage": {
        "pageNum": 1,
        "pageSize": 10
     }
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
parentId | 父分类id | str | 1 | 为空字符串则查询根节点
queryPage | 分页参数 | obj | 1 |
pageNum | 页码 | int | 1 |
pageSize | 每页数量 | int | 1 |

返回值示例:
```
{
  "code": 0,
  "msg": "成功",
  "data": [
    {
      "id": "1533627421723595723",
      "parentId": "",
      "name": "电脑",
      "level": 1,
      "path": "/"
    },
    {
      "id": "0",
      "parentId": "",
      "name": "默认分类",
      "level": 1,
      "path": "/"
    }
  ]
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:
parentId | 父分类id | 
name  |  分类名 | 
level | 层级 |
path | 节点路径 |  以id作为标识，根节点为/

2.根据父id插入一个子分类
url:`insert-category-by-pid`

data: 
```
{
	"parentId": "1533627421723777410",
	"name": "惠普"
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
parentId | 父分类id | str | 1 | 为空字符串则查询根节点
name | 分类名称 | str | 1 |

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:

3.删除该节点为根的分类树

tips:删除分类后，该分类下的所有设备全部转移到默认分类下

url:`delete-category-by-id/{rootId}`

data: 
```
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
rootId | 要删除的根节点id | str | 1 | 

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:


### 地点
#### 1.根据父id插入一个子分类
url:`insert-location-by-pid`

data: 
```
{
   "name": "十教",
   "parentId": "1"
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
parentId | 父地点id | str | 1 | 为空字符串则查询根节点
name | 地点名称 | str | 1 |

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:

2.删除该节点为根的地点树

tips:删除地点后，同时将删除所有节点关联的设备

url:`delete-location-tree-by-Id/{rootId}`

data: 
```
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
rootId | 要删除的根节点id | str | 1 | 

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:

3.根据父id查询地点
  
  url:`list-location-by-pid`
  
  data: 
  ```
  {
       "parentId": "123",
       "queryPage": {
          "pageNum": 1,
          "pageSize": 10
       }
  }
  ```
  参数说明：
  
  字段名 | 描述 | 参数类型 | 必要 | 备注
  :-----: | :-------: | :-------: | :------: | :-------:
  parentId | 父地点id | str | 1 | 为空字符串则查询根节点
  queryPage | 分页参数 | obj | 1 |
  pageNum | 页码 | int | 1 |
  pageSize | 每页数量 | int | 1 |
  
  返回值示例:
  ```
  {
      "code": 0,
      "msg": "成功",
      "data": [
          {
              "id": "2",
              "parentId": "",
              "name": "温江",
              "level": 1,
              "path": "/"
          },
          {
              "id": "3",
              "parentId": "",
              "name": "都江堰",
              "level": 1,
              "path": "/"
          },
          {
              "id": "1",
              "parentId": "",
              "name": "雅安",
              "level": 1,
              "path": "/"
          }
      ]
  }
  ```
  字段说明：
  
  字段名 | 描述 | 备注
  :-----: | :-------: | :-------:
  parentId | 父地点id | 
  name  |  地点名 | 
  level | 层级 |
  path | 节点路径 |  以id作为标识，根节点为/
  ##工作性质（work_nature）
  1.添加工作性质
  
  url:`work_nature/add`
  
  data: 
  ```
  {
  	"name": "他人使用"
  }
  ```
  参数说明：
  
  字段名 | 描述 | 参数类型 | 必要 | 备注
  :-----: | :-------: | :-------: | :------: | :-------:
  name | 工作性质的名字 | str | 1 | 
  
  
  返回值示例:
  ```
  {
      "code": 0,
      "msg": "成功"
  }
  ```
  2.查询所有工作性质的记录
  
  url:`work_nature/listAll`
  
  
  返回值示例:
  ```
  {
      "code": 0,
      "msg": "成功",
      "data":[
                     {
                         "id": "1",
                         "name": "自用"
                     },
                     {
                         "id": "1540826238787360568",
                         "name": "他人使用"
                     }
                 ]
  }
  ```