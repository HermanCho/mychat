package cn.edu.scau.dbclub.mychat.pojo.do0;

import java.util.Arrays;

public class User {
    private Integer id;

    private String name;

    private String chatNum;

    private String password;

    private Long phoneNum;

    private Integer gender;

    private String address;

    private String sign;

    private byte[] icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getChatNum() {
        return chatNum;
    }

    public void setChatNum(String chatNum) {
        this.chatNum = chatNum == null ? null : chatNum.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chatNum='" + chatNum + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum=" + phoneNum +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", sign='" + sign + '\'' +
                ", icon=" + Arrays.toString(icon) +
                '}';
    }
}