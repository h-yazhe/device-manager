//设置本地存储的值
function setLocalStorage(key, val) {
    if (typeof val == 'string') {
        localStorage.setItem(key, val);
    } else {
        localStorage.setItem(key, JSON.stringify(val));
    }
}
//获取本地存储的值
function getLocalStorage(key) {
    return JSON.parse(localStorage.getItem(key));
}

//格式化时间

/**
 * 发送api请求
 * 重载$.ajax()方法，method默认为post，请求数据类型为json，header中添加了token
 * @param params 同$.ajax()的参数
 */
function sendPost(params) {
    var defaultParams = {
        url: params.url,
        method: 'post',
        contentType: "application/json",
        headers: {
            token: localStorage.getItem(STORAGE_KEY.token)
        },
        data: params.data
    };
    $.extend(defaultParams, params);
    $.ajax(defaultParams);
    this.success = function (res) {
        if (res.code == 0) {
            alert(res.msg);
        } else {
            alert(res.msg);
        }
    };
    this.error = function (res) {
        var resBody = res.responseBody;
        alert(resBody.msg);
    }
}
//api统一定义
var API = {
    prefix: '/dev-manager/api_v1/',
    //登录
    login: "login",
    //查询设备列表
    listDevice: "device/list",
    //添加设备
    addDevice: "device/add",
    //获取添加设备的选项卡数据
    getDeviceSelection: "device/search-selection",
    //分发设备
    distributeDevice: "device/distribute",
    //报废设备
    discardDevice: "device/discard",
    //查询品牌列表
    listDeviceBrand:"get-brands",
    //添加品牌
    addDeviceBrand:"brand",
    //删除品牌
    deleteBrand:"delete-brand/",
    //根据父id查询子分类
    listCategoryByPId: 'list-category-by-pId',
    //分页查询所有用户
    ListUser:"user/list-by-page",
    //添加用户
    addUser:"user/add",
    //删除用户
    deleteUser:"user/delete/",
    //查询用户角色
    getUserSelection: "role/list-by-page",
    //根据父id查询地点
    addressDevice:"list-location-by-pid",
    //删除分类
    deleteCategory:"delete-category-by-id/",
    //根据父id插入一个子分类
    insertCategory:"insert-category-by-pid",
    AddAddress:"insert-location-by-pid",
    //删除该节点为根的地点树
    DeleteAddress:"delete-location-tree-by-Id",
    DeviceRecord:"device/get-status-record-by-deviceId",
    //查询设备型号
    listDeviceModel:"device-model-listAll",
    //添加设备型号
    addDeviceModel:"/device-model-submit",
    //删除设备型号
    deleteDeviceModel:"/device-model-delete/",
    //查询工作性质
    listWorkNature:"work_nature/listAll",
    //添加工作性质
    addWorkNature:"work_nature/add",
    //删除工作性质
    deleteWorkNature:"work_nature/delete/",
    getApi: function (name) {
        return this.prefix + name;
    }
};

//local storage的key
var STORAGE_KEY = {
    token: "token",
    userInfo: "u_in"
};

//默认分页参数
var defaultQueryPage = function () {
    this.pageNum = 1;
    this.pageSize = 20;
};
//构造category对象的初始参数
var initCategory = function (category) {
    category.active = false;
    category.expanded = false;
    category.children = [];
};

//设备搜索的参数
var searchDeviceParams= {
    queryPage: new defaultQueryPage(),
    workNatureId: null,//状态码
    locationId: null,//地点id
    brandId: null,//品牌id
    deviceModelId: null//设备型号id
};
//设备搜索，添加设备的选项卡数据
var deviceSearchSelection = {
    categoryList: {
        id: '',
        name: '',
        level: 1
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
            id: '',
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
};

/**
 * 格式化时间
 * @param timestamp 时间戳
 * @returns {string}
 */
function formatTime(timestamp) {
    var date = new Date(timestamp);
    return date.getFullYear() + "." + date.getMonth() + "." + date.getDay();
}
//设备分类树组件
var CategoryTree = {
    name: 'CategoryTree',
    props: ['parent','index'],
    data: function () {
        return {
            queryPage: new defaultQueryPage()
        }
    },
    template: '<div>\n' +
        '                                <a :class="{active: parent.active}" :style="indent" @click="listChildren"  href="javascript:;" class="list-group-item" >\n' +
        '                                    <span v-bind:class="[parent.expanded ?\'glyphicon-chevron-down\':\'glyphicon glyphicon-chevron-right\']" class="glyphicon"></span>{{parent.name}}\n' +
        '                                </a>\n' +
        '                                <CategoryTree v-if="parent.expanded"  v-for="child in parent.children" :parent="child" :key="child.id"></CategoryTree>\n' +
        '                            </div>',
    methods: {
        listChildren: function () {
            var self = this;
            if(vueDeviceList.isSort==true){
                sideBarVm.sortList(self.parent.id);
                vueDeviceList.getValue(self.parent.id,self.parent.name)
            }
            if (self.parent.expanded) {
                self.parent.expanded = !self.parent.expanded;
                return;
            }
            sendPost({
                url: API.getApi(API.listCategoryByPId),
                data: JSON.stringify({
                    queryPage: self.queryPage,
                    parentId: self.parent.id
                }),
                success: function (res) {
                    if (res.code === 0){
                        for (var item of res.data){
                            initCategory(item);
                        }
                        self.parent.children = res.data;
                        self.parent.expanded = !self.parent.expanded;
                    }
                }
            });
            //设置设备查询参数，重新渲染表格数据
            vueDeviceList.queryParams.categoryId = self.parent.id;
            vueDeviceList.listDevice();
        }
    },
    computed: {
        indent: function () {
            var level = this.parent.level;
            return {transform: 'translate(' + (level > 1 ? (level - 1) : 0) * 8 + '%)'}
        }
    }
};
//地点分类树组件
var AddressTree = {
    name: 'AddressTree',
    props: ['parent','index'],
    data: function () {
        return {
            queryPage: new defaultQueryPage()
        }
    },
    template: '<div>\n' +
        '                                <a :class="{active: parent.active}" :style="indent" @click="listChildren"  href="javascript:;" class="list-group-item">\n' +
        '                                    <span v-bind:class="[parent.expanded ?\'glyphicon-chevron-down\':\'glyphicon glyphicon-chevron-right\']" class="glyphicon"></span>{{parent.name}}\n' +
        '                                </a>\n' +
        '                                <AddressTree v-if="parent.expanded"  v-for="child in parent.children" :parent="child" :key="child.id"></AddressTree>\n' +
        '                            </div>',
    methods: {
        listChildren: function () {
            var self = this;
            if (self.parent.expanded) {
                self.parent.expanded = !self.parent.expanded;
                return;
            }
            sendPost({
                url: API.getApi(API.addressDevice),
                data: JSON.stringify({
                    "parentId": "",
                    "queryPage": {
                        "pageNum": 1,
                        "pageSize": 10
                    }
                }),
                success: function (res) {
                    if (res.code === 0){
                        for (var item of res.data){
                            initCategory(item);
                        }
                        self.parent.children = res.data;
                        self.parent.expanded = !self.parent.expanded;
                    }
                }
            });
            //设置设备查询参数，重新渲染表格数据
            vueDeviceList.addressDevice();
        }
    },
    computed: {
        indent: function () {
            var level = this.parent.level;
            return {transform: 'translate(' + (level > 1 ? (level - 1) : 0) * 8 + '%)'}
        }
    }
};

//搜索设备组件
var SearchDevice = {
    name: 'SearchDevice',
    props: ['selection'],
    data: function () {
        return {
            queryParams: $.extend(true,{},searchDeviceParams)
        }
    },
    template: "<div class=\"row form-group\" style=\"margin-bottom: 0\" id=\"form-group\">\n" +
        "                        <div class=\"col-md-2\">\n" +
        "<select v-model=\"queryParams.locationId\" id=\"input-partition\" class=\"form-control\">\n" +
        "<option :value=\"null\">全部地点</option>" +
        "                        <option v-for=\"location in selection.locationList\" v-bind:value=\"location.id\">{{location.name}}</option>\n" +
        "                    </select>"+
        "                        </div>\n" +
        "                        <div class=\"col-md-2\">\n" +
        "<select v-model=\"queryParams.brandId\" id=\"input-brand\" class=\"form-control\">\n" +
        "                        <option :value=\"null\">全部品牌</option>\n" +
        "                        <option v-for=\"brand in selection.brandList\" v-bind:value=\"brand.id\">{{brand.name}}</option>\n" +
        "                    </select>" +
        "                        </div>\n" +
        "                        <div class=\"col-md-2\">\n" +
        "                           <select v-model=\"queryParams.deviceModelId\" class=\"form-control\">\n" +
        "                        <option :value=\"null\">全部型号</option>\n" +
        "                        <option v-for=\"deviceModel in selection.deviceModelList\" v-bind:value=\"deviceModel.id\">{{deviceModel.name}}</option>\n" +
        "                    </select>"+
        "                        </div>\n" +
        "                        <div class=\"col-md-2\">\n" +
        "                            <select v-model=\"queryParams.workNatureId\" class=\"form-control\">\n" +
        "                        <option :value=\"null\">全部工作性质</option>\n" +
        "                        <option v-for=\"workNature in selection.workNatureList\" v-bind:value=\"workNature.id\">{{workNature.name}}</option>\n" +
        "                    </select>"+
        "                        </div>\n" +
        "                        <div class=\"col-md-5\">\n" +
        "                            检索关键词：<input v-model=\"queryParams.queryKey\" type=\"text\" style=\"width: 40%\" placeholder=\"从设备id，序列号，名称中检索\"/>\n" +
        "                            <button @click=\"searchDevice\" type=\"button\" class=\"btn btn-success\">\n" +
        "                                查询\n" +
        "                            </button>\n" +
        "                            <button class=\"btn btn-warning\">\n" +
        "                                清除查询条件\n" +
        "                            </button>\n" +
        "                        </div>\n" +
        "                    </div>",
    methods: {
        searchDevice: function () {
            this.$parent.listDevice();
        }
    },
    created: function () {
        //传递参数到父组件
        this.$parent.queryParams = this.queryParams;
    }
};
//分发设备组件
var DistributeDevice = {
    name: 'distribute-device',
    props: ['distributeParam','selection'],
    data: function () {
        return {
        }
    },
    methods: {
        //分发设备
        distributeDevice: function () {
            var self = this;
            sendPost({
                url: API.getApi(API.distributeDevice),
                data: JSON.stringify(self.distributeParam),
                success: function (res) {
                    if (res.code === 0) {
                        alert("设备" + self.distributeParam.deviceIdList + "分发成功！");
                    } else {
                        alert(res.msg);
                    }
                },
                error: function (res) {
                    alert("网络异常！");
                }
            });
            //分发完成关闭模态框并刷新设备列表
            $('#distribute-device-modal').modal('toggle');
            vueDeviceList.listDevice();
        }
    },
    template: '<!--分发设备模态框-->\n' +
        '<div id="distribute-device-modal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="distribute-device">\n' +
        '    <div class="modal-dialog modal-lg" role="document">\n' +
        '        <div class="modal-header">\n' +
        '            <h2>分发设备</h2>\n' +
        '        </div>\n' +
        '        <div class="modal-body">\n' +
        '            <label for="distribute-location">请选择分发的部门</label>\n' +
        '            <select v-model="distributeParam.locationId" id="distribute-location" class="form-control">\n' +
        '                <option disabled :value="null">请选择</option>\n' +
        '                <option v-for="location in selection.locationList" v-bind:value="location.id">{{location.name}}</option>\n' +
        '            </select>\n' +
        '        </div>\n' +
        '<div class="modal-footer">\n' +
        '            <button @click="distributeDevice" type="button" class="btn btn-success">确定</button>\n' +
        '            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>\n' +
        '        </div>'+
        '    </div>\n' +
        '</div>'
};


//报废设备组件
var DiscardDevice = {
    name: 'discard-device',
    props: ['discardParams'],
    methods: {
        discard: function () {
            var self = this;
            sendPost({
                url: API.getApi(API.discardDevice),
                data: self.discardParams.deviceId,
                success: function (res) {
                    if (res.code === 0){
                        alert("设备" + self.discardParams.deviceId + "已报废！");
                        $('#discard-device-modal').modal('toggle');
                        vueDeviceList.listDevice();
                    } else {
                        alert(res.msg);
                    }
                },
                error: function (res) {
                    alert("网络异常！");
                }
            });
        }
    },
    template: '<div id="discard-device-modal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="distribute-device">\n' +
        '    <div class="modal-dialog modal-lg" role="document">\n' +
        '        <div class="modal-body">\n' +
        '         <h2>确定报废id为 {{discardParams.deviceId}} 的设备吗？</h2>   '+
        '        </div>\n' +
        '<div class="modal-footer">\n' +
        '            <button @click="discard" type="button" class="btn btn-success">确定</button>\n' +
        '            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>\n' +
        '        </div>'+
        '    </div>\n' +
        '</div>'
};

