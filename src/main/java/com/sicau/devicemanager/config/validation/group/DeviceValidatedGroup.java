package com.sicau.devicemanager.config.validation.group;

/**
 * @author Yazhe
 * Created at 15:23 2018/8/7
 */
//验证分成两部分：1.非空验证(此处group)，2.判断一个字段是否符合格式要求(LegalityGroup);格式要求是一致的
public class DeviceValidatedGroup {

    public interface InsertDeviceCategoryGroup {
    }

    public interface AddDeviceGroup {
    }

    public interface UpdateDeviceGroup {
    }

    public interface QueryDeviceGroup {
    }

    public interface DistributeDeviceGroup {
    }

    public interface GetDeviceStatusRecordByDeviceId {
    }

    public interface InsertTree {
    }

    public interface ListTreeByPId {
    }

    public interface InsertTreeByPId {
    }

    public interface Login {
    }

    public interface SubmitRepairOrder {
    }

    public interface ModifyRepairOrder {
    }

    public interface AdminFinishOrder {
    }

    public interface UserFinishOrder {
    }

    public interface UpdateRepairedStatusByDeviceId {

    }

    public interface addWorkNature {

    }

    public interface addDeviceModel {
    }

    public interface AddBrandGroup{
	}

	public interface GetBrandsGroup{}

	//添加用户
	public interface addUser{}

	//修改用户
    public interface modifyUser{}
}
