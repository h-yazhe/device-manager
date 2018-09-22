##用户管理
1.添加用户

url:`user/add`

data: 
```
{
	"username": "用户名1",
	"password": "123456",
	"roleId": "1",
	"realName": "王五",
	"email": "1234123@qq.com",
	"phone": "12345678911",
	"address": "地址"
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
username | 用户名 | str | 1 | 
password | 密码 | str | 1 |
roleId | 角色id | str | 1 | 
realName | 真实姓名 | str | 1 |
email | 邮箱 | str | 1 | 
phone | 电话 | str | 1 |
address | 地址 | str | 0 |

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

2.分页查询所有角色

url:`role/list-by-page`

data: 
```
{
    "pageNum": 1,
	"pageSize": 20
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
pageNum | 页码 | int | 1 | 
pageSize | 每页数据量 | int | 1 |

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "pageNum": 1,
        "pageSize": 20,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "total": 12,
        "pages": 1,
        "list": [
            {
                "id": "1",
                "name": "管理员",
                "locked": false,
                "createTime": 1526202702000,
                "updateTime": 1526202698000,
                "permissionList": [
                    {
                        "id": "8",
                        "resource": "permission",
                        "resourceName": "权限",
                        "permissionCode": "permission:get",
                        "permissionName": "查询",
                        "required": false
                    },
                    {
                        "id": "4",
                        "resource": "role",
                        "resourceName": "角色",
                        "permissionCode": "role:get",
                        "permissionName": "查询",
                        "required": false
                    }
                ]
            },
            {
                "id": "2",
                "name": "招新用",
                "locked": false,
                "createTime": 1536959060000,
                "updateTime": 1536959060000,
                "permissionList": [
                    {
                        "id": "12",
                        "resource": "brand",
                        "resourceName": "品牌",
                        "permissionCode": "brand:get",
                        "permissionName": "查询",
                        "required": false
                    }
                ]
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
name | 角色名 |
locked | 是否锁定 | 锁定就是不能用了
createTime | 创建时间 | 
updateTime | 更新时间 | 
permissionList | 权限列表 |
permissionList.resourceName | 权限对应资源的名称 | 
permissionName | 权限操作的名称 |

3.分页查询所有用户

url:`user/list-by-page`

data: 
```
{
    "pageNum": 1,
	"pageSize": 20
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
pageNum | 页码 | int | 1 | 
pageSize | 每页数据量 | int | 1 |

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "id": "1526467363362171844",
            "username": "hyz",
            "realName": "黄雅哲",
            "email": "609927332@qq.com",
            "phone": "18728193205",
            "address": " ",
            "locked": false,
            "createTime": 1526467363000,
            "updateTime": 1526467363000,
            "lastTime": 1526467363000,
            "roleList": [
                {
                    "id": "1",
                    "name": "管理员",
                    "locked": false,
                    "createTime": 1526202702000,
                    "updateTime": 1526202698000
                }
            ],
            "permissionList": [
                {
                    "id": "0",
                    "resource": "user",
                    "resourceName": "用户",
                    "permissionCode": "user:add",
                    "permissionName": "添加",
                    "required": false
                },
                {
                    "id": "15",
                    "resource": "location",
                    "resourceName": "地点",
                    "permissionCode": "location:update",
                    "permissionName": "更新",
                    "required": false
                },
                {
                    "id": "16",
                    "resource": "location",
                    "resourceName": "地点",
                    "permissionCode": "location:delete",
                    "permissionName": "删除",
                    "required": false
                }
            ]
        },
        {
            "id": "1536670730690452459",
            "username": "test1",
            "realName": "张三",
            "email": "111123@163.com",
            "phone": "11111",
            "address": "",
            "locked": false,
            "createTime": 1536641932000,
            "updateTime": 1536641932000,
            "lastTime": 1536641932000,
            "roleList": [
                {
                    "id": "1",
                    "name": "管理员",
                    "locked": false,
                    "createTime": 1526202702000,
                    "updateTime": 1526202698000
                }
            ],
            "permissionList": [
                {
                    "id": "0",
                    "resource": "user",
                    "resourceName": "用户",
                    "permissionCode": "user:add",
                    "permissionName": "添加",
                    "required": false
                },
                {
                    "id": "15",
                    "resource": "location",
                    "resourceName": "地点",
                    "permissionCode": "location:update",
                    "permissionName": "更新",
                    "required": false
                }
            ]
        }
    ]
}
```
字段说明：

字段名 | 描述 | 备注
:-----: | :-------: | :-------:
username | 用户名 |
realName | 真实姓名 |
email | 邮箱 |
phone | 电话 |
address | 地址 |
locked | 锁定 | 锁定则不可使用
createTime | 创建时间 | 
updateTime | 更新时间 | 不用显示到表格
lastTime | 上次登录时间 |
roleList.name | 角色名 | 

4.通过用户id删除用户

url:`delete/{userId}`

data: 
```
```

参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
userId | 用户id | str | 1 | 

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
