#API文档
api前缀：`http://39.108.97.103:8080/dev-manager/api_v1/`\
tips:公共api前使用*标注,非公共api需在header中设置`token`参数，token由登录接口获取，
所有接口均使用post方法

##设备管理
###分类
1.根据父id查询分类\
url:`list-category-by-pId`\
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

字段名 | 描述 | 参数类型 | 必要
:-----: | :-------: | :-------: | :------:
parentId | 父分类id | str | 1
queryPage | 分页参数 | obj | 1
pageNum | 页码 | int | 1
pageSize | 每页数量 | int | 1

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