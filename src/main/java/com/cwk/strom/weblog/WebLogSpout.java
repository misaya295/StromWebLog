package com.cwk.strom.weblog;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.*;
import java.util.Map;

public class WebLogSpout implements IRichSpout {
    private BufferedReader bufferedReader;
    private SpoutOutputCollector spoutOutputCollector;
    private String str;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
        //打开文件
        try {
             bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/chenwenkang/IdeaProjects/storom/src/website.log")));



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void nextTuple() {
        //发送数据

            try {

                while ((str = bufferedReader.readLine()) != null) {
                    //发送数据
                    spoutOutputCollector.emit(new Values(str));

                    Thread.sleep(500);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

        outputFieldsDeclarer.declare(new Fields("log"));





    }


    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void ack(Object o) {

    }

    public void fail(Object o) {

    }



    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
