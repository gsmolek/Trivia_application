package com.example.finalproject;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class Answer implements Serializable {
    public String question;
    public String selected;
    public String correct;
    public boolean answerRight;

    public Answer()
    {

    }
    public Answer(String question,String selected,String correct,boolean answerRight)
    {
        this.question=question;
        this.selected=selected;
        this.correct=correct;
        this.answerRight=answerRight;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public boolean isAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(boolean answerRight) {
        this.answerRight = answerRight;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }





}
