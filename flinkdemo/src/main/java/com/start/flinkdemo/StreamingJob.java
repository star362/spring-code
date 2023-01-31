/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.start.flinkdemo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		/*
		 * Here, you can start creating your execution plan for Flink.
		 *
		 * Start with getting some data from the environment, like
		 * 	env.readTextFile(textPath);
		 *
		 * then, transform the resulting DataStream<String> using operations
		 * like
		 * 	.filter()
		 * 	.flatMap()
		 * 	.join()
		 * 	.coGroup()
		 *
		 * and many more.
		 * Have a look at the programming guide for the Java API:
		 *
		 * http://flink.apache.org/docs/latest/apis/streaming/index.html
		 *
		 *
		 * 	nc -l 9000
		 */

//  通过socket获取源数据
		DataStreamSource<String> sourceData = env.socketTextStream("127.0.0.1", 9000);
		/**
		 *  数据源进行处理
		 *  flatMap方法与spark一样，对数据进行扁平化处理
		 *  将每行的单词处理为<word,1>
		 */
		DataStream<Tuple2<String, Integer>> dataStream = sourceData.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
			public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
				String[] words = s.split(" ");
				for (String word : words) {
					collector.collect(new Tuple2<String, Integer>(word, 1));
				}
			}
		})
				// 相同的单词进行分组
				.keyBy(0)
				//  聚合数据
				.sum(1);
		//  将数据流打印到控制台
		dataStream.print();



		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}
}
