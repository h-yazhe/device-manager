// //设备品牌表格渲染
// var deviceBrandVm = new Vue({
//     el: "#devicebrand-list",
//     data: {
//         brandList: [
//             {
//                 "id": "0",
//                 "name": ""
//             }
//         ],
//         "queryPage": {
//             "pageNum": 1,
//             "pageSize": 10
//         },
//         queryParams: $.extend(true,{},searchDeviceParams),
//         pages: 1,//总页数
//         total: 0,//总条数
//         disableLastPage: true,
//         disableNextPage: true
//     },
//     methods: {
//         listDeviceBrand: function () {
//             var vue=this;
//             sendPost({
//                 url: API.getApi(API.listDeviceBrand),
//                 data: JSON.stringify(vue.queryPage),
//                 success: function (res) {
//                     console.log(res);
//                     if (res.code == 0) {
//                         deviceBrandVm.brandList =res.data.list;
//                         deviceBrandVm.total = res.data.total;
//                         deviceBrandVm.pages = res.data.pages;
//                         deviceBrandVm.disableNextPage = deviceBrandVm.queryParams.queryPage.pageNum === res.data.pages;
//                         deviceBrandVm.disableLastPage = deviceBrandVm.queryParams.queryPage.pageNum <= 1;
//                     } else {
//                         alert(res.msg);
//                     }
//                 },
//                 error: function (res) {
//                     var json = res.responseJSON;
//                     if (json != null && json.code == 3) {
//                         alert("登录异常！");
//                     } else {
//                         alert("网络连接异常！");
//                     }
//                 }
//             });
//         },
//         deleteBrand: function (brandId) {
//             sendPost({
//                 url: API.getApi(API.deleteBrand) + brandId,
//                 success: function (data) {
//                     console.log(data);
//                     if (data.code == 0) {
//                         alert(data.msg);
//                         deviceBrandVm.listDeviceBrand();
//                     } else {
//                         alert("删除失败！");
//                     }
//                 }
//             })
//         }
//     },
//     created: function () {
//         this.listDeviceBrand();
//     }
// });
//添加设备品牌
var addDeviceBrandVm=new Vue({
    el:"#add-devicebrand",
    data: {
        brand: {
            "id": "",
            "name": ""
        }
    },
    methods: {
        addDeviceBrand: function () {
            var data =this.brand;
            sendPost({
                url: API.getApi(API.addDeviceBrand),
                data: JSON.stringify(data),
                success: function (res) {
                    if (res.code == 0) {
                        alert("添加成功！");
                        $("#add-devicebrand").modal('toggle');
                        $("#input-brand-name").val('');
                        vueDeviceList.listDeviceBrand();
                    } else {
                        console.log(res);
                        alert(res.msg);
                    }
                }
            });
        }
    }
});
// //设备型号表格渲染
// var deviceModelVm = new Vue({
//     el: "#devicemodel-list",
//     data: {
//         deviceModelList: [
//             {
//                 "id": "0",
//                 "name": ""
//             }
//         ],
//         "queryPage": {
//             "pageNum": 1,
//             "pageSize": 10
//         },
//         queryParams: $.extend(true,{},searchDeviceParams),
//         pages: 1,//总页数
//         total: 0,//总条数
//         disableLastPage: true,
//         disableNextPage: true
//     },
//     methods: {
//         listDeviceModel: function () {
//             var vue = this;
//             sendPost({
//                 url: API.getApi(API.listDeviceModel),
//                 data: JSON.stringify(vue.queryPage),
//                 success: function (res) {
//                     console.log(res);
//                     if (res.code == 0) {
//                         deviceModelVm.deviceModelList = res.data.list;
//                         deviceModelVm.total = res.data.total;
//                         deviceModelVm.pages = res.data.pages;
//                         deviceModelVm.disableNextPage = deviceModelVm.queryParams.queryPage.pageNum === res.data.pages;
//                         deviceModelVm.disableLastPage = deviceModelVm.queryParams.queryPage.pageNum <= 1;
//                     } else {
//                         alert(res.msg);
//                     }
//                 },
//                 error: function (res) {
//                     var json = res.responseJSON;
//                     if (json != null && json.code == 3) {
//                         alert("登录异常！");
//                     } else {
//                         alert("网络连接异常！");
//                     }
//                 }
//             });
//         },
//         deleteDeviceModel:function (deviceModelId) {
//             sendPost({
//                 url:API.getApi(API.deleteDeviceModel)+deviceModelId,
//                 success:function(data){
//                     console.log(data);
//                     if(data.code==0)
//                     {
//                         alert(data.msg);
//                         deviceModelVm.listDeviceModel();
//                     }
//                     else
//                         alert("删除失败！");
//                 }
//             })
//         }
//     },
//     created: function () {
//         this.listDeviceModel();
//     }
// });
//添加设备型号
var addDeviceModelVm=new Vue({
    el:"#add-devicemodel",
    data:{
        deviceModel: {
            "id":"",
            "name":""
        }
    },
    methods: {
        addDeviceModel: function () {
            var data =this.deviceModel;
            sendPost({
                url: API.getApi(API.addDeviceModel),
                data: JSON.stringify(data),
                success: function (res) {
                    if (res.code == 0) {
                        alert("添加成功！");
                        $("#add-devicemodel").modal('toggle');
                        $("#input-device-model").val('');
                        vueDeviceList.listDeviceModel();
                    } else {
                        console.log(res);
                        alert(res.msg);
                    }
                }
            });
        }
    }
});
// //工作性质表格渲染
// var workNatureVm = new Vue({
//     el: "#worknature-list",
//     data: {
//         workNatureList: [
//             {
//                 "id": "0",
//                 "name": ""
//             }
//         ],
//         "queryPage": {
//             "pageNum": 1,
//             "pageSize": 10
//         },
//         queryParams: $.extend(true,{},searchDeviceParams),
//         pages: 1,//总页数
//         total: 0,//总条数
//     },
//     methods: {
//         listWorkNature: function () {
//             var vue=this;
//             sendPost({
//                 url: API.getApi(API.listWorkNature),
//                 data: JSON.stringify(vue.queryPage),
//                 success: function (res) {
//                     console.log(res);
//                     if (res.code == 0) {
//                         workNatureVm.workNatureList = res.data;
//                     } else {
//                         alert(res.msg);
//                     }
//                 },
//                 error: function (res) {
//                     var json = res.responseJSON;
//                     if (json != null && json.code == 3) {
//                         alert("登录异常！");
//                     } else {
//                         alert("网络连接异常！");
//                     }
//                 }
//             });
//         },
//         deleteWorkNature:function (workNatureId) {
//             sendPost({
//                 url:API.getApi(API.deleteWorkNature)+workNatureId,
//                 success:function (data) {
//                     console.log(data);
//                     if(data.code==0)
//                     {
//                         alert(data.msg);
//                         workNatureVm.listWorkNature();
//                     }
//                     else
//                         alert("删除失败！");
//                 }
//             })
//         }
//     },
//     created: function () {
//         this.listWorkNature();
//     }
// });
//添加工作性质
var addWorkNatureVm=new Vue({
    el:"#add-worknature",
    data:{
        workNature: {
            "id": "",
            "name": ""
        }
    },
    methods: {
        addWorkNature: function () {
            var data =this.workNature;
            sendPost({
                url: API.getApi(API.addWorkNature),
                data: JSON.stringify(data),
                success: function (res) {
                    if (res.code == 0) {
                        alert("添加成功！");
                        $("#add-worknature").modal('toggle');
                        $("#input-work-nature").val('');
                        vueDeviceList.listWorkNature();
                    } else {
                        console.log(res)
                        alert(res.msg);
                    }
                }
            });
        }
    }
});
