package top.justdj.model;

import lombok.Data;
import top.justdj.util.base.BaseNode;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019/8/22
 * Time: 10:52
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc
 */
@Data
public class DepartmentInfo extends BaseNode {
    
    /**
     * 自定额外的属性
     */
    private String name;
    
    
    public DepartmentInfo(String id, String name, String parentId) {
        super(id, parentId);
        this.name = name;
    }
    
    public DepartmentInfo(String id, String name, DepartmentInfo parent) {
        super(id, parent.getId());
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "DepartmentInfo{" +
                "id='" + getId() + '\'' +
                ", parentId='" + getParentId() + '\'' +
                ", children='" + getChildren() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
