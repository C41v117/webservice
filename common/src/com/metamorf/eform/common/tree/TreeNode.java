package com.metamorf.eform.common.tree;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private List<? extends TreeNode> children;        // this list of child only applied for ROOT node,
     // non ROOT node no need to set this, leave as null
	private String parentValue;
	private String description;    // for ALT attribute in tree view
	private String name;
	private String value; 
	private String path;
	
	public boolean hasChild() {
		return (children != null && children.size() > 0);
	}
	
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}
	
	public String getParentValue() {
		return parentValue;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setChildren(List<? extends TreeNode> childs) {
		this.children = childs;
	}
	
	public List<? extends TreeNode> getChildren() {
		return children;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String string) {
		description = string;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String toString() {
		return String.format("WorklistTreeNode(name=%s,value=%s,parent=%s,children=%s)", this.name, this.value, this.parentValue, this.children);
	}
	
	/**
	 * Write the javascript dynatree for the given TreeNodes to the given Out stream writer (usually called from jsp)
	 * @param nodes the list of TreeNodes
	 * @param autoExpand if true will expand the tree on the first loading
	 * @param out the Writer object to which the output string will be streamed
	 * @throws java.io.IOException
	 */
	public static void printNode(ArrayList<TreeNode> nodes, boolean autoExpand, Writer out)
		throws java.io.IOException {
		for (int i = 0; i < nodes.size(); i++) {
			com.metamorf.eform.common.tree.TreeNode settlementCategoryTree =
				(com.metamorf.eform.common.tree.TreeNode) nodes.get(i);
			out.write("tree.enableUpdate(false);\n");
			if(settlementCategoryTree.getParentValue()=="-1"){
				out.write(new StringBuilder()
								.append("rootNode.addChild({ ")
								.append("title: \"").append(settlementCategoryTree.getName()).append("\", ")
								.append("tooltip: \"").append(settlementCategoryTree.getName()).append("\", ")
								.append("isFolder: true, ")
								.append("expand: true, ")
								//.append("url :\"").append(settlementCategoryTree.getPath()).append("\", ")
								.append("key: \"").append(settlementCategoryTree.getValue()).append("\", ")
								.append("descr: \"").append(settlementCategoryTree.getDescription()).append("\", ")
								.append("icon: \"base.gif\" ")
								.append("});\n").toString()
							);
				//out.println("alert(1 + \"-\" + rootNode);");
			}
			else{
				out.write(new StringBuilder()
								.append("parentNode = tree.getNodeByKey(\"")
								.append(settlementCategoryTree.getParentValue())
								.append("\");\n").toString()
							);
				//out.println("alert(\""+settlementCategoryTree.getName()+"\")");
				//out.println("alert(2 + \"-\" + parentNode);");
				out.write(new StringBuilder()
								.append("parentNode.addChild({ ")
								.append("title: \"").append(settlementCategoryTree.getName()).append("\", ")
								.append("tooltip: \"").append(settlementCategoryTree.getName()).append("\", ")
								.append("isFolder: false, ")
								.append("url :\"").append(settlementCategoryTree.getPath()).append("\", ")
								.append("key: \"").append(settlementCategoryTree.getValue()).append("\", ")
								.append("descr: \"").append(settlementCategoryTree.getDescription()).append("\" ")
								.append("});\n").toString()
						   );
				out.write("parentNode.data.isFolder = true;\n");
			}
			out.write("tree.enableUpdate(true);\n");
		}
	}
}
