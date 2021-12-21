package com.example.a12_8.zhihui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a12_8.R;

import java.util.ArrayList;
import java.util.List;

public class A_zhihui extends AppCompatActivity {
    public static List<String> hb_title=new ArrayList<>();
    public static List<Integer> hb_img=new ArrayList<>();
    public static List<String> hb_count=new ArrayList<>();

    public static List<String> pl_text1=new ArrayList<>();
    public static List<String> pl_data1=new ArrayList<>();
    public static List<String> pl_count1=new ArrayList<>();

    public static List<String> pl_text2=new ArrayList<>();
    public static List<String> pl_data2=new ArrayList<>();
    public static List<String> pl_count2=new ArrayList<>();

    public static List<String> pl_text3=new ArrayList<>();
    public static List<String> pl_data3=new ArrayList<>();
    public static List<String> pl_count3=new ArrayList<>();

    public static List<String> pl_text4=new ArrayList<>();
    public static List<String> pl_data4=new ArrayList<>();
    public static List<String> pl_count4=new ArrayList<>();

    public static List<String> wz_text1=new ArrayList<>();
    public static List<String> wz_data1=new ArrayList<>();
    public static List<String> wz_count1=new ArrayList<>();

    public static  List<String> hs_name=new ArrayList<>();
    public static  List<String> hs_phone=new ArrayList<>();
    public static  List<String> hs_adress=new ArrayList<>();
    public static  List<String> hs_type=new ArrayList<>();
    public static  List<String> hs_data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_zhihui);
        initdata();
    }

    public static void initdata() {
        hs_name.add("星漾");
        hs_phone.add("10358951670");
        hs_adress.add("安徽省六安市");
        hs_type.add("纸箱");
        hs_data.add("2011-4-20 18:00");




        hb_title.add("可回收物");
        hb_title.add("其他垃圾");
        hb_title.add("厨余垃圾");
        hb_title.add("有害垃圾");
        hb_title.add("西安市智慧环保综合指挥中心开展六·五环境日系列宣传活动");
        hb_img.add(R.drawable.init1);
        hb_img.add(R.drawable.init2);
        hb_img.add(R.drawable.init3);
        hb_img.add(R.drawable.init4);
        hb_img.add(R.drawable.hb_1);
        hb_count.add("可回收物主要包括废纸、塑料、玻璃、金属和布料五大类。\n" +
                "废纸：主要包括报纸、期刊、图书、各种包装纸等。但是，要注意纸巾和厕所纸由于水溶性太强不可回收。\n" +
                "塑料：各种塑料袋、塑料泡沫、塑料包装（快递包装纸是其他垃圾/干垃圾）、一次性塑料餐盒餐具、硬塑料、塑料牙刷、塑料杯子、矿泉水瓶等。\n" +
                "玻璃：主要包括各种玻璃瓶、碎玻璃片、暖瓶等。（镜子是其他垃圾/干垃圾）\n" +
                "金属物：主要包括易拉罐、罐头盒等。\n" +
                "布料：主要包括废弃衣服、桌布、洗脸巾、书包、鞋等。\n" +
                "这些垃圾通过综合处理回收利用，可以减少污染，节省资源。如每回收1吨废纸可造好纸850公斤，节省木材300公斤，比等量生产减少污染74%；每回收1吨塑料饮料瓶可获得0.7吨二级原料；每回收1吨废钢铁可炼好钢0.9吨，比用矿石冶炼节约成本47%，减少空气污染75%，减少97%的水污染和固体废物。\n");
        hb_count.add("其他垃圾（上海称干垃圾）包括除上述几类垃圾之外的砖瓦陶瓷、渣土、卫生间废纸、纸巾等难以回收的废弃物及尘土、食品袋（盒）。采取卫生填埋可有效减少对地下水、地表水、土壤及空气的污染。\n" +
                "大棒骨因为“难腐蚀”被列入“其它垃圾”。玉米核、坚果壳、果核、鸡骨等则是餐厨垃圾。\n" +
                "卫生纸：厕纸、卫生纸遇水即溶，不算可回收的“纸张”，类似的还有烟盒等。\n" +
                "餐厨垃圾装袋：常用的塑料袋，即使是可以降解的也远比餐厨垃圾更难腐蚀。此外塑料袋本身是可回收垃圾。正确做法应该是将餐厨垃圾倒入垃圾桶，塑料袋另扔进“可回收垃圾”桶。\n" +
                "果壳：在垃圾分类中，“果壳瓜皮”的标识就是花生壳，的确属于厨余垃圾。家里用剩的废弃食用油，也归类在“厨余垃圾”。\n" +
                "尘土：在垃圾分类中，尘土属于“其它垃圾”，但残枝落叶属于“厨余垃圾”，包括家里开败的鲜花等。\n");
        hb_count.add("湿垃圾种类\n" +
                "厨余垃圾（上海称湿垃圾）包括剩菜剩饭、骨头、菜根菜叶、果皮等食品类废物。经生物技术就地处理堆肥，每吨可生产0.6~0.7吨有机肥料。\n");
        hb_count.add("有害垃圾含有对人体健康有害的重金属、有毒的物质或者对环境造成现实危害或者潜在危害的废弃物。包括电池、荧光灯管、灯泡、水银温度计、油漆桶、部分家电、过期药品及其容器、过期化妆品等。这些垃圾一般使用单独回收或填埋处理");
        hb_count.add(" 2019年6月5日第48个世界环境日，主场活动在中国浙江杭州举办，中国主题是“美丽中国，我是行动者”。\n" +
                "        为营造全社会自觉改善生态环境出份力的社会氛围，呼吁公众尊重自然，顺应自然，保护自然，共同打好污染防治攻坚战，携手建设天蓝、水碧、土净、山青的美丽中国，西安市智慧环保综合指挥中心助力环境保护宣传月顺利进行，开展了形式多样、内容丰富的系列宣传活动，主要有：《勇做时代追梦人》和《蓝天碧水，不忘有你》系列宣讲、“讲述我身边的环保人”的道德讲堂、“美丽家园”摄影图片展和“垃圾分类”微视频制作展播等活动。\n" +
                " \n" +
                "       在系列宣讲活动中，视频工作室的同事集思广益，选定宣讲内容，确定题目为《勇做时代追梦人》和《蓝天碧水，不忘有你》。宣讲活动通过视频和宣讲团队现场宣讲的方式，声情并茂地描绘出基层环保人怀揣梦想、追逐梦想、努力奔跑的时代精神，展现了基层环保人时刻把群众利益挂在心上，将环保责任落到实处，用实际行动诠释了西安环保铁军精神。\n" +
                " \n" +
                "　　“垃圾分类”微视频，利用可回收材料，采用手工制作和绘制的表现方式，围绕主题“垃圾分类益处多，环境保护靠你我”，拍摄了通俗易懂的微视频小知识。通过政府门户网站、微博平台和大屏幕向公众展播，倡议公众垃圾分类从自身做起，为我市优美环境贡献力量。\n" +
                "0在“讲述我身边的环保人”道德讲堂的活动中，指挥中心同志亲自调查走访、凭借亲身经历和感受，讲述自己身边环保基层工作者和环保志愿者的故事，还原一个个真实的工作场景、一个个鲜活的人物，讴歌环保人面对严峻的生态环境保护形势，任劳任怨、敢于担当、履职尽责的铁军精神，体现了环保人在平凡岗位上做出不平凡业绩，弘扬了西安环保铁军精神。\n" +
                " \n" +
                " \n" +
                "       在“美丽家园”摄影图片展活动中，通过摄影者的镜头，目睹了环保人奋战在环境治理一线的工作场景，展现给公众西安天蓝、水碧、土净、山青的美丽画面，展示出我市环境治理所取得的成果，强化了全民参与、全民共建、全民共享的生态环保理念，让更多的人关注环境保护。\n");

        pl_text1.add("text01");
        pl_text2.add("text02");
        pl_text3.add("text03");
        pl_text4.add("text04");

        pl_data1.add("2011-04-18");
        pl_data2.add("2020-06-20");
        pl_data3.add("2021-11-18");
        pl_data4.add("2019-09-08");

        pl_count1.add("不错");
        pl_count2.add("绿水青山就是金山银山");
        pl_count3.add("智慧环保从你我做起");
        pl_count4.add("保护环境人人有责");

        wz_text1.add("星漾");
        wz_data1.add("2021-12-21");
        wz_count1.add("智慧环保厉害呀");


    }
}