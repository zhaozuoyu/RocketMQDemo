package cn.zzy.rocketmq.pojo;

import java.io.Serializable;

public class User implements Serializable{

    private String userCode;
    private String userName;
    private String passWord;
    private int age;
    private char sex;

    public User() {
    }
    public User(String userCode, String userName, String passWord, int age, char sex) {
        this.userCode = userCode;
        this.userName = userName;
        this.passWord = passWord;
        this.age = age;
        this.sex = sex;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
