package com.dolby.daxappui.headphoneTuning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.dolby.dax.DolbyAudioEffect;
import com.dolby.daxappui.IDsFragObserver;
import com.dolby.daxappui.R;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ValidFragment"})
public class FragHeadPhoneTuning extends Fragment {
    private static int mSelectedPosition;
    private DolbyAudioEffect mDolbyAudio;
    private IDsFragObserver mFObserver = null;
    private TuningListAdapter mTuningListAdapter;
    private ListView mTuningsListView;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mFObserver = (IDsFragObserver) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement IDsFragObserver");
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.headphone_tuning, viewGroup, false);
        IDsFragObserver iDsFragObserver = this.mFObserver;
        this.mDolbyAudio = iDsFragObserver == null ? null : iDsFragObserver.getDolbyAudioEffect();
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect == null) {
            Log.e("FragHeadPhoneTuning", "onCreateView(): Dolby audio effect is null!");
            return null;
        }
        try {
            this.mTuningListAdapter = new TuningListAdapter(dolbyAudioEffect.getTuningDevicesList(DaxTuningApiConstants.DEFAULT_HEADPHONE_PORT));
            this.mTuningsListView = (ListView) viewInflate.findViewById(R.id.tuningsListView);
            this.mTuningsListView.setAdapter((ListAdapter) this.mTuningListAdapter);
        } catch (Exception unused) {
            Log.e("FragHeadPhoneTuning", "Invalid Tuning Device List!");
        }
        this.mTuningsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.dolby.daxappui.headphoneTuning.-$$Lambda$FragHeadPhoneTuning$hoIRfQ8Iudr8W3YZlOYoOYOnliw
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.lambda$onCreateView$0$FragHeadPhoneTuning(adapterView, view, i, j);
            }
        });
        return viewInflate;
    }

    public /* synthetic */ void lambda$onCreateView$0$FragHeadPhoneTuning(AdapterView adapterView, View view, int i, long j) {
        mSelectedPosition = i;
        String str = (String) this.mTuningsListView.getItemAtPosition(i);
        IDsFragObserver iDsFragObserver = this.mFObserver;
        this.mDolbyAudio = iDsFragObserver == null ? null : iDsFragObserver.getDolbyAudioEffect();
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect == null) {
            Log.e("FragHeadPhoneTuning", "onCreateView(): Dolby audio effect is null!");
        } else {
            dolbyAudioEffect.setSelectedTuningDevice(DaxTuningApiConstants.DEFAULT_HEADPHONE_PORT, str);
            this.mTuningListAdapter.notifyDataSetChanged();
        }
    }

    private class TuningListAdapter extends ArrayAdapter<String> {
        TuningListAdapter(String[] strArr) {
            super(FragHeadPhoneTuning.this.getActivity(), android.R.layout.simple_list_item_1);
            add(DaxTuningApiConstants.DEFAULT_NAME);
            for (String str : strArr) {
                if (!str.equals(DaxTuningApiConstants.DEFAULT_NAME)) {
                    add(str);
                }
            }
            try {
                FragHeadPhoneTuning.this.mDolbyAudio = FragHeadPhoneTuning.this.mFObserver == null ? null : FragHeadPhoneTuning.this.mFObserver.getDolbyAudioEffect();
                if (FragHeadPhoneTuning.this.mDolbyAudio != null) {
                    int unused = FragHeadPhoneTuning.mSelectedPosition = getPosition(FragHeadPhoneTuning.this.mDolbyAudio.getSelectedTuningDevice(DaxTuningApiConstants.DEFAULT_HEADPHONE_PORT));
                } else {
                    Log.e("FragHeadPhoneTuning", "TuningListAdapter(): Dolby audio effect is null!");
                }
            } catch (Exception unused2) {
                Log.e("FragHeadPhoneTuning", "Uninitialized selected position!");
            }
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = (TextView) super.getView(i, view, viewGroup);
            if (i == 0) {
                textView.setText(FragHeadPhoneTuning.this.getString(R.string.default_headphone));
            }
            if (FragHeadPhoneTuning.mSelectedPosition == i) {
                textView.setTextColor(ContextCompat.getColor(FragHeadPhoneTuning.this.getActivity(), R.color.colorHpTuningSelectedText));
            } else {
                textView.setTextColor(ContextCompat.getColor(FragHeadPhoneTuning.this.getActivity(), R.color.colorHpTuningText));
            }
            return textView;
        }
    }
}
