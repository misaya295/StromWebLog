package com.cwk.strom.weblog;

import clojure.lang.Var;
import com.esotericsoftware.kryo.io.Input;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class WebLogBolt implements IRichBolt {
    private int line_num=0;

    public void cleanup() {

        //清除资源
    }

    public void execute(Tuple tuple) {
        //执行

        //获取数据
//        String log = tuple.getStringByField("log");
        //www.atguigu.com	CYYH6Y2345GHI899OFG4V9U567	2017-08-07 08:40:52

        String line = tuple.getString(0);
        //切割数据
        String[] split = line.split("\t");
        String session_id = split[1];

        //统计行数
        line_num++;

        //打印
        System.out.println(Thread.currentThread().getId() + "   " + session_id + "   linenum:" + line_num);
    }

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        //准备
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //声明
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
