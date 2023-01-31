package com.joruns.test.demo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.joruns.test.demo.config.Alibaba;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/alipay")
public class AliPayController {
@Autowired
  private Alibaba alibaba;
  @Autowired
  private AlipayClient alipayClient;

    /**
     * 网页支付
     * @param httpRequest
     * @param httpResponse
     * @throws ServletException
     * @throws IOException
     */
   @RequestMapping("/create")
   public void doPost(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse) throws ServletException, IOException {
       AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
       model.setOutTradeNo("20150320010101016");//订单编号
       model.setSubject("xinhui商城");
       model.setTotalAmount("198.00");//金额
       model.setBody("商务中心");
       model.setTimeoutExpress("3h");//
       model.setProductCode("QUICK_WAP_WAY");
       model.setGoodsType("1");
       model.setPassbackParams("aaa222aaa");

       AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
       alipayRequest.setReturnUrl(alibaba.getReturnUrl());
       alipayRequest.setNotifyUrl(alibaba.getNotifyUrl());//在公共参数中设置回跳和通知地址
       alipayRequest.setBizModel(model);
//       alipayRequest.setBizContent("{" +
//               " \"out_trade_no\":\"20150320010101011\"," +
//               " \"total_amount\":\"8888.88\"," +
//               " \"subject\":\"商务中心\"," +
//               " \"product_code\":\"QUICK_WAP_PAY\"" +
//               " }");//填充业务参数
       String form="";
       try {
           form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
       } catch (AlipayApiException e) {
           e.printStackTrace();
       }
       httpResponse.setContentType("text/html;charset=" + alibaba.getCharset());
       httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
       httpResponse.getWriter().flush();
       httpResponse.getWriter().close();
   }


   @PostMapping("/notif")
public void test(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException {
       log.info("商户订单号==={}", new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8"));
       log.info("支付宝交易号==={}", new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8"));
       log.info("交易状态==={}", new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8"));
       log.info("用户在交易中支付的金额==={}", new String(request.getParameter("buyer_pay_amount").getBytes("ISO-8859-1"),"UTF-8"));
       log.info("商家在交易中实际收到的款项==={}", new String(request.getParameter("receipt_amount").getBytes("ISO-8859-1"),"UTF-8"));
       log.info("订单金额==={}", new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8"));
       log.info("支付成功的各个渠道金额信息==={}", new String(request.getParameter("fund_bill_list").getBytes("ISO-8859-1"),"UTF-8"));
       log.info("该笔交易的买家付款时间==={}", new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8"));

       Map paramsMap =new HashMap<String,String>();//将异步通知中收到的所有参数都存放到map中

       Map requestParams = request.getParameterMap();
       for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
           String name = (String) iter.next();
           String[] values = (String[]) requestParams.get(name);
           String valueStr = "";
           for (int i = 0; i < values.length; i++) {
               valueStr = (i == values.length - 1) ? valueStr + values[i]
                       : valueStr + values[i] + ",";
           }
           log.info("map===key:{}",name+"   value:"+valueStr);

           //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
           //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
           paramsMap.put(name, valueStr);
       }

       boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, alibaba.getAlipayPublicKey(), alibaba.getCharset(), alibaba.getSigntype()); //调用SDK验证签名
       log.info("================signVerified==={}",signVerified);
       BufferedOutputStream out = new BufferedOutputStream(
               response.getOutputStream());
       if(signVerified){
           log.info("================success==={}",signVerified);
// TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure

//           out.clear();
//           out.println("success");	//请不要修改或删除
           out.write("success".getBytes());
           out.flush();
           out.close();
       }else{
           log.info("================fail==={}",signVerified);
// TODO 验签失败则记录异常日志，并在response中返回failure.
           out.write("fail".getBytes());
           out.flush();
           out.close();
       }
   }


   @RequestMapping("/paycreate")
   public String alipaycreate(){
//       AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
//               "2016092400584193", alibaba.getRsaPrivateKey(), "json",
//               alibaba.getCharset(), alibaba.getAlipayPublicKey(), "RSA2"); //获得初始化的AlipayClient//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
       AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
       AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
       String re="";
       model.setBody("我是测试数据");
       model.setSubject("App支付测试Java");
       model.setOutTradeNo("20150320010101003");
       model.setTimeoutExpress("30m");
       model.setTotalAmount("0.01");
       model.setProductCode("QUICK_MSECURITY_PAY");
       request.setBizModel(model);
       request.setNotifyUrl(alibaba.getNotifyUrl());
       try {
           //这里和普通的接口调用不同，使用的是sdkExecute
           AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
           System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
           re=response.getBody();
       } catch (AlipayApiException e) {
           e.printStackTrace();
       }
       return re;
   }





}
