package com.joaomgcd.taskerm.google.vision;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public final class Face {
    private final int anger;
    private final int blurred;
    private final String bounds;
    private final int confidence;
    private final int headwear;
    private final int joy;
    private final String leftEye;
    private final String mouth;
    private final String nose;
    private final String rightEye;
    private final int sorrow;
    private final int surprise;
    private final int underExposed;

    public Face(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, String str3, String str4, String str5) {
        this.joy = i;
        this.sorrow = i2;
        this.anger = i3;
        this.surprise = i4;
        this.underExposed = i5;
        this.blurred = i6;
        this.headwear = i7;
        this.confidence = i8;
        this.bounds = str;
        this.leftEye = str2;
        this.rightEye = str3;
        this.nose = str4;
        this.mouth = str5;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_likelyhood_emotion_description, labelResId = R.string.google_cloud_vision_joy, name = "joy")
    public final int getJoy() {
        return this.joy;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_likelyhood_emotion_description, labelResId = R.string.google_cloud_vision_sorrow, name = "sorrow")
    public final int getSorrow() {
        return this.sorrow;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_likelyhood_emotion_description, labelResId = R.string.google_cloud_vision_anger, name = "anger")
    public final int getAnger() {
        return this.anger;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_likelyhood_emotion_description, labelResId = R.string.google_cloud_vision_surprise, name = "surprise")
    public final int getSurprise() {
        return this.surprise;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_likelyhood_other_description, labelResId = R.string.google_cloud_vision_under_exposed, name = "under_exposed")
    public final int getUnderExposed() {
        return this.underExposed;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_likelyhood_other_description, labelResId = R.string.google_cloud_vision_blurred, name = "blurred")
    public final int getBlurred() {
        return this.blurred;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_likelyhood_other_description, labelResId = R.string.google_cloud_vision_headwear, name = "headwear")
    public final int getHeadwear() {
        return this.headwear;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_score_description, labelResId = R.string.google_cloud_vision_confidence, name = "confidence")
    public final int getConfidence() {
        return this.confidence;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_bounds_description, labelResId = R.string.google_cloud_vision_bounds, name = "bounds")
    public final String getBounds() {
        return this.bounds;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_landmark_description, labelResId = R.string.google_cloud_vision_left_eye, name = "left_eye")
    public final String getLeftEye() {
        return this.leftEye;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_landmark_description, labelResId = R.string.google_cloud_vision_right_eye, name = "right_eye")
    public final String getRightEye() {
        return this.rightEye;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_landmark_description, labelResId = R.string.google_cloud_vision_nose, name = "nose")
    public final String getNose() {
        return this.nose;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_face_landmark_description, labelResId = R.string.google_cloud_vision_mouth, name = "mouth")
    public final String getMouth() {
        return this.mouth;
    }
}
