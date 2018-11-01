package com.sicau.devicemanager.util.tree;


/**
 * 树的节点
 * @author Yazhe
 * Created at 11:03 2018/8/1
 */
public class BasicTreeNode {

    private String id;

    private String parentId;

    /**
     * 该节点的值
     */
    private String value;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 路径
     */
    private String path;

    public BasicTreeNode() {
    }

    public BasicTreeNode(String id, String parentId, String value, Integer level, String path) {
        this.id = id;
        this.parentId = parentId;
        this.value = value;
        this.level = level;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
