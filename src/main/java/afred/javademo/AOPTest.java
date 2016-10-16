package afred.javademo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

import afred.javademo.aop.IHello;

/**
 * Created by afred on 16/10/16.
 */
@BenchmarkMode(Mode.SampleTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(24)
@State(Scope.Benchmark)
@Fork(5)
public class AOPTest {

    private ApplicationContext context;

    private IHello javaBean;

    private IHello proxyBean;

    @Setup(Level.Trial)
    public void setup() {
        context = new ClassPathXmlApplicationContext("proxyfactorybean.xml");

        javaBean = (IHello) context.getBean("helloService");
        proxyBean = (IHello) context.getBean("logAdvisor");
    }

    @Benchmark
    public void callBean() {
        javaBean.sayHi("hello");
    }

    @Benchmark
    public void callProxy() {
        javaBean.sayHi("hello");
    }


}
