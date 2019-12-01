package com.cwk.strom.weblog;

import clojure.lang.Compiler;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class WebLogMain {

    public static void main(String[] args) {




        //创建拓扑
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("WebLogSpout", new WebLogSpout(), 1);
        topologyBuilder.setBolt("WebLogBolt", new WebLogBolt(), 2).shuffleGrouping("WebLogSpout");


        //创建配置信息对象 开启woker数
        Config config = new Config();
        config.setNumWorkers(2);


        //提交程序
        if (args.length > 0) {
            try {
                StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }

        } else {
            LocalCluster localCluster = new LocalCluster();

            localCluster.submitTopology("Webtopology", config, topologyBuilder.createTopology());
        }

    }
}
