package com.example.vtorushin_ekz3;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class Influx {
    InfluxDB influx;

    @PostConstruct
    public void init(){
        String URL = "http://localhost:8086";
        influx = InfluxDBFactory.connect(URL);
        String Database = "EXAM";
        influx.query(new Query("CREATE DATABASE " + Database));
        influx.setDatabase(Database);
    }

    @Autowired
    public Test test;

    @RequestMapping("/tsubTest")
    public String sender(){
        long startTran = System.currentTimeMillis();
        int random = new Random().nextInt(500);

        try {
            Thread.sleep(2000 + random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTran = System.currentTimeMillis();

        influx.write(Point.measurement("NT")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("responseTime", endTran - startTran)
                .build());
        return test.toString();
    }
}
