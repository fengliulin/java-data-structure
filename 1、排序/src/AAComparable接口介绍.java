public class AAComparable接口介绍 {
    public static void main(String[] args) {
        Student s1 = new Student("张三", 18);
        Student s2 = new Student("张三", 18);

        Comparable max = getMax(s1, s2);
        System.out.println(max);
    }

    public static Comparable getMax(Comparable c1, Comparable c2) {
        int result = ((Student)c1).compareTo123(((Student)c2));

        if (result >= 0) {
            return c1;
        } else {
            return c2;
        }

    }
}

class Student implements Comparable<Student> {
    private String username;
    private int age;

    public Student() {
    }

    public Student(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }


    public int compareTo123(Student o) {
        return this.getAge() - o.getAge();
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }
}
