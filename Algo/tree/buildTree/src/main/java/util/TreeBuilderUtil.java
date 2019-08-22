package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019/8/22
 * Time: 14:00
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 封装了有关树的一些操作 比如将数组转为树  更多功能有待开发
 */
public final class TreeBuilderUtil {
    
    /**
     * 默认的根节点id 为 “0”
     */
    private static String rootId = "0";
    
    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     */
    public static <T extends BaseNode> List <T> bulid(List<T> treeNodes) {
        return bulid(treeNodes,rootId);
    }
    
    public static <T extends BaseNode> List <T> bulid(List<T> treeNodes,String rootId) {
        //trees 里存放所有的根节点
        List<BaseNode> trees = new ArrayList <BaseNode>();
        
        for (BaseNode treeNode : treeNodes) {
            // 首先判断是不是根节点 是根节点 加入数组 对于 null 值特殊处理一下
            boolean isNullAndEqual = (rootId == null && treeNode.getId() == null);
            if ( isNullAndEqual || rootId.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            // 这个循环的作用就是找到当前节点的所有子节点
            for (BaseNode it : treeNodes) {
                if (treeNode.getId().equals(it.getParentId())) {
                    // 第一次发现子节点 先创建一个空数组
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<BaseNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return  (List<T>)trees;
    }
    
    /**
     * 使用递归方法建树
     * @param treeNodes
     */
    public static <T extends BaseNode> List<T> buildByRecursive(List<T> treeNodes) {
       return buildByRecursive(treeNodes,rootId);
    }
    
    public static <T extends BaseNode> List<T> buildByRecursive(List<T> treeNodes,String rootId) {
        List<BaseNode> trees = new ArrayList<BaseNode>();
        for (BaseNode treeNode : treeNodes) {
            boolean isNullAndEqual = (rootId == null && treeNode.getId() == null);
            if ( isNullAndEqual || rootId.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
        }
        return (List<T>)trees;
    }
    
    /**
     * 递归查找子节点  找到treeNode的所有子节点
     * @param treeNodes
     */
    private static <T extends BaseNode> BaseNode findChildren(BaseNode treeNode, List<T> treeNodes) {
        for (BaseNode it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<BaseNode>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
    
    
}
