### 品牌
#### 1.添加品牌
url:`device/add`

data: 
```
{
	"name": "长虹"
}
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
name | 品牌名 | str | 1 | 只能为汉字、英文字母、数字、下划线的组合

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

#### 2.删除品牌
url:`delete-brand/{id}`

data: 
```
```
参数说明：

字段名 | 描述 | 参数类型 | 必要 | 备注
:-----: | :-------: | :-------: | :------: | :-------:
id | 品牌id | str | 1 | 

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

#### 3.分页查询所有品牌
url:`get-brands`

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

返回值示例:
```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "pageNum": 1,
        "pageSize": 20,
        "size": 7,
        "startRow": 1,
        "endRow": 7,
        "total": 7,
        "pages": 1,
        "list": [
            {
                "id": "1527744129765806748",
                "name": "三星"
            },
            {
                "id": "1527745606203104480",
                "name": "华硕"
            },
            {
                "id": "1541225443267400491",
                "name": "长虹"
            },
            {
                "id": "2",
                "name": "苹果"
            },
            {
                "id": "3",
                "name": "联想"
            },
            {
                "id": "98ca6dcbb8af11e8b2a50242ac110002",
                "name": "三星"
            },
            {
                "id": "98d8ab40b8af11e8b2a50242ac110002",
                "name": "三星"
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
id     | 品牌id | 
name   | 品牌名称 |
