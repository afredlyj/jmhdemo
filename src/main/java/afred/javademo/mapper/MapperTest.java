package afred.javademo.mapper;

import com.google.common.collect.Lists;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by afred on 17/3/24.
 */

@BenchmarkMode(Mode.All)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(24)
@State(Scope.Benchmark)
@Fork(5)
public class MapperTest {

    private MapperFacade orikaMapper;

    private Mapper dozerMappder;

    @Setup(Level.Trial)
    public void init() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        orikaMapper = mapperFactory.getMapperFacade();
        dozerMappder = new DozerBeanMapper();
    }

    @Benchmark
    public void dozerTest() {
        Student student = new Student();
        student.setName("afred");
        student.setAge(18);
        Teacher teacher = new Teacher();
        teacher.setName("doubi");
        ArrayList<String> list = Lists.newArrayList("chinese", "english");
        student.setTeacher(teacher);
        student.setCourse(list);
        dozerMappder.map(student, new StudentVO());

    }


    @Benchmark
    public void orikaTest() {

        Student student = new Student();
        student.setName("afred");
        student.setAge(18);
        Teacher teacher = new Teacher();
        teacher.setName("doubi");
        ArrayList<String> list = Lists.newArrayList("chinese", "english");
        student.setTeacher(teacher);
        student.setCourse(list);

        StudentVO studentVo = orikaMapper.map(student, StudentVO.class);
    }


    @Benchmark
    public void manualTest() {
        Student student = new Student();
        student.setName("afred");
        student.setAge(18);
        Teacher teacher = new Teacher();
        teacher.setName("doubi");
        ArrayList<String> list = Lists.newArrayList("chinese", "english");
        student.setTeacher(teacher);
        student.setCourse(list);

        StudentVO studentVO = new StudentVO();
        studentVO.setCourse(student.getCourse());
        studentVO.setAge(student.getAge());
        studentVO.setName(student.getName());
        TeacherVO teacherVO = new TeacherVO();
        teacherVO.setName(teacher.getName());
        studentVO.setTeacher(teacherVO);

    }

    public static class TeacherVO {
        private String name;

        public TeacherVO() {

        }

        public TeacherVO(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class StudentVO {
        public String name;
        private int age;
        private TeacherVO teacher;
        private List<String> course = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public StudentVO() {

        }

        public StudentVO(String name, int age, TeacherVO teacher, List<String> course) {
            this.name = name;
            this.age = age;
            this.teacher = teacher;
            this.course = course;
        }

        public List<String> getCourse() {
            return course;
        }

        public void setCourse(List<String> course) {
            this.course = course;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public TeacherVO getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherVO teacher) {
            this.teacher = teacher;
        }

    }

    public static class Teacher {
        private String name;

        public Teacher() {

        }

        public Teacher(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class Student {
        public String name;
        private int age;
        private Teacher teacher;
        private List<String> course = new ArrayList<>();

        public Student() {

        }

        public Student(String name, int age, Teacher teacher, List<String> course) {
            this.name = name;
            this.age = age;
            this.teacher = teacher;
            this.course = course;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCourse() {
            return course;
        }

        public void setCourse(List<String> course) {
            this.course = course;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

    }


}
