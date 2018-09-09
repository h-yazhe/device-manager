var vueDeviceList = new Vue({
    el: "#device-list",
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
        pageNum: 1,
        pageSize: 20,
        pages: 1,//总页数
        total: 0,//总条数
        disableLastPage: true,
        disableNextPage: true
    },
    methods: {
        /**
         * 渲染表格
         */
        listDevice: function () {
            var vue = this;
            sendPost({
                url: API.getApi(API.listDevice),
                data: JSON.stringify({
                    "queryPage": {
                        "pageNum": vue.pageNum,
                        "pageSize": vue.pageSize
                    }
                }),
                success: function (res) {
                    var data = res.data;
                    if (res.code == 0) {
                        vueDeviceList.deviceList = data.list;
                        vueDeviceList.total = data.total;
                        vueDeviceList.pages = data.pages;
                        if (vueDeviceList.pageNum != data.pages) {
                            vueDeviceList.disableNextPage = false;
                        } else {
                            vueDeviceList.disableNextPage = true;
                        }
                        if (vueDeviceList.pageNum > 1) {
                            vueDeviceList.disableLastPage = false;
                        } else {
                            vueDeviceList.disableLastPage = true;
                        }
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
            if (this.pageNum > 1) {
                this.pageNum--;
                this.listDevice();
            }
        },
        //翻到下一页
        nextPage: function () {
            if (this.pages > this.pageNum) {
                this.pageNum++;
                this.listDevice();
            }
        },
        //格式化时间
        formatTime: function (timeStamp) {
            var date = new Date(timeStamp);
            return date.getFullYear() + "." + date.getMonth() + "." + date.getDay();
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
        }
    },
    created: function () {
        this.listDevice(this);
    }
});

/**
 * 添加设备组件
 * @type {*|Vue}
 */
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
        '                            <CategoryTree v-for="item in categoryList" :parent="item" :key="item.id"></CategoryTree>\n' +
        '                        </div>'
});
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
            "locationId": "0",
            "name": "",
            "nationalId": "",
            "serialNumber": "",
            "unitPrice": 0,
            "workNatureId": "1"
        },
        brandPage: {
            pageNum: 1,
            pageSize: 10
        },
        brandList: [
            {
                id: '',
                name: ''
            }
        ],
        deviceModelPage: {
            pageNum: 1,
            pageSize: 10
        },
        deviceModelList: [
            {
                id: 0,
                name: ''
            }
        ],
        locationPageNum: {
            pageNum: 1,
            pageSize: 10
        },
        locationList: [
            {
                id: '0',
                name: ''
            }
        ],
        workNaturePage: {
            pageNum: 1,
            pageSize: 10
        },
        workNatureList: [
            {
                id: '',
                name: ''
            }
        ],
        custodianPage: {
            pageNum: '',
            pageSize: ''
        },
        custodianList: [
            {
                id: '',
                name: ''
            }
        ]
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
                    }else {
                        alert(res.msg);
                    }
                }
            });
        },
        getDeviceSelection: function () {
            var self = this;
            sendPost({
                url: API.getApi(API.getDeviceSelection),
                success: function (res) {
                    if (res.code === 0){
                        var categoryList = res.data.categoryList;
                        for (var category of categoryList){
                            initCategory(category);
                        }
                        categoryVm.categoryList = categoryList;
                        //渲染选项卡数据
                        self.brandList = res.data.brandList;
                        self.deviceModelList = res.data.deviceModelList;
                        self.locationList = res.data.locationList;
                        self.workNatureList = res.data.workNatureList;
                        self.custodianList = res.data.custodianList
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

//侧边栏，注销
var sideBarVm = new Vue({
    el: "#sidebar",
    data: {
        username: getLocalStorage(STORAGE_KEY.userInfo).username
    }
});

var logoutVm = new Vue({
    el: "#logout",
    methods: {
        logout: function () {
            localStorage.clear();
            window.location.href = "login.html";
        }
    }
});