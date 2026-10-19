package com.sonyericsson.soundenhancement.switchbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.sonyericsson.soundenhancement.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class SwitchBar extends LinearLayout implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private static int[] XML_ATTRIBUTES = {R.attr.switchBarMarginStart, R.attr.switchBarMarginEnd, R.attr.switchBarBackgroundColor, R.attr.switchBarBackgroundActivatedColor};
    private int mBackgroundActivatedColor;
    private int mBackgroundColor;
    private String mLabel;
    private int mOffTextId;
    private OnBeforeCheckedChangeListener mOnBeforeListener;
    private int mOnTextId;
    private ToggleSwitch mSwitch;
    private ArrayList<OnSwitchChangeListener> mSwitchChangeListeners;
    private TextView mTextView;

    public interface OnBeforeCheckedChangeListener {
        boolean onBeforeCheckedChanged(boolean z);
    }

    public interface OnSwitchChangeListener {
        void onSwitchChanged(Switch r1, boolean z);
    }

    public SwitchBar(Context context) {
        this(context, null);
    }

    public SwitchBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SwitchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mSwitchChangeListeners = new ArrayList<>();
        LayoutInflater.from(context).inflate(R.layout.switch_bar, this);
        TypedArray a = context.obtainStyledAttributes(attrs, XML_ATTRIBUTES);
        int switchBarMarginStart = (int) a.getDimension(0, 0.0f);
        int switchBarMarginEnd = (int) a.getDimension(1, 0.0f);
        this.mBackgroundColor = a.getColor(2, 0);
        this.mBackgroundActivatedColor = a.getColor(3, 0);
        a.recycle();
        this.mTextView = (TextView) findViewById(R.id.switch_text);
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) this.mTextView.getLayoutParams();
        lp.setMarginStart(switchBarMarginStart);
        this.mSwitch = (ToggleSwitch) findViewById(R.id.switch_widget);
        this.mSwitch.setSaveEnabled(false);
        ViewGroup.MarginLayoutParams lp2 = (ViewGroup.MarginLayoutParams) this.mSwitch.getLayoutParams();
        lp2.setMarginEnd(switchBarMarginEnd);
        setBackgroundColor(this.mBackgroundColor);
        setSwitchBarText(R.string.ahc_on, R.string.ahc_off);
        addOnSwitchChangeListener(new OnSwitchChangeListener() { // from class: com.sonyericsson.soundenhancement.switchbar.SwitchBar.1
            @Override // com.sonyericsson.soundenhancement.switchbar.SwitchBar.OnSwitchChangeListener
            public void onSwitchChanged(Switch switchView, boolean isChecked) {
                SwitchBar.this.setTextViewLabelAndBackground(isChecked);
            }
        });
        this.mSwitch.setOnBeforeCheckedChangeListener(new ToggleSwitch.OnBeforeCheckedChangeListener() { // from class: com.sonyericsson.soundenhancement.switchbar.SwitchBar.2
            @Override // com.sonyericsson.soundenhancement.switchbar.ToggleSwitch.OnBeforeCheckedChangeListener
            public boolean onBeforeCheckedChanged(ToggleSwitch toggleSwitch, boolean checked) {
                if (SwitchBar.this.mOnBeforeListener != null) {
                    return SwitchBar.this.mOnBeforeListener.onBeforeCheckedChanged(checked);
                }
                return false;
            }
        });
        setOnClickListener(this);
        setVisibility(8);
    }

    public void setOnBeforeCheckedChangeListener(OnBeforeCheckedChangeListener listener) {
        this.mOnBeforeListener = listener;
    }

    public void setTextViewLabelAndBackground(boolean isChecked) {
        this.mLabel = getResources().getString(isChecked ? this.mOnTextId : this.mOffTextId);
        setBackgroundColor(isChecked ? this.mBackgroundActivatedColor : this.mBackgroundColor);
        updateText();
    }

    public void setSwitchBarText(int onText, int offText) {
        this.mOnTextId = onText;
        this.mOffTextId = offText;
        setTextViewLabelAndBackground(isChecked());
    }

    private void updateText() {
        this.mTextView.setText(this.mLabel);
    }

    public void setFixedText(int textId) {
        this.mOffTextId = textId;
        this.mOnTextId = textId;
        setTextViewLabelAndBackground(isChecked());
    }

    public void setChecked(boolean checked) {
        setTextViewLabelAndBackground(checked);
        this.mSwitch.setChecked(checked);
    }

    public void setCheckedInternal(boolean checked) {
        setTextViewLabelAndBackground(checked);
        this.mSwitch.setCheckedInternal(checked);
    }

    public boolean isChecked() {
        return this.mSwitch.isChecked();
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mTextView.setEnabled(enabled);
        this.mSwitch.setEnabled(enabled);
    }

    public final ToggleSwitch getSwitch() {
        return this.mSwitch;
    }

    public void show() {
        if (!isShowing()) {
            setVisibility(0);
            this.mSwitch.setOnCheckedChangeListener(this);
        }
    }

    public void hide() {
        if (isShowing()) {
            setVisibility(8);
            this.mSwitch.setOnCheckedChangeListener(null);
        }
    }

    public boolean isShowing() {
        return getVisibility() == 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        boolean isChecked = !this.mSwitch.isChecked();
        OnBeforeCheckedChangeListener onBeforeCheckedChangeListener = this.mOnBeforeListener;
        if (onBeforeCheckedChangeListener != null && onBeforeCheckedChangeListener.onBeforeCheckedChanged(isChecked)) {
            return;
        }
        setChecked(isChecked);
    }

    public void propagateChecked(boolean isChecked) {
        int count = this.mSwitchChangeListeners.size();
        for (int n = 0; n < count; n++) {
            this.mSwitchChangeListeners.get(n).onSwitchChanged(this.mSwitch, isChecked);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        propagateChecked(isChecked);
    }

    public void addOnSwitchChangeListener(OnSwitchChangeListener listener) {
        if (this.mSwitchChangeListeners.contains(listener)) {
            throw new IllegalStateException("Cannot add twice the same OnSwitchChangeListener");
        }
        this.mSwitchChangeListeners.add(listener);
    }

    public void removeOnSwitchChangeListener(OnSwitchChangeListener listener) {
        if (!this.mSwitchChangeListeners.contains(listener)) {
            throw new IllegalStateException("Cannot remove OnSwitchChangeListener");
        }
        this.mSwitchChangeListeners.remove(listener);
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.sonyericsson.soundenhancement.switchbar.SwitchBar.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean checked;
        boolean visible;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.checked = ((Boolean) in.readValue(null)).booleanValue();
            this.visible = ((Boolean) in.readValue(null)).booleanValue();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(Boolean.valueOf(this.checked));
            out.writeValue(Boolean.valueOf(this.visible));
        }

        public String toString() {
            return "SwitchBar.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + this.checked + " visible=" + this.visible + "}";
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.checked = this.mSwitch.isChecked();
        ss.visible = isShowing();
        return ss;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.mSwitch.setCheckedInternal(ss.checked);
        setTextViewLabelAndBackground(ss.checked);
        setVisibility(ss.visible ? 0 : 8);
        this.mSwitch.setOnCheckedChangeListener(ss.visible ? this : null);
        requestLayout();
    }
}
