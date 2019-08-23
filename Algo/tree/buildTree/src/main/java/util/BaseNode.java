package util;

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
@Data
public class BaseNode implements Serializable {
    
    private String id;
    
    private String parentId;
    
    private List <BaseNode> children;
    
    public BaseNode(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
    }
}
