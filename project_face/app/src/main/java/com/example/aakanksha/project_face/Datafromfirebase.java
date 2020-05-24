package com.example.aakanksha.project_face;

public class Datafromfirebase {
    private String Age;
    private String Gender;
    private String Emotion;
    private String Date;
    private String Freq;

    public Datafromfirebase()
    {

    }
    public Datafromfirebase(String age, String gender, String emotion, String date, String freq) {
        Age = age;
        Gender = gender;
        Emotion = emotion;
        Date = date;
        Freq = freq;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmotion() {
        return Emotion;
    }

    public void setEmotion(String emotion) {
        Emotion = emotion;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFreq() {
        return Freq;
    }

    public void setFreq(String freq) {
        Freq = freq;
    }
}
