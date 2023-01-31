package com.star.springbeandemo.config;

/**
 * @author wangyu
 * @date: 2020-03-06 18:15
 * @describe:
 */
public class Person  {
    String name;

    private int age;

    protected String sex;

    public String zNmae;

    public Person() {
        System.out.println("null Persion");
    }

    public Person(String name, int age, String sex, String zNmae) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.zNmae = zNmae;
        System.out.println("all Persion");
    }

    public void publicfa(){
        System.out.println("public function");

    }

    private void privatefas(String a){
        System.out.println("private method ==="+a);

    }
    public String saa(){
        System.out.println("null method");
        return "saa function";
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", zNmae='" + zNmae + '\'' +
                '}';
    }
}
