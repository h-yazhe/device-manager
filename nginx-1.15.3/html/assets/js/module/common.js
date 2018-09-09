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
var defaultQueryPage = {
    pageNum: 1,
    pageSize: 10
};

//构造category对象的初始参数
var initCategory = function (category) {
    category.active = false;
    category.expanded = false;
    category.children = [];
};

var CategoryTree = {
    name: 'CategoryTree',
    props: ['parent'],
    data: function () {
        return {
            queryPage: defaultQueryPage
        }
    },
    template: '<div>\n' +
        '                                <a :class="{active: parent.active}" :style="indent" @click="listChildren"  href="javascript:;" class="list-group-item">\n' +
        '                                    <span v-bind:class="[parent.expanded ?\'glyphicon-chevron-down\':\'glyphicon-minus\']" class="glyphicon"></span>{{parent.name}}\n' +
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
            })
        }
    },
    computed: {
        indent: function () {
            var level = this.parent.level;
            return {transform: 'translate(' + (level > 1 ? (level - 1) : 0) * 8 + '%)'}
        }
    }
};