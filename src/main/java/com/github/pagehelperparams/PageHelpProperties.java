package com.github.pagehelperparams;

import java.util.ArrayList;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.github.pagehelper.PageHelper;

/**
 * TODO {@link PageHelper pageHelper 自动配置分页参数，配置类}
 * 
 * @author huangYu
 * @version 1.0
 * @createTime 2018年12月18日 下午4:05:46
 */
@ConfigurationProperties(prefix = PageHelpProperties.PAGE_HELP_AUTO_PARAMS)
public class PageHelpProperties {

	/**
	 * 配置前缀
	 */
	public static final String PAGE_HELP_AUTO_PARAMS = "pageHelpParams";

	/**
	 * 默认的pageNo
	 */
	private Integer defaultPageNo = 0;

	/**
	 * 默认的pageSize
	 */
	private Integer defaultPageSize = 10;

	/**
	 * pageNo参数名称list
	 */
	private ArrayList<String> pageNoParams = new ArrayList<>();

	/**
	 * pageSize参数名称list
	 */
	private ArrayList<String> pageSizeParams = new ArrayList<>();

	public PageHelpProperties() {
		this.pageNoParams.add("pageNo");
		this.pageNoParams.add("pageNum");

		this.pageSizeParams.add("pageSize");
	}

	public Integer getDefaultPageNo() {
		return defaultPageNo;
	}

	public void setDefaultPageNo(Integer defaultPageNo) {
		this.defaultPageNo = defaultPageNo;
	}

	public Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(Integer defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public ArrayList<String> getPageNoParams() {
		return pageNoParams;
	}

	public void setPageNoParams(ArrayList<String> pageNoParams) {
		this.pageNoParams = pageNoParams;
	}

	public ArrayList<String> getPageSizeParams() {
		return pageSizeParams;
	}

	public void setPageSizeParams(ArrayList<String> pageSizeParams) {
		this.pageSizeParams = pageSizeParams;
	}

}
