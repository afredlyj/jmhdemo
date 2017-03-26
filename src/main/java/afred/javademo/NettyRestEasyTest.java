package afred.javademo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

import afred.common.http.AsyncHttpConnectionManager;

/**
 * Created by afred on 16/10/31.
 */
@BenchmarkMode(Mode.All)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(24)
@State(Scope.Benchmark)
@Fork(5)
public class NettyRestEasyTest {


    @Benchmark
    public void rawNettyServer() throws Exception {
        AsyncHttpConnectionManager.get("http://127.0.0.1:8081/hello");
    }

    @Benchmark
    public void resteasyServer() throws Exception {
        AsyncHttpConnectionManager.get("http://127.0.0.1:12345/spring/test");

    }

}
