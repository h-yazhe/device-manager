/**
 * 校验权限，拥有权限返回true
 * @param target 要校验是否存在的权限，若为一个对象，其中一个属性匹配成功即返回ture
 */
function checkPermission(target) {
    var permissionList = getLocalStorage(STORAGE_KEY.userInfo).permissionList;
    return checkPermissionInList(target,permissionList);
}

/**
 * 校验权限，拥有权限返回true
 * @param target 要校验是否存在的权限，若为一个对象，其中一个属性匹配成功即返回ture
 * @param permissionList 权限列表
 */
function checkPermissionInList(target, permissionList) {
    for (var permission of permissionList) {
        if (typeof(target) === 'string'){
            if (permission.permissionCode === target) {
                return true;
            }
        }else {
            for (var item in target){
                if (checkPermissionInList(target[item], permissionList)){
                    return true;
                }
            }
        }
    }
    return false;
}

var PERMISSION_ENUM = {
    SYS_SETTING: {
        USER: {
            USER_ADD: "user:add",
            USER_DELETE: "user:delete",
            USER_UPDATE: 'user:update'
        },
        MENU_MANAGEMENT: {
            LOCATION: {
                LOCATION_ADD: 'location:add',
                LOCATION_UPDATE: 'location:update',
                LOCATION_DELETE: 'location:delete'
            },
            CATEGORY: {
                CATEGORY_ADD: 'category:add',
                CATEGORY_UPDATE: 'category:update',
                CATEGORY_DELETE: 'category:delete'
            },
            WORK_NATURE: {
                WORK_NATURE_ADD: 'nature:add',
                WORK_NATURE_DELETE: 'nature:delete'
            },
            BRAND: {
                BRAND_ADD: 'brand:add',
                BRAND_DELETE: 'brand:delete'
            },
            DEVICE_MODEL: {
                DEVICE_MODEL_ADD: 'model:add'
            }
        }
    },
    DEVICE: {
        DEVICE_ADD: "device:add",
        DEVICE_UPDATE: "device:update",
        DEVICE_DELETE: 'device:delete',
        DEVICE_GET: 'device:get',
        DEVICE_DISTRIBUTE: 'device:distribute',
        DEVICE_DISCARD: 'device:discard'
    }
};
