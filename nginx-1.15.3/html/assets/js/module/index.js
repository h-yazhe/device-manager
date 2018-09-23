//表格渲染
var vueDeviceList = new Vue({
    el: "#device-list",
    components: {
      'search-device': SearchDevice
    },
    data: {
        deviceList: [
            {
                "id": "0",
                "name": "",
                "nationalId": "",
                "serialNumber": "",
                "useTime": 0,
                "unitPrice": 0,
                "statusId": 0,
                "createTime": 0,
                "updateTime": 0,
                "brand": {
                    "id": "0",
                    "name": ""
                },
                "locationStr": "",
                "categoryStr": "",
                "workNature": "",
                "custodian": "",
                "amountUnit": "",
                "useDepartment": "",
                "deviceModel": ""
            }
        ],
        queryParams: $.extend(true,{},searchDeviceParams),
        pages: 1,//总页数
        total: 0,//总条数
        disableLastPage: true,
        disableNextPage: true,
        //搜索的选项卡数据,
        searchSelection: $.extend(true,{},deviceSearchSelection)
    },
    methods: {
        /**
         * 渲染表格
         */
        listDevice: function () {
            var vue = this;
            sendPost({
                url: API.getApi(API.listDevice),
                data: JSON.stringify(vue.queryParams),
                success: function (res) {
                    var data = res.data;
                    if (res.code == 0) {
                        vueDeviceList.deviceList = data.list;
                        vueDeviceList.total = data.total;
                        vueDeviceList.pages = data.pages;
                        vueDeviceList.disableNextPage = vueDeviceList.queryParams.queryPage.pageNum === data.pages;
                        vueDeviceList.disableLastPage = vueDeviceList.queryParams.queryPage.pageNum <= 1;
                    } else {
                        alert(res.msg);
                    }
                },
                error: function (res) {
                    var json = res.responseJSON;
                    if (json != null && json.code == 3) {
                        alert("登录异常！");
                        window.location.href = "login.html";
                    } else {
                        alert("网络连接异常！");
                    }
                }
            });
        },
        //翻到上一页
        lastPage: function () {
            if (this.queryParams.queryPage.pageNum > 1) {
                this.queryParams.queryPage.pageNum--;
                this.listDevice();
            }
        },
        //翻到下一页
        nextPage: function () {
            if (this.pages > this.queryParams.queryPage.pageNum) {
                this.queryParams.queryPage.pageNum++;
                this.listDevice();
            }
        },
        //格式化时间
        formatTime: function (timestamp,deviceStatusId) {
            return deviceStatusId===1 ? '' : formatTime(timestamp);
        },
        /**
         * 解析设备状态
         * @param status 状态吗
         * @returns {*}中文释义
         */
        parseStatus: function (status) {
            switch (status) {
                case 1:
                    return '入库';
                case 2:
                    return '使用中';
                case 3:
                    return '报废';
            }
        },
        getDepartment: function (locationStr) {
            return locationStr.substring(locationStr.indexOf('/') + 1 ,locationStr.length);
        },
        showDistributeModal: function (deviceId) {
            deviceModalVm.distributeDeviceParam.deviceIdList.push(deviceId);
            $('#distribute-device-modal').modal('toggle');
        },
        showDiscardModal: function (deviceId) {
            deviceModalVm.discardDeviceParam.deviceId = deviceId;
            $('#discard-device-modal').modal('toggle');
        }
    },
    created: function () {
        this.listDevice(this);
    }
});

//渲染分发设备模态框，报废设备模态框
var deviceModalVm = new Vue({
    el: '#device-modals',
    components: {
        'distribute-device': DistributeDevice,
        'discard-device': DiscardDevice
    },
    data: {
        //分发设备的参数
        distributeDeviceParam: {
            deviceIdList: [],
            locationId: null
        },
        distributeSelection: $.extend(true,{},deviceSearchSelection),
        discardDeviceParam: {
            deviceId: null
        }
    },
    template: '<div>'+
        '<distribute-device :distributeParam=\"distributeDeviceParam\" :selection=\"distributeSelection\"></distribute-device>'+
        "<discard-device :discardParams=\"discardDeviceParam\"></discard-device>"+
        '</div>'
});

//生成分类树，依赖于addDeviceVm
var categoryVm = new Vue({
    el: '#category-tree',
    components: {
        'CategoryTree': CategoryTree
    },
    data: {
        categoryList: [
            {
                id: '0',
                name: '',
                level: '',
                children: [
                ],
                active: false,//是否激活
                expanded: false//是否展开
            }
        ]
    },
    template: ' <div id="category-tree" class="panel-body">\n' +
        '                            <CategoryTree v-for="(item,i) in categoryList" :index="i" :parent="item" :key="item.id"></CategoryTree>\n' +
        '                        </div>'
});
//添加设备
var addDeviceVm = new Vue({
    el: "#add-device",
    data: {
        device: {
            "amountUnitId": "",
            "brandId": "",
            "categoryIds": [
                ""
            ],
            "deviceModelId": 0,
            "useDepartmentId": 0,
            "custodianId": "",
            "locationId": "",
            "name": "",
            "nationalId": "",
            "serialNumber": "",
            "unitPrice": 0,
            "workNatureId": ""
        },
        //选项卡数据
        selection: $.extend(true,{},deviceSearchSelection)

    },
    methods:{
        //添加设备
        addDevice: function () {
            var data = this.device;
            sendPost({
                url: API.getApi(API.addDevice),
                data: JSON.stringify(data),
                success: function (res) {
                    if (res.code == 0){
                        alert("添加成功！");
                        $("#add-device").modal('toggle');
                        //刷新设备列表
                        vueDeviceList.listDevice();
                    }else {
                        alert(res.msg);
                    }
                }
            });
        },
        //获取添加设备选项卡数据
        getDeviceSelection: function () {
            var self = this;
            sendPost({
                url: API.getApi(API.getDeviceSelection),
                success: function (res) {
                    if (res.code === 0){
                        var categoryList = $.extend(true,[],res.data.categoryList);
                        for (var category of categoryList){
                            initCategory(category);
                        }
                        categoryVm.categoryList = categoryList;
                        //渲染选项卡数据
                        self.selection.categoryList = res.data.categoryList;
                        self.selection.brandList = res.data.brandList;
                        self.selection.deviceModelList = res.data.deviceModelList;
                        self.selection.locationList = res.data.locationList;
                        self.selection.workNatureList = res.data.workNatureList;
                        self.selection.custodianList = res.data.custodianList;
                        //选项数据同样给到搜索设备组件中
                        vueDeviceList.searchSelection = $.extend(true,{},res.data);
                        //选项数据复制给分发设备组件
                        deviceModalVm.distributeSelection = $.extend(true,{},res.data);
                    }else {
                        console.error(res.msg);
                    }
                }
            });
        }
    },
    created: function () {
        this.getDeviceSelection();
    }
});

//侧边栏
var sideBarVm = new Vue({
    el: "#sidebar",
    data: {
        username: getLocalStorage(STORAGE_KEY.userInfo).username
    },
    methods: {
        listDevice: function (statusId) {
            vueDeviceList.queryParams.statusId = statusId;
            vueDeviceList.listDevice();
        }

    }
});
//注销
var logoutVm = new Vue({
    el: "#logout",
    methods: {
        logout: function () {
            localStorage.clear();
            window.location.href = "login.html";
        }
    }
});