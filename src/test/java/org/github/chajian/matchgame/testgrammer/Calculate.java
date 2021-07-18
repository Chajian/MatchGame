package org.github.chajian.matchgame.testgrammer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
    public void c(){
        String rule = "%.*%";
        String input = "slkjdfljsd%player%ksjdlkfjsdf";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(input);
        if(m.find())
            log.info("匹配的消息:"+m.group().replaceAll("%",""));
    }

}
