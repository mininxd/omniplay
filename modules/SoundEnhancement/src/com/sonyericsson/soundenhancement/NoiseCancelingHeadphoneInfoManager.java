package com.sonyericsson.soundenhancement;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class NoiseCancelingHeadphoneInfoManager {
    private static final int INFO_MAX_NUM = 4;
    private static final String LOG_TAG = "SoundEnhancement";
    private static final int NC_HEADPHONE_TYPE_DEFAULT = 3;
    ArrayList<Integer> mDisplayOrderList = new ArrayList<>();
    NcHeadphoneInfo[] mNcHeadphoneInfoArray = new NcHeadphoneInfo[4];

    public static class NcHeadphoneInfo {
        boolean mIsHiresSupported;
        String mName;
        int mSummaryType;

        public NcHeadphoneInfo(String name, int summaryType, boolean isHiresSupported) {
            this.mName = name;
            this.mSummaryType = summaryType;
            this.mIsHiresSupported = isHiresSupported;
        }
    }

    public NoiseCancelingHeadphoneInfoManager() {
        this.mNcHeadphoneInfoArray[0] = new NcHeadphoneInfo("MDR-NC31/NC033/NWNC33/NWN33S", 0, false);
        this.mNcHeadphoneInfoArray[1] = new NcHeadphoneInfo("MDR-NC31E/NC31EM", 1, false);
        this.mNcHeadphoneInfoArray[2] = new NcHeadphoneInfo("MDR-NW750N", 0, true);
        this.mNcHeadphoneInfoArray[3] = new NcHeadphoneInfo("MDR-NC750", 1, true);
        this.mDisplayOrderList.add(2);
        this.mDisplayOrderList.add(0);
        this.mDisplayOrderList.add(3);
        this.mDisplayOrderList.add(1);
    }

    public int getAccessoryType(int index) {
        if (index < this.mDisplayOrderList.size()) {
            int accessoryType = this.mDisplayOrderList.get(index).intValue();
            return accessoryType;
        }
        return 3;
    }

    public String getNcHeadphoneName(int accessoryType) {
        if (4 <= accessoryType) {
            accessoryType = 3;
        }
        String name = this.mNcHeadphoneInfoArray[accessoryType].mName;
        return name;
    }

    public String getNcHeadphoneNameByListOrder(int index) {
        int accessoryType;
        if (index < this.mDisplayOrderList.size()) {
            accessoryType = this.mDisplayOrderList.get(index).intValue();
        } else {
            accessoryType = 3;
        }
        String name = this.mNcHeadphoneInfoArray[accessoryType].mName;
        return name;
    }

    public int getListOrderIndex(int accessoryType) {
        if (4 <= accessoryType) {
            accessoryType = 3;
        }
        int index = 0;
        while (index < this.mDisplayOrderList.size() && accessoryType != this.mDisplayOrderList.get(index).intValue()) {
            index++;
        }
        return index;
    }

    public int getMaxListItem() {
        return this.mDisplayOrderList.size();
    }

    public int getSummaryType(int index) {
        int accessoryType;
        if (index < this.mDisplayOrderList.size()) {
            accessoryType = this.mDisplayOrderList.get(index).intValue();
        } else {
            accessoryType = 3;
        }
        int sammaryType = this.mNcHeadphoneInfoArray[accessoryType].mSummaryType;
        return sammaryType;
    }

    public boolean isHiresSupported(int index) {
        int accessoryType;
        if (index < this.mDisplayOrderList.size()) {
            accessoryType = this.mDisplayOrderList.get(index).intValue();
        } else {
            accessoryType = 3;
        }
        boolean isSupported = this.mNcHeadphoneInfoArray[accessoryType].mIsHiresSupported;
        return isSupported;
    }
}
