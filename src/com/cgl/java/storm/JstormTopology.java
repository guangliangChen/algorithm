package com.cgl.java.storm;

import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

import java.lang.Object;
import java.util.HashMap;

/**
 * Created by chenguangliang on 9/17/15.
 */
public class JstormTopology {

    public static void main(String[] args) throws Exception {

        java.util.Map<String, Object> stormConf = new HashMap<String, Object>();

        TopologyBuilder topoBuilder = new TopologyBuilder();
        StormSubmitter.submitTopology("JstormTopology", stormConf, topoBuilder.createTopology());
    }
}
