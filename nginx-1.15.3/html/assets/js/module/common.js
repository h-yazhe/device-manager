function setLocalStorage(key, val) {
    if (typeof val == 'string'){
        localStorage.setItem(key,val);
    }else {
        localStorage.setItem(key,JSON.stringify(val));
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
    $.extend(defaultParams,params);
    $.ajax(defaultParams);
    this.success = function (res) {
        if (res.code == 0){
            alert(res.msg);
        }else {
            alert(res.msg);
        }
    };
    this.error = function (res) {
        var resBody = res.responseBody;
        alert(resBody.msg);
    }
}

var commonVm = new Vue({
    data: {
        //api统一定义
        api: {
            apiPrefix: "/dev-manager/api_v1/",

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
    methods:{
        getApi: function (name) {
            return this.api.apiPrefix + name;
        }
    }
});