public class Boy {
    private String name;
    private int age;
    Boy(final String name, final int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {return name;}
    public int getAge() { return age;}
    @Override
    public String toString() { return name + "-" + age; }
}
