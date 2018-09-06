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

var commonVm = new Vue({
    data: {
        //api统一定义
        api: {
            apiPrefix: "/dev-manager/api_v1/",
            listDevice: "device/list",
            login: "login"
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