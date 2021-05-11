package com.example.finalproject;

import java.io.Serializable;

public class Question {

    private String q;
    private String a;
    private String b;
    private String c;
    private String d;
    private String correct;
    private static long total;
    private static int record;

    public static int getRecord() {
        return record;
    }

    public static void setRecord(int record) {
        Question.record = record;
    }

    public static long getTotal() {
        return total;
    }

    public static void setTotal(long total) {
        Question.total = total;
    }


    public Question()
    {

    }
    public Question(String q,String a,String b,String c,String d,String correct)
    {
        this.q=q;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.correct=correct;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
