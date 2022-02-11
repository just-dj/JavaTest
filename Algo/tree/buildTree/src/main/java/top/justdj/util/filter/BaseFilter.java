package top.justdj.util.filter;

import top.justdj.util.base.BaseNode;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019/8/22
 * Time: 15:57
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 用来过滤满足条件的节点
 */
public interface BaseFilter {
    /**
     * 对节点进行过滤  使判定结果为真的节点将会被返回
     * @param node
     * @return
     */
    Boolean isSatisfy(BaseNode node);
    
}
