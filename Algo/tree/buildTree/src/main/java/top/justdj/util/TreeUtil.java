package top.justdj.util;

import top.justdj.util.base.BaseNode;
import top.justdj.util.filter.BaseFilter;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019/8/22
 * Time: 14:00
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 封装了有关树的一些操作
 *      比如将数组转为树(支持多棵树)
 *      树转为数组  (支持多棵树)
 *      遍历搜索树节点
 *      更多功能有待开发
 *      节点需要继承 top.justdj.util.base.BaseNode类 */
public final class TreeUtil {
    
    /**
     * 默认的根节点parentId 为 “0”
     */
    private static final String ROOT_NODE_PARENT_ID = "0" ;
    
    /**
     * 两层循环实现建树 返回多棵树
     * @param tree 节点list
     * @param <T>
     * @return
     */
    public static <T extends BaseNode> List <T> buildMutiRoot(List <T> tree) {
        return buildMutiRoot(tree, ROOT_NODE_PARENT_ID);
    }
    
    /**
     *  返回多棵树
     * @param tree
     * @param rootNodeParentId 根节点parentId值
     * @param <T>
     * @return
     */
    public static <T extends BaseNode> List <T> buildMutiRoot(List <T> tree, String rootNodeParentId) {
        if (collectionIsEmpty(tree))
            return Collections.emptyList();
        
        //trees 里存放所有的根节点
        List trees = new ArrayList <BaseNode>();
        
        for (BaseNode treeNode : tree) {
            // 首先判断是不是根节点 是根节点 加入数组 对于 null 值特殊处理一下
            //第一个==是为了处理rootId为null的情况下
            if (rootNodeParentId == treeNode.getId() || (rootNodeParentId != null && rootNodeParentId.equals(treeNode.getParentId()))) {
                trees.add(treeNode);
            }
            // 这个循环的作用就是找到当前节点的所有子节点
            for (BaseNode it : tree) {
                if (treeNode.getId().equals(it.getParentId())) {
                    // 第一次发现子节点 先创建一个空数组
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList <BaseNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return (List <T>) trees;
    }
    
    /**
     * 返回单棵树
     * @param tree
     * @param <T>
     * @return
     */
    public static <T extends BaseNode> T buildSingleRoot(List <T> tree) {
        return buildSingleRoot(tree, ROOT_NODE_PARENT_ID);
    }
    
    /**
     * 返回单棵树
     * @param tree
     * @param rootNodeParentId 根节点parentId值
     * @param <T>
     * @return
     */
    public static <T extends BaseNode> T buildSingleRoot(List <T> tree, String rootNodeParentId) {
        if (collectionIsEmpty(tree))
            return null;
        
        //根节点
        BaseNode root = null;
        
        for (BaseNode treeNode : tree) {
            // 首先判断是不是根节点 是根节点 加入数组 对于 null 值特殊处理一下
            //第一个==是为了处理rootId为null的情况下
            if (rootNodeParentId == treeNode.getId() || (rootNodeParentId != null && rootNodeParentId.equals(treeNode.getParentId()))) {
                root = treeNode;
            }
            // 这个循环的作用就是找到当前节点的所有子节点
            for (BaseNode it : tree) {
                if (treeNode.getId().equals(it.getParentId())) {
                    // 第一次发现子节点 先创建一个空数组
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList <BaseNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return (T) root;
    }
    
    /**
     * 返回所有满足条件的节点 广度优先
     * @param tree 树
     * @param filter 过滤条件 使返回结果为true的节点将会被返回
     * @param <T>
     * @return
     */
    public static <T extends BaseNode> List <T> findAll(List <T> tree, BaseFilter filter) {
        if (collectionIsEmpty(tree))
            return Collections.emptyList();
        
        Deque <BaseNode> nodeDeque = new LinkedList <BaseNode>();
        BaseNode node;
        List result = new ArrayList <T>();
        for (BaseNode item : tree) {
            nodeDeque.push(item);
        }
        
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.pop();
            if (filter.isSatisfy(node)) {
                result.add(node);
            }
            if (node.getChildren() != null) {
                for (BaseNode treeNode : node.getChildren()) {
                    nodeDeque.addLast(treeNode);
                }
            }
        }
        return result;
    }
    
    /**
     * 返回单个满足条件的节点 广度优先
     * @param tree 树
     * @param filter 过滤条件 使返回结果为true的节点将会被返
     * @param <T>
     * @return
     */
    public static <T extends BaseNode> T findOne(List <T> tree, BaseFilter filter) {
        if (collectionIsEmpty(tree))
            return null;
        
        Deque <BaseNode> nodeDeque = new LinkedList <BaseNode>();
        BaseNode node;
        BaseNode result = null;
        for (BaseNode item : tree) {
            nodeDeque.push(item);
        }
        
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.pop();
            if (filter.isSatisfy(node)) {
                result = node;
                break;
            }
            if (node.getChildren() != null) {
                for (BaseNode treeNode : node.getChildren()) {
                    nodeDeque.addLast(treeNode);
                }
            }
        }
        return (T)result;
    }
    
    /**
     *  将树转化为数组
     * @param tree 树
     * @param isSetChildrenEmpty 是否将子节点置为空
     * @param <T>
     * @return
     */
    public static <T extends BaseNode> List <T> toList(List <T> tree,boolean isSetChildrenEmpty) {
        if (collectionIsEmpty(tree))
            return Collections.emptyList();
        
        Deque <BaseNode> nodeDeque = new LinkedList <BaseNode>();
        BaseNode node;
        List<BaseNode> result = new ArrayList <BaseNode>();
        for (BaseNode item : tree) {
            nodeDeque.push(item);
        }
        
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.pop();
            result.add(node);
            if (node.getChildren() != null) {
                for (BaseNode treeNode : node.getChildren()) {
                    nodeDeque.addLast(treeNode);
                }
            }
        }
        // 如果设置了要把children置为空
        if (isSetChildrenEmpty){
            for (BaseNode item:result ){
                item.setChildren(Collections.EMPTY_LIST);
            }
        }
        return (List <T>)result;
    }
    
    /**
     * 判断集合是否为空
     * @param list
     * @return
     */
    private static boolean collectionIsEmpty(List list) {
        return list == null || list.size() <=0;
    }
    
    
    /**
     * 使用递归方法建树 可能会爆栈 不推荐使用
     * @param tree
     */
    @Deprecated
    public static <T extends BaseNode> List <T> buildByRecursive(List <T> tree) {
        return buildByRecursive(tree, ROOT_NODE_PARENT_ID);
    }
    
    @Deprecated
    public static <T extends BaseNode> List <T> buildByRecursive(List <T> tree, String rootNodeParentId) {
        if (collectionIsEmpty(tree))
            return Collections.emptyList();
        
        List trees = new ArrayList <BaseNode>();
        for (BaseNode treeNode : tree) {
            //第一个==是为了处理rootId为null的情况下
            if (rootNodeParentId == treeNode.getId() || (rootNodeParentId != null && rootNodeParentId.equals(treeNode.getParentId()))) {
                trees.add(treeNode);
            }
        }
        return (List <T>) trees;
    }
    
    /**
     * 递归查找子节点  找到treeNode的所有子节点
     * @param tree
     */
    @Deprecated
    private static <T extends BaseNode> BaseNode findChildren(BaseNode treeNode, List <T> tree) {
        for (BaseNode it : tree) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList <BaseNode>());
                }
                treeNode.getChildren().add(findChildren(it, tree));
            }
        }
        return treeNode;
    }
    
}