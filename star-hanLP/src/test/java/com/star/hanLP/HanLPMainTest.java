package com.star.hanLP;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.model.perceptron.CWSTrainer;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import com.hankcs.hanlp.model.perceptron.PerceptronTrainer;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HanLPMainTest {

    private static final Logger log = LoggerFactory.getLogger(HanLPMainTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public static void main(String[] args) {
    }
    /**
     * 2：动态调用
     */
    @Test
    public void test3() {

        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/services/ws/api?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // 设置请求头
            client.getRequestContext().put("CODE","");
            client.getRequestContext().put("SID","");
            client.getRequestContext().put("TIMESTAMP","");
            client.getRequestContext().put("SERVICEID","");

            String TOKEN = "";
            String APPACCTID = "";

            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("RequestInfo", APPACCTID, TOKEN);
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test1() {
        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
        System.out.println(HanLP.segment("哈尔滨工业大学"));
        System.out.println(HanLP.segment("王宇"));


        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
// 注意观察下面两个“希望”的词性、两个“晚霞”的词性
        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
    }


    @Test
    public void test2() {
//        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
// 注意观察下面两个“希望”的词性、两个“晚霞”的词性
        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
    }

    /**
     * 简繁转换
     *
     * @author hankcs
     */
    @Test
    public void demoTraditionalChinese2SimplifiedChinese() {
        System.out.println(HanLP.convertToTraditionalChinese("用笔记本电脑写程序"));
        System.out.println(HanLP.convertToSimplifiedChinese("以後等妳當上皇后，就能買士多啤梨慶祝了"));
    }

    /**
     * 汉字转拼音
     *
     * @author hankcs
     */
    @Test
    public void hanpinyin() {
        String text = "重载不是重任";
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
        System.out.print("原文,");
        for (char c : text.toCharArray()) {
            System.out.printf("%c,", c);
        }
        System.out.println();

        System.out.print("拼音（数字音调）,");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin);
        }
        System.out.println();

        System.out.print("拼音（符号音调）,");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getPinyinWithToneMark());
        }
        System.out.println();

        System.out.print("拼音（无音调）,");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getPinyinWithoutTone());
        }
        System.out.println();

        System.out.print("声调,");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getTone());
        }
        System.out.println();

        System.out.print("声母,");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getShengmu());
        }
        System.out.println();

        System.out.print("韵母,");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getYunmu());
        }
        System.out.println();

        System.out.print("输入法头,");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getHead());
        }
        System.out.println();
    }

    /**
     * 文本推荐(句子级别，从一系列句子中挑出与输入句子最相似的那一个)
     *
     * @author hankcs
     */
    @Test
    public void texttuijian() {
        Suggester suggester = new Suggester();
        String[] titleArray =
                (
                        "威廉王子发表演说 呼吁保护野生动物\n" +
                                "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
                                "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
                                "日本保密法将正式生效 日媒指其损害国民知情权\n" +
                                "英报告说空气污染带来“公共健康危机”"
                ).split("\\n");
        for (String title : titleArray) {
            suggester.addSentence(title);
        }

        System.out.println(suggester.suggest("发言", 1));       // 语义
        System.out.println(suggester.suggest("危机公共", 1));   // 字符
        System.out.println(suggester.suggest("mayun", 1));      // 拼音
    }

    /**
     * 演示词向量的训练与应用
     *
     * @author hankcs
     */
    @Test
    public void demoWord2Vec() {
        /*WordVectorModel wordVectorModel = trainOrLoadModel();
        printNearest("中国", wordVectorModel);
        printNearest("美丽", wordVectorModel);
        printNearest("购买", wordVectorModel);

        // 文档向量
        DocVectorModel docVectorModel = new DocVectorModel(wordVectorModel);
        String[] documents = new String[]{
                "山东苹果丰收",
                "农民在江苏种水稻",
                "奥运会女排夺冠",
                "世界锦标赛胜出",
                "中国足球失败",
        };

        System.out.println(docVectorModel.similarity(documents[0], documents[1]));
        System.out.println(docVectorModel.similarity(documents[0], documents[4]));

        for (int i = 0; i < documents.length; i++)
        {
            docVectorModel.addDocument(i, documents[i]);
        }

        printNearestDocument("体育", documents, docVectorModel);
        printNearestDocument("农业", documents, docVectorModel);
        printNearestDocument("我要看比赛", documents, docVectorModel);
        printNearestDocument("要不做饭吧", documents, docVectorModel);*/
    }

    @Test
    public void any() throws IOException {
        PerceptronLexicalAnalyzer analyzer = new PerceptronLexicalAnalyzer("/Users/star/Desktop/aaa/b.bin",
                HanLP.Config.PerceptronPOSModelPath,
                HanLP.Config.PerceptronNERModelPath);
//                "/Users/star/Desktop/aaa/b.bin",
//                "/Users/star/Desktop/aaa/b.bin");

        System.out.println(analyzer.analyze("上海华安工业（集团）公司董事长谭旭光和秘书胡花蕊来到美国纽约现代艺术博物馆参观"));
        System.out.println(analyzer.analyze("微软公司於1975年由比爾·蓋茲和保羅·艾倫創立，18年啟動以智慧雲端、前端為導向的大改組。"));

        // 任何模型总会有失误，特别是98年这种陈旧的语料库
        System.out.println(analyzer.analyze("总统普京与特朗普通电话讨论太空探索技术公司"));
        // 支持在线学习
        analyzer.learn("与/c 特朗普/nr 通/v 电话/n 讨论/v [太空/s 探索/vn 技术/n 公司/n]/nt");
        // 学习到新知识
        System.out.println(analyzer.analyze("总统普京与特朗普通电话讨论太空探索技术公司"));
        // 还可以举一反三
        System.out.println(analyzer.analyze("主席和特朗普通电话"));

        // 知识的泛化不是死板的规则，而是比较灵活的统计信息
        System.out.println(analyzer.analyze("我在浙江金华出生"));
        analyzer.learn("在/p 浙江/ns 金华/ns 出生/v");
        System.out.println(analyzer.analyze("我在四川金华出生，我的名字叫金华"));

        // 在线学习后的模型支持序列化，以分词模型为例：
//        analyzer.getPerceptronSegmenter().getModel().save(HanLP.Config.PerceptronCWSModelPath);

        // 请用户按需执行对空格制表符等的预处理，只有你最清楚自己的文本中都有些什么奇怪的东西
        System.out.println(analyzer.analyze("空格 \t\n\r\f&nbsp;统统都不要"
                .replaceAll("\\s+", "")    // 去除所有空白符
                .replaceAll("&nbsp;", "")  // 如果一些文本中含有html控制符
        ));
    }

    @Test
    public void ioTest() throws IOException {
        PerceptronTrainer trainer = new CWSTrainer();
        PerceptronTrainer.Result result = trainer.train(
                "/Users/star/Desktop/pku98/199801-test.txt",
                "/Users/star/Desktop/aaa/b.bin"
        );
//                System.out.printf("准确率F1:%.2f\n", result.prf[2]);
    }


}