package com.joruns.test.demo.util;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 封装签名加密工具类
 */
@Component
public class JdSignUtil implements InitializingBean {

    Map<String, Object> keyMap;

    // 获取所有公私钥数据存储到 map
    @Override
    public void afterPropertiesSet() throws Exception {
//        Collections.emptyList().stream().collect(Collectors.toMap())
    }

    /**
     * 构建签名方法
     * <p>
     * sign采用了非对称加密和对称解密对传递参数做了签名处理，防止参数被篡改风险。
     * 签名过程分为三步，如下所述：
     * 1、对于POST表单提交的所有参数按照参数名的ASCII码顺序从小到大排序（字典序），使用URL键值对的方式拼接成字符串S1，（如：k1=value1&k2=value2&k3=value3…）。
     * 2、对拼接的参数字符串S1通过SHA256算法计算摘要，得到字符串S2。
     * 3、对字符串S2使用rsaSignPrivateKey(RSA签名私钥)进行RSA加密，并进行base64转码，得到签名字符串sign。
     *
     * @param imap
     * @param accessKey
     * @return
     */
    public String bulidSign(Map imap, String accessKey) {
        String S1 = MapUtil.sortJoin(imap, "&", "=", true, null);
//        System.out.println("content3:" + content3);
        String S2 = DigestUtil.sha256Hex(S1);

        String rsaSignPrivateKey = ""; //私钥通过 accessKey 查询

        RSA rsa = new RSA(rsaSignPrivateKey, null);

        return rsa.encryptBase64(S2, KeyType.PrivateKey);
    }


    /**
     * 验证签名方法
     * 平台sign验证规则分为五步。如下所述：
     * 1、将解密后的参数按照参数名的ASCII码顺序从小到大排序（字典序），使用URL键值对的方式拼接成字符串S1，（如：k1=value1&k2=value2&k3=value3…）
     * 2、对拼接的参数字符串S1通过SHA256算法计算摘要，得到字符串S2
     * 3、将sign使用BASE64进行解码，得到SS1
     * 4、使用rsaEncryptPublicKey对SS1进行解密，然后转换为字符串SS2
     * 5、对比S2与SS2是否相等
     *
     * @param imap
     * @param sign
     * @param accessKey
     * @return
     */
    public Boolean verifySign(Map imap, String sign, String accessKey) {
        String S1 = MapUtil.sortJoin(imap, "&", "=", true, null);
//        System.out.println("content3:" + content3);
        String S2 = DigestUtil.sha256Hex(S1);

        String SS1 = Base64.decodeStr(sign);

        String rsaEncryptPublicKey = "";//公钥通过 accessKey 查询

        RSA rsa = new RSA(null, rsaEncryptPublicKey);
        String SS2 = rsa.decryptStr(SS1, KeyType.PublicKey);
        return StrUtil.equals(S2, SS2);
    }


    /**
     * 构建数据加密方法
     * 是否加密字段中标注为“加密传输”的字段均先采用rsaEncryptPublicKey加密，再通过base64转换为字符串 。
     *
     * @param s
     * @return
     */
    public String encryptData(String s, String accessKey) {
        String rsaEncryptPublicKey = "";//公钥通过 accessKey 查询
        RSA rsa = new RSA(null, rsaEncryptPublicKey);
        return rsa.encryptBase64(s, KeyType.PublicKey);
    }


    /**
     * 构建数据解密方法
     * <p>
     * 凡标注为“加密传输”的字段均先通过base64 进行解码 ，然后通过rsaEncryptPrivateKey 解密。
     * 所有信息均按照逆向流程处理，商户使用公钥对基本信息加密，商城平台使用私钥对基本信息解密。
     *
     * @param s
     * @return
     */
    public String decryptData(String s, String accessKey) {
        String SS1 = Base64.decodeStr(s);
        String rsaSignPrivateKey = ""; //私钥通过 accessKey 查询

        RSA rsa = new RSA(rsaSignPrivateKey, null);

        return rsa.decryptStr(SS1, KeyType.PrivateKey);
    }


    public static void main(String[] args) {
        long l = System.currentTimeMillis() / 1000L;
//        Long s = new Long(l);

        System.out.println(ObjUtil.serialize(l));
        System.out.println(ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(l).array());
    }


}
