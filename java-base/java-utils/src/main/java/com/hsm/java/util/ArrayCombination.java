package com.hsm.java.util;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/8/12 20:08
 */
import java.util.ArrayList;
import java.util.List;

public class ArrayCombination {

    public static List<Object[]> combination(List<Object[]> dataList, int index, List<Object[]> resultList){
        if(index==dataList.size()){
            return resultList;
        }

        List<Object[]> resultList0=new ArrayList<Object[]>();
        if(index==0){
            Object[] objArr=dataList.get(0);
            for(Object obj : objArr){
                resultList0.add(new Object[]{obj});

            }
        }else{
            Object[] objArr=dataList.get(index);
            for(Object[] objArr0: resultList){
                for(Object obj : objArr){
                    //复制数组并扩充新元素
                    Object[] objArrCopy=new Object[objArr0.length+1];
                    System.arraycopy(objArr0, 0, objArrCopy, 0, objArr0.length);
                    objArrCopy[objArrCopy.length-1]=obj;

                    //追加到结果集
                    resultList0.add(objArrCopy);
                }
            }
        }
        return combination(dataList,++index,resultList0);
    }

    public static void main(String[] args) {
        Object[] arr1=new Object[]{"A1","A2","A3"};
        Object[] arr2=new Object[]{"B1","B2","B3","B4"};
        Object[] arr3=new Object[]{"C1","C2","C3","C4","C5"};

        List<Object[]> dataList=new ArrayList<Object[]>();
        dataList.add(arr1);
        dataList.add(arr2);
        dataList.add(arr3);
        List<Object[]> resultList= combination(dataList,0,null);

        //打印组合结果
        for(int i=0;i<resultList.size();i++){
            Object[] objArr=resultList.get(i);
            for(Object obj : objArr){
                System.out.print( obj+" ");
            }
            System.out.println("组合"+(i+1)+"---");
        }
    }
}