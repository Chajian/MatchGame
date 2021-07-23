package org.github.chajian.matchgame.testgrammer;

import lombok.extern.slf4j.Slf4j;
import org.bukkit.entity.Player;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Calculate {

    @Test
    public void a(){
        double a = 500;
        log.info("测试结果"+1/a);
    }

    @Test
    public void b(){
        String s = "谁看见对方空间适当加分%player%sdfsdf";
        String pattern = ".*%player%.*";
        boolean isMatch = Pattern.matches(pattern,s);
        System.out.println("字符串是否包含了%player%?"+isMatch);

    }

    @Test
    public void d(){
        int i = 48;
        char g = (char) i;
        log.info("info:"+g);
    }

    /**
     * 插入排序升序
     */
    @Test
    public void insertSort(){
        //定义一个待排序数组
        int[] array = new int[]{213,1,3,1,2,3,123,23,13,13,123,123,23,1};
        //因为i=0算作已经排序好的数，所以i从1开始
        for(int i = 1 ; i < array.length ; i++){
            //保存待排序数
            int temp = array[i];
            //j=i-1,因为待排序数的左边都是已排序好的
            int j = i-1;
            for(; j>=0 ; j--){
                //实现思路
                //因为插入排序是插入到已排序数中
                //所有默认待排序数一定会插入到已排序数中
                //1.每次比较的时候，就可以将比较的数向后移
                //2.如果被比较数比待排序数小,将待排序数插入比较数的位置
                if(array[j] <= temp){
                    array[j+1] = temp;
                    break;
                }
                else {
                    array[j + 1] = array[j];
                }
            }
            //如果存在temp没有被赋值，则是因为当未能满足array[j] <= temp所以我们需要将array[0]赋值给temp
            if(array[j+1] != temp){
                array[0] = temp;
            }
        }
        log.info(""+ Arrays.toString(array));
    }

    @Test
    public void test(){
        int a = 0;
        while(a<100){
            a = a++;
            log.info(""+a);
        }

    }



}
