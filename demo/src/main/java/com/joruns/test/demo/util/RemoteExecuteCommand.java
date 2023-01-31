package com.joruns.test.demo.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: <br>
 * Create Date: 2020-09-15 10:16 <br>
 *
 * @author wangyu@mvtech.com.cn
 */
public class RemoteExecuteCommand {

    //字符编码默认是utf-8
    public static String DEFAULTCHART = "UTF-8";
    private Connection conn;
    private String ip;
    private String userName;
    private String userPwd;


    public RemoteExecuteCommand(String ip, String userName, String userPwd) {
        this.ip = ip;
        this.userName = userName;
        this.userPwd = userPwd;
    }



    /**
     * 远程登录linux的主机
     *
     * @return 登录成功返回true，否则返回false
     * @author huzhidong
     * @since V0.1
     */
    public Boolean login() {
        boolean flg = false;
        try {
            conn = new Connection(ip);
            conn.connect();//连接
            flg = conn.authenticateWithPassword(userName, userPwd);//认证
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }

    /**
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     * @author huzhidong
     * 远程执行shll脚本或者命令
     * @since V0.1
     */
    public String execute(String cmd) {
        String result = "";
        try {
            if (login()) {
                Session session = conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result = processStdout(session.getStdout(), DEFAULTCHART);
                //如果未得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isBlank(result)) {
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                }
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param cmds 即将执行的命令
     * @return 命令执行完后返回的结果值
     * @author huzhidong
     * 远程执行shll脚本或者命令
     * @since V0.1
     */
    public List<String> executeList(List<String> cmds) {
        List<String> results = null;
        if (!CollectionUtils.isEmpty(cmds)) {
            results = new ArrayList<>();
            for (String o : cmds) {
                if (o != null) {
                    String execute = execute(o);
                    results.add(execute);
                }
            }
        }
        return results;
    }

    /**
     * @param cmd 即将执行的命令
     * @return 命令执行成功后返回的结果值，如果命令执行失败，返回空字符串，不是null
     * @author huzhidong
     * 远程执行shll脚本或者命令
     * @since V0.1
     */
    public String executeSuccess(String cmd) {
        String result = "";
        try {
            if (login()) {
                Session session = conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result = processStdout(session.getStdout(), DEFAULTCHART);
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     * @author Ickes
     * @since V0.1
     */
    private String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /***
     * @Author huzhidong
     * @Description //ping
     * @Date 17:01 2019/1/7
     * @Param [ip]
     * @return boolean
     **/
    public boolean ping(String ip){
        Boolean login = this.login();
        if (login) {
            String cmd = "ping -c 5 -W 500 "+ip;
            String s = this.executeSuccess(cmd);
            //若line含有=18 ms ttl=64字样,说明已经ping通
            String regex = "(TTL=\\d+)";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public static void setCharset(String charset) {
        DEFAULTCHART = charset;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
