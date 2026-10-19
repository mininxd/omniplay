package com.sonyericsson.soundenhancement;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectUtilities;

/* JADX INFO: loaded from: classes2.dex */
public class SurroundSelectorAdapter extends PagerAdapter {
    private Context mContext;
    private String[] mDescriptions;
    private Integer[] mImageIds;
    private LayoutInflater mInflater;
    private boolean mIsMultiLayoutRequested;
    private String[] mNames;
    private float mPositionAndOffset;
    private ViewHolder[] mHolder = null;
    private int mPosition = -1;
    private int mScrollState = -1;

    public SurroundSelectorAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    private static class ViewHolder {
        ImageView bg;
        ImageView icon;

        private ViewHolder() {
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = this.mInflater.inflate(R.layout.surround_selector_item, container, false);
        if (this.mHolder[position] == null) {
            ViewHolder holder = new ViewHolder();
            ViewHolder[] viewHolderArr = this.mHolder;
            viewHolderArr[position] = holder;
            viewHolderArr[position].bg = (ImageView) convertView.findViewById(R.id.surround_selector_bg);
            SoundEnhancementLocalise localise = new SoundEnhancementLocalise();
            SoundEnhancementPresetList surroundPresetlist = new SoundEnhancementPresetList();
            surroundPresetlist.queryAvailableSurroundPresets(AudioEffectUtilities.getSurroundPresetNames());
            String[] presetNames = surroundPresetlist.getSurroundPresetNames();
            this.mHolder[position].bg.setContentDescription(localise.getLocalisedSurroundPresetName(this.mContext, presetNames[position]) + localise.getLocalisedSurroundDescription(this.mContext, presetNames[position]));
            this.mHolder[position].icon = (ImageView) convertView.findViewById(R.id.surround_selector_icon);
            this.mHolder[position].icon.setImageResource(this.mImageIds[position].intValue());
            this.mHolder[position].icon.setContentDescription(this.mNames[position] + "." + this.mDescriptions[position]);
            if (this.mIsMultiLayoutRequested) {
                layoutViewHolder(this.mHolder[position], true);
            }
            if (this.mPosition == position) {
                this.mHolder[position].bg.setSelected(true);
            }
            this.mHolder[position].bg.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sonyericsson.soundenhancement.SurroundSelectorAdapter.1
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        SurroundSelectorAdapter.this.setAccentColor();
                    }
                }
            });
        }
        setAccentColor();
        container.addView(convertView);
        return convertView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
        this.mHolder[position] = null;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        Integer[] numArr = this.mImageIds;
        if (numArr == null) {
            return 0;
        }
        return numArr.length;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public String getDescription(int position) {
        String[] strArr = this.mDescriptions;
        if (strArr == null) {
            return null;
        }
        return strArr[position];
    }

    protected void setImageIds(Integer[] ids) {
        if (ids == null) {
            throw new IllegalArgumentException("Parameter is null.");
        }
        this.mImageIds = ids;
        if (this.mHolder == null) {
            this.mHolder = new ViewHolder[this.mImageIds.length];
        }
    }

    protected void setDescriptions(String[] descriptions) {
        this.mDescriptions = descriptions;
    }

    protected void setPresetNames(String[] names) {
        this.mNames = names;
    }

    public void setSelected(int position) {
        this.mPosition = position;
        if (this.mHolder == null) {
            return;
        }
        for (int i = 0; i < this.mImageIds.length; i++) {
            boolean selected = false;
            if (i == position) {
                selected = true;
            }
            ViewHolder[] viewHolderArr = this.mHolder;
            if (viewHolderArr[i] != null && viewHolderArr[i].bg != null) {
                this.mHolder[i].bg.setSelected(selected);
                setAccentColor();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAccentColor() {
        Drawable frame;
        int i = this.mPosition;
        if (i == -1) {
            return;
        }
        ViewHolder[] viewHolderArr = this.mHolder;
        if (viewHolderArr[i] == null || viewHolderArr[i].bg == null || (frame = this.mHolder[this.mPosition].bg.getBackground()) == null) {
            return;
        }
        if (this.mHolder[this.mPosition].bg.hasFocus()) {
            frame.clearColorFilter();
        } else {
            int accentColor = SoundEnhancementUtility.fetchAccentColor(this.mContext);
            frame.setColorFilter(new PorterDuffColorFilter(accentColor, PorterDuff.Mode.MULTIPLY));
        }
    }

    public void setMultiWindowLayout(boolean isMulti) {
        this.mIsMultiLayoutRequested = isMulti;
        ViewHolder[] viewHolderArr = this.mHolder;
        if (viewHolderArr != null) {
            for (ViewHolder holder : viewHolderArr) {
                layoutViewHolder(holder, isMulti);
            }
        }
    }

    private void layoutViewHolder(ViewHolder holder, boolean isMulti) {
        int bgWidth;
        int bgHeight;
        int iconWidth;
        int iconHeight;
        Resources res = this.mContext.getResources();
        if (isMulti) {
            bgWidth = res.getDimensionPixelSize(R.dimen.surround_preset_bg_small_width);
            bgHeight = res.getDimensionPixelSize(R.dimen.surround_preset_bg_small_height);
            iconWidth = res.getDimensionPixelSize(R.dimen.surround_preset_icon_small_width);
            iconHeight = res.getDimensionPixelSize(R.dimen.surround_preset_icon_small_height);
        } else {
            bgWidth = res.getDimensionPixelSize(R.dimen.surround_preset_bg_width);
            bgHeight = res.getDimensionPixelSize(R.dimen.surround_preset_bg_height);
            iconWidth = res.getDimensionPixelSize(R.dimen.surround_preset_icon_width);
            iconHeight = res.getDimensionPixelSize(R.dimen.surround_preset_icon_height);
        }
        holder.bg.setLayoutParams(new FrameLayout.LayoutParams(bgWidth, bgHeight, 17));
        holder.icon.setLayoutParams(new FrameLayout.LayoutParams(iconWidth, iconHeight, 17));
    }

    public void setScrollState(int scrollState) {
        this.mScrollState = scrollState;
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public void setPositionAndOffset(float positionAndOffset) {
        this.mPositionAndOffset = positionAndOffset;
    }

    public float getPositionAndOffset() {
        return this.mPositionAndOffset;
    }
}
