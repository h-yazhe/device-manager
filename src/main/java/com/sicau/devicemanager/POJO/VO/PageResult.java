package com.sicau.devicemanager.POJO.VO;

import lombok.Data;

import java.util.List;

/**
 * @author Yazhe
 * Created at 14:16 2019/4/9
 */
@Data
public class PageResult<T> {

	private Long total;

	private List<T> list;
}
