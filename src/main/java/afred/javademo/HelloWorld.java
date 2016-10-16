package afred.javademo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by afred on 16/10/16.
 */
public class HelloWorld {


    @Benchmark
    public void wellHelloThere() {
        // this method was intentionally left blank.
    }

    /**
     * 可以直接运行main, 也可以命令行
     * mvn clean install;cd target;java -jar benchmarks.jar HelloWorld
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HelloWorld.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
