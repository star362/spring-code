package com.start.demo.exceldemo;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import com.google.common.base.Joiner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigDataTest {


    @Test
    public void bigDataTest() {
//        Stream.of().distinct()
        /*String fileName = "服务协议" + System.currentTimeMillis() + ExcelTypeEnum.XLS.getValue();

        // 文件存储位置
        String filePath = BeanTool.getProperty("file.client.storage.path") + "/" + fileName;

        ExcelWriter excelWriter = EasyExcel.write(filePath, ServiceAgreementExcelVO.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(1).build();
        long pageIndex = 1L;
        while (true) {
            // 一次分页查询500条
            PageInfo pageInfo = new PageInfo().setPageSize(500L).setPageIndex(pageIndex);
            serviceAgreementQueryVO.setPagingQuery(pageInfo);
            Result<ServiceAgreementVO> result = getServiceAgreementProductList(serviceAgreementQueryVO, userSessionVO);
            if (result.getPageInfo().getTotal() > 20000) {
                ExceptionUtil.rollback("导出最大不超过20000条", ErrorConstants.EXPORT_CANNOT_EXCEED_20000);
            }
            List<ServiceAgreementExcelVO> serviceAgreementExcelVOList = BeanCopyUtil.copy(result.getModels(), ServiceAgreementExcelVO.class);
            if (!CollectionUtils.isEmpty(serviceAgreementExcelVOList)) {
                // 第一次写入会创建头,第二次写入会在上一次写入的最后一行后面写入
                excelWriter.write(serviceAgreementExcelVOList, writeSheet);
            }

            PageInfo pageInformation = result.getPageInfo();
            if (pageInformation.getPageIndex() * pageInformation.getPageSize() >= pageInformation.getTotal()) {
                break;
            }
            pageIndex++;
        }
        excelWriter.finish();

        // 将生成的文件上传文件服务器 , 返回结果
        Result uploadResult = FileClientUtil.uploadFile(ExcelUtil.getAddress(), new File(filePath), fileName);
        // 上传失败抛出异常
        if (uploadResult.getSuccess() == Constants.NO) {
            ExceptionUtil.rollback("文件上传服务器失败", ErrorConstants.UPLOAD_FAILED);
        }
        // 将文件地址放入vo
        CommonFileVO commonFileVO = JsonUtil.parseObject(JsonUtil.toJsonString(uploadResult.getModels().get(0)), CommonFileVO.class);
        FileResultVO fileResultVO = new FileResultVO();
        fileResultVO.setSymbol(Constants.EXCEL_FILE);
        fileResultVO.setFileId(commonFileVO.getId());
        fileResultVO.setFileCode(commonFileVO.getCode());
        fileResultVO.setFileName(commonFileVO.getOriginalName());
        return Result.createWithModel(null, fileResultVO);*/


    }

    @Test
    private void extractedTest() {
        //签名/验签时，将参数名ASCII码从小到大排序
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("logicId", "logicId()");
        map.put("cardType", "tripRecentForm.getCardType()");
        map.put("traTime", "tripRecentForm.getTraTime()");
        map.put("deviceId", "tripRecentForm.getDeviceId()");
        map.put("traStation", "tripRecentForm.getTraStation()");
        map.put("version", "tripRecentForm.getVersion()");

       /* //1.Guava
        String content1 = Joiner.on("&").withKeyValueSeparator("=").join(map);
        System.out.println("content1:" + content1);

        //2.StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        map.forEach((key, value) -> stringBuffer.append(key).append("=").append(value).append("&"));
        String content2 = stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
        System.out.println("content2:" + content2);
*/
        //3.hutool工具类
        String content3 = MapUtil.sortJoin(map, "&", "=", true, null);
        System.out.println("content3:" + content3);


        String S2 = DigestUtil.sha256Hex(content3);

        String rsaSignPrivateKey = ""; //私钥通过 accessKey 查询

//        RSA rsa = new RSA(rsaSignPrivateKey, null);
        RSA rsa = new RSA();

        System.out.println(rsa.encryptBase64(S2, KeyType.PrivateKey));
    }

    public static void main(String[] args) {
        //签名/验签时，将参数名ASCII码从小到大排序
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("logicId", "logicId()");
        map.put("cardType", "tripRecentForm.getCardType()");
        map.put("traTime", "tripRecentForm.getTraTime()");
        map.put("deviceId", "tripRecentForm.getDeviceId()");
        map.put("traStation", "tripRecentForm.getTraStation()");
        map.put("version", "tripRecentForm.getVersion()");

       /* //1.Guava
        String content1 = Joiner.on("&").withKeyValueSeparator("=").join(map);
        System.out.println("content1:" + content1);

        //2.StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        map.forEach((key, value) -> stringBuffer.append(key).append("=").append(value).append("&"));
        String content2 = stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
        System.out.println("content2:" + content2);
*/
        //3.hutool工具类
        String content3 = MapUtil.sortJoin(map, "&", "=", true, null);
        System.out.println("content3:" + content3);


        //签名/验签时，将参数名ASCII码从小到大排序
        Map<String, Object> map2 = new HashMap<>();
        map.put("logicId", "logicId()");
        map.put("cardType", "tripRecentForm.getCardType()");
        map.put("traTime", "tripRecentForm.getTraTime()");
        map.put("deviceId", "tripRecentForm.getDeviceId()");
        map.put("traStation", "tripRecentForm.getTraStation()");
        map.put("version", "tripRecentForm.getVersion()");

//3.hutool工具类
        String content4 = MapUtil.sortJoin(map, "&", "=", true, null);
        System.out.println("content4:" + content4);

        String S2 = DigestUtil.sha256Hex(content3);

        String rsaSignPrivateKey = ""; //私钥通过 accessKey 查询

//        RSA rsa = new RSA(rsaSignPrivateKey, null);
        RSA rsa = new RSA();

//        System.out.println(rsa.encryptBase64(S2,  KeyType.PrivateKey));
    }

}
