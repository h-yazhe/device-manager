package com.sicau.devicemanager.util.tree;


import java.util.List;

/**
 * @author Yazhe
 * Created at 11:42 2018/8/1
 */
public class BasicTreeNodeDTO {

    private String id;

    private List<BasicTreeNodeDTO> children;

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

    public BasicTreeNodeDTO() {
    }

    public BasicTreeNodeDTO(String id, List<BasicTreeNodeDTO> children, String value, Integer level, String path) {
        this.id = id;
        this.children = children;
        this.value = value;
        this.level = level;
        this.path = path;
    }

    public static BasicTreeNodeDTO getInstance() {
        return new BasicTreeNodeDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BasicTreeNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<BasicTreeNodeDTO> children) {
        this.children = children;
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
