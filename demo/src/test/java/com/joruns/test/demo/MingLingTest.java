package com.joruns.test.demo;

import com.joruns.test.demo.util.CommandUtil;
import com.joruns.test.demo.util.RemoteExecuteCommand;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: <br>
 * Create Date: 2020-09-15 10:15 <br>
 *
 * @author wangyu@mvtech.com.cn
 */
public class MingLingTest {


    @Test
    public void test() {

        final RemoteExecuteCommand root = new RemoteExecuteCommand("172.16.3.31", "root", "Tong@2020");
        System.out.println(root.login());




        final String jps = root.execute("cd masscan/bin&&masscan --ping 172.16.2.0/24 --rate 100000");


        final List<String> ips = getIps(jps);


        ips.stream().forEach(trim->{
            final String format = String.format("cd masscan/bin&&masscan -p0-65535 %s --banners --rate 100000", trim);
                System.out.println(format);
                final String jps2 = root.execute(format);
                System.out.println(jps2);
        });

//        final String[] split = jps.split("Discovered open port 0/icmp on ");
//        for (int i = 0; i < split.length; i++) {
//            final String trim = split[i].trim();
////            System.out.println(trim);
//            if (trim != null && !"".equals(trim)) {
//                final String format = String.format("cd masscan/bin&&masscan -p0-65535 %s --banners --rate 100000", trim);
//                System.out.println(format);
//                final String jps2 = root.execute(format);
//                System.out.println(jps2);
//            }
//
//        }


    }



    @Test
    public void test2() throws IOException {
        final String jps = CommandUtil.run("jps");
        System.out.println(jps);
    }




    public static List<String> getIps(String ipString){
        String regEx="((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        List<String> ips = new ArrayList<String>();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ipString);
        while (m.find()) {
            String result = m.group();
            ips.add(result);
        }
        return ips;
    }

}
