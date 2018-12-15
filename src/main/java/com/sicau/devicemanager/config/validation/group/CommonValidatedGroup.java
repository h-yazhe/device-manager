package com.sicau.devicemanager.config.validation.group;

public interface CommonValidatedGroup {
    //验证分成两部分：1.非空验证(其他group)，2.判断一个字段是否符合格式要求(LegalityGroup);格式要求是一致的
    public interface LegalityGroup{}
}
