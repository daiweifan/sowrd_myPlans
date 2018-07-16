package com.daiwei.common.node;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 左侧菜单信息展示
 * @author david
 *
 */
@Data
public class MenuNode  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 节点id
     */
    public Long id;


    /**
     * 节点名称
     */
    public String name;

    /**
     * 节点的url
     */
    public String url;

    /**
     * 节点图标
     */
    public String icon; 
    
    /**
     * 子节点的集合
     */
    public List<MenuNode> childrens;

	public MenuNode(Long id, String name, String url, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
	}

	public MenuNode(Long id, String name, String url, String icon, List<MenuNode> childrens) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.childrens = childrens;
	}







}
