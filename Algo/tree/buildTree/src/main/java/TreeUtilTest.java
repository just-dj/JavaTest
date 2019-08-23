import com.alibaba.fastjson.JSON;
import top.justdj.model.DepartmentInfo;
import top.justdj.util.TreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019/8/22
 * Time: 10:49
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc
 */
public class TreeUtilTest {
    
    
    private static List <DepartmentInfo> getMutiRootTree() {
        DepartmentInfo treeNode1 = new DepartmentInfo("1", "广州", "0");
        DepartmentInfo treeNode2 = new DepartmentInfo("2", "深圳", "0");
        
        DepartmentInfo treeNode3 = new DepartmentInfo("3", "天河区", treeNode1);
        DepartmentInfo treeNode4 = new DepartmentInfo("4", "越秀区", treeNode1);
        DepartmentInfo treeNode5 = new DepartmentInfo("5", "黄埔区", treeNode1);
        DepartmentInfo treeNode6 = new DepartmentInfo("6", "石牌", treeNode3);
        DepartmentInfo treeNode7 = new DepartmentInfo("7", "百脑汇", treeNode6);
        
        DepartmentInfo treeNode8 = new DepartmentInfo("8", "南山区", treeNode2);
        DepartmentInfo treeNode9 = new DepartmentInfo("9", "宝安区", treeNode2);
        DepartmentInfo treeNode10 = new DepartmentInfo("10", "科技园", treeNode8);
        
        List <DepartmentInfo> list = new ArrayList <DepartmentInfo>();
        
        list.add(treeNode1);
        list.add(treeNode2);
        list.add(treeNode3);
        list.add(treeNode4);
        list.add(treeNode5);
        list.add(treeNode6);
        list.add(treeNode7);
        list.add(treeNode8);
        list.add(treeNode9);
        list.add(treeNode10);
        return list;
    }
    
    private static List <DepartmentInfo> getSingleRootTree() {
        DepartmentInfo treeNode1 = new DepartmentInfo("1", "广州", "0");
        DepartmentInfo treeNode3 = new DepartmentInfo("3", "天河区", treeNode1);
        DepartmentInfo treeNode4 = new DepartmentInfo("4", "越秀区", treeNode1);
        DepartmentInfo treeNode5 = new DepartmentInfo("5", "黄埔区", treeNode1);
        DepartmentInfo treeNode6 = new DepartmentInfo("6", "石牌", treeNode3);
        DepartmentInfo treeNode7 = new DepartmentInfo("7", "百脑汇", treeNode6);
        
        List <DepartmentInfo> list = new ArrayList <DepartmentInfo>();
        
        list.add(treeNode1);
        list.add(treeNode3);
        list.add(treeNode4);
        list.add(treeNode5);
        list.add(treeNode6);
        list.add(treeNode7);
        return list;
    }
    
    public static void main(String[] args) {
        List <DepartmentInfo> list = getMutiRootTree();
       
        
        // 数组转为树
//        List <DepartmentInfo> tree1 = TreeBuilderUtil.buildMutiRoot(list);
//        System.out.println(JSON.toJSONString(tree1));
        
        // 查找树中元素
//        List <DepartmentInfo> result = TreeBuilderUtil.findAll(tree1, (temp) -> {
//            DepartmentInfo departmentInfo = (DepartmentInfo) temp;
//            if (departmentInfo.getName().equals("黄埔区")) {
//                return true;
//            }
//            return false;
//        });
        
        // 将树转化为数组
//        System.out.println(JSON.toJSONString(TreeBuilderUtil.toList(tree1, true)));
    
    
    
        List <DepartmentInfo> singleTree = getMutiRootTree();
        System.out.println(JSON.toJSONString(TreeUtil.buildSingleRoot(singleTree)));
        
    }
    
}
