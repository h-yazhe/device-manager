function setLocalStorage(key, val) {
    if (typeof val == 'string') {
        localStorage.setItem(key, val);
    } else {
        localStorage.setItem(key, JSON.stringify(val));
    }
}

function getLocalStorage(key) {
    return JSON.parse(localStorage.getItem(key));
}

function sendPost(params) {
    var defaultParams = {
        url: params.url,
        method: 'post',
        contentType: "application/json",
        headers: {
            token: localStorage.getItem(commonVm.storageKey.token)
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

var API = {
    prefix: '/dev-manager/api_v1/',

    login: "login",

    listDevice: "device/list",
    addDevice: "device/add",
    getDeviceSelection: "device/search-selection",

    listBrand: "brand",

    listCategoryByPId: 'list-category-by-pId',

    getApi: function (name) {
        return this.prefix + name;
    }
};

//local storage的key
var STORAGE_KEY = {
    token: "token",
    userInfo: "u_in"
};

var commonVm = new Vue({
    data: {
        //api统一定义
        api: {
            apiPrefix: "",

            login: "login",

            listDevice: "device/list",
            addDevice: "device/add",
            getDeviceSelection: "device/search-selection",

            listBrand: "brand"
        },
        storageKey: {
            token: "token",
            userInfo: "u_in"
        }
    },
    methods: {
        getApi: function (name) {
            return this.api.apiPrefix + name;
        }
    }
});

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

var CategoryTree = {
    name: 'CategoryTree',
    props: ['parent'],
    data: function () {
        return {
            queryPage: new defaultQueryPage()
        }
    },
    template: '<div>\n' +
        '                                <a :class="{active: parent.active}" :style="indent" @click="listChildren"  href="javascript:;" class="list-group-item">\n' +
        '                                    <span v-bind:class="[parent.expanded ?\'glyphicon-chevron-down\':\'glyphicon glyphicon-chevron-right\']" class="glyphicon"></span>{{parent.name}}\n' +
        '                                </a>\n' +
        '                                <CategoryTree v-if="parent.expanded"  v-for="child in parent.children" :parent="child" :key="child.id"></CategoryTree>\n' +
        '                            </div>',
    methods: {
        listChildren: function () {
            var self = this;
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

var SearchDevice = {
    name: 'SearchDevice',
    props: ['selection'],
    data: function () {
        return {
            queryParams: {
                queryPage: new defaultQueryPage(),
                statusId: null,//状态码
                locationId: null,//地点id
                brandId: null,//品牌id
                deviceModelId: null//设备型号id
            }
        }
    },
    template: "<div class=\"row form-group\" style=\"margin-bottom: 0\" id=\"form-group\">\n" +
        "                        <div class=\"col-md-1\">\n" +
        "<select v-model=\"queryParams.locationId\" id=\"input-partition\" class=\"form-control\">\n" +
        "                        <option disabled value=\"-1\">选择地点</option>\n" +
        "<option value=\"null\">全部地点</option>" +
        "                        <option v-for=\"location in selection.locationList\" v-bind:value=\"location.id\">{{location.name}}</option>\n" +
        "                    </select>"+
        "                        </div>\n" +
        "                        <div class=\"col-md-1\">\n" +
        "<select v-model=\"queryParams.brandId\" id=\"input-brand\" class=\"form-control\">\n" +
        "                        <option disabled value=\"null\">请选择</option>\n" +
        "                        <option v-for=\"brand in selection.brandList\" v-bind:value=\"brand.id\">{{brand.name}}</option>\n" +
        "                    </select>" +
        "                        </div>\n" +
        "                        <div class=\"col-md-1\">\n" +
        "                            <select class=\"form-control\">\n" +
        "                                <option>全部型号</option>\n" +
        "                                <option>2</option>\n" +
        "                                <option>3</option>\n" +
        "                                <option>4</option>\n" +
        "                            </select>\n" +
        "                        </div>\n" +
        "                        <div class=\"col-md-1\">\n" +
        "                            <select class=\"form-control\" id=\"device_number\">\n" +
        "                                <option>国资编号</option>\n" +
        "                                <option>2</option>\n" +
        "                                <option>3</option>\n" +
        "                                <option>4</option>\n" +
        "                            </select>\n" +
        "                        </div>\n" +
        "                        <div class=\"col-md-6\">\n" +
        "                            检索关键词：<input type=\"text\" style=\"width: 60%\"/>\n" +
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