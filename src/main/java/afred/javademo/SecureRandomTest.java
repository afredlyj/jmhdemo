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

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/10/16.
 * 用法
 * http://openjdk.java.net/projects/code-tools/jmh/
 * http://www.importnew.com/12548.html
 */
@BenchmarkMode(Mode.SampleTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(24)
@State(Scope.Benchmark)
public class SecureRandomTest {

    private SecureRandom secureRandom;

    @Setup(Level.Trial)
    public void setup() {
        secureRandom = new SecureRandom();
    }

    @Benchmark
    public long randomWithNative() {
        return secureRandom.nextLong();
    }

    @Benchmark
    @Fork(jvmArgsAppend = "-Djava.security.egd=file:/dev/./urandom")
    public long randomWithSHA1() {
        return secureRandom.nextLong();
    }
}
