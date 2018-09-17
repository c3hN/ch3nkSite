//package com.ch3nk.ch3nkSite.modules.test;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestList {
//
//    @Test
//    public void test() {
//        ArrayList<SysDepartment> list = new ArrayList<>();
//        SysDepartment department1 = new SysDepartment();
//        SysDepartment department2 = new SysDepartment();
//        SysDepartment department3 = new SysDepartment();
//        SysDepartment department4 = new SysDepartment();
//        SysDepartment department5 = new SysDepartment();
//        department1.setDeptId("111");
//        department2.setDeptId("222");
//        department3.setDeptId("333");
//        department3.setParentId("111");
//        department4.setDeptId("444");
//        department4.setParentId("111");
//        department5.setDeptId("555");
//        department5.setParentId("333");
//        list.add(department1);
//        list.add(department2);
//        list.add(department3);
//        list.add(department4);
//        list.add(department5);
//
//        List<SysDepartment> build = buildByRecursive(list);
//        System.out.println(build);
//    }
//
//    /**
//     * 递归查找子节点
//     * @param department
//     * @param list
//     * @return
//     */
//    public SysDepartment findChildren(SysDepartment department,List<SysDepartment> list) {
//        for (SysDepartment it : list) {
//            if(department.getDeptId().equals(it.getParentId())) {
//                if (department.getChildren() == null) {
//                    department.setChildren(new ArrayList<SysDepartment>());
//                }
//                department.getChildren().add(findChildren(it,list));
//            }
//        }
//        return department;
//    }
//
//    /**
//     * 使用递归方法建树
//     * @param list
//     * @return
//     */
//    public List<SysDepartment> buildByRecursive(List<SysDepartment> list) {
//        List<SysDepartment> trees = new ArrayList<SysDepartment>();
//        for (SysDepartment department : list) {
//            if (StringUtils.isEmpty(department.getParentId())) {
//                trees.add(findChildren(department,list));
//            }
//        }
//        return trees;
//    }
//}
