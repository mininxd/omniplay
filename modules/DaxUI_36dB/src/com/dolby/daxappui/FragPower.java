package com.dolby.daxappui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.dolby.dax.DolbyAudioEffect;

/* JADX INFO: loaded from: classes.dex */
public class FragPower extends Fragment implements View.OnClickListener {
    private Context mContext;
    private IDsFragObserver mFObserver = null;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mFObserver = (IDsFragObserver) context;
            this.mContext = context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement IDsFragObserver");
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean dsOn;
        View viewInflate = layoutInflater.inflate(R.layout.fragpower, viewGroup, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.powerButtonOn);
        imageView.setOnClickListener(this);
        imageView.setSoundEffectsEnabled(false);
        IDsFragObserver iDsFragObserver = this.mFObserver;
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver == null ? null : iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect != null) {
            dsOn = dolbyAudioEffect.getDsOn();
        } else {
            Log.e("FragPower", "onCreateView(): Dolby audio effect is null!");
            dsOn = true;
        }
        if (dsOn) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.btn_power_on_titlebar, this.mContext.getTheme()));
        } else {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.btn_power_off_titlebar, this.mContext.getTheme()));
        }
        return viewInflate;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        IDsFragObserver iDsFragObserver = this.mFObserver;
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver == null ? null : iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null) {
            Log.e("FragPower", "onClick(): Dolby audio effect is null!");
            return;
        }
        if (R.id.powerButtonOn == id) {
            boolean z = !dolbyAudioEffect.getDsOn();
            if (dolbyAudioEffect.hasControl()) {
                dolbyAudioEffect.setDsOn(z);
            } else {
                Log.w("FragPower", "Dolby audio effect is out of control in FragPower");
            }
        }
    }
}
