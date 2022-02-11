package top.justdj.util.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019/8/22
 * Time: 13:55
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 树节点基础类
 */

public class BaseNode implements Serializable {
    
    private String id;
    
    private String parentId;
    
    private List <BaseNode> children;
    
    public BaseNode(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
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
    
    public List <BaseNode> getChildren() {
        return children;
    }
    
    public void setChildren(List <BaseNode> children) {
        this.children = children;
    }
    
    @Override
    public String toString() {
        return "BaseNode{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", children=" + children +
                '}';
    }
}
