package com.daiwei.common.node;

import lombok.Data;

/**
 * 插件的节点
 * @author david
 *
 */
@Data
public class ZTreeNode {

	private Long id;	//节点id
	
	private Long pId;//父节点id
	
	private String name;//节点名称
	
	private Boolean open;//是否打开节点
	
	private Boolean checked;//是否被选中
	
	//pId 转换到页面会变成pid
	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public ZTreeNode(){
		
	}
	
	public ZTreeNode(Long id,Long pId,String name ,boolean open,boolean checked){
		this.id=id;
		this.pId=pId;
		this.name=name;
		this.open=open;
		this.checked=checked;
	}
	
	public static ZTreeNode createParent(){
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(true);
		zTreeNode.setId(0l);
		zTreeNode.setName("顶级");
		zTreeNode.setOpen(true);
		zTreeNode.setpId(-1l);
		return zTreeNode;
	}
	
	public static ZTreeNode createUncheckParent(){
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(false);
		zTreeNode.setId(0l);
		zTreeNode.setName("顶级");
		zTreeNode.setOpen(true);
		zTreeNode.setpId(-1l);
		return zTreeNode;
	}
}
