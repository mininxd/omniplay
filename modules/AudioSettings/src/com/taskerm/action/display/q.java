package com.joaomgcd.taskerm.action.display;

import cyanogenmod.app.ProfileManager;
import cyanogenmod.profiles.RingModeSettings;
import java.util.ArrayList;
import java.util.Iterator;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public final class q extends com.joaomgcd.taskerm.action.setting.e<af> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return true;
    }

    private static final class a implements com.joaomgcd.taskerm.helper.x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5136a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f5137b;

        public a(String str, String str2) {
            c.f.b.k.b(str, be.EXTRA_ID);
            c.f.b.k.b(str2, ProfileManager.EXTRA_PROFILE_NAME);
            this.f5136a = str;
            this.f5137b = str2;
        }

        @Override // com.joaomgcd.taskerm.helper.x
        public String a() {
            return this.f5136a;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a(String str) {
            this(str, f.b(str));
            c.f.b.k.b(str, be.EXTRA_ID);
        }

        public String toString() {
            return this.f5137b;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(ActionEdit actionEdit, com.joaomgcd.taskerm.action.setting.a<af> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (af) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, af afVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(afVar, "input");
        ArrayList arrayListD = c.a.j.d("airplane", "alarm_clock", "battery", "bluetooth", "cast", "cdma_eri", "clock", "com.samsung.rcs", "data_connection", "data_saver", "ethernet", "face", "felica_lock", "femtoicon", "gesture", "glove", "gps", "headset", "hotspot", "ime", "ims_volte", "keyguard_wakeup", "knox_container", "lbs", "location", "managed_profile", "mobile", RingModeSettings.RING_MODE_MUTE, "nfc", "nfc_on", "phone_evdo_signal", "phone_signal", "phone_signal_second_sub", be.POPUP_LABEL, "power_saver", "private_mode", "rotate", "safezone", "secure", "smart_bonding", "smart_network", "smart_scroll", be.SPEAKERPHONE_LABEL, "sync_active", "sync_failing", "toddler", "tty", "volte", "volume", "vowifi", "wearable_gear", "wifi", "wifi_ap", "wifi_oxygen", "wifi_p2p", "wimax", "zen", "left_clock_position", "right_clock_position", "middle_clock_position", "nw_booster");
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) arrayListD, 10));
        Iterator it = arrayListD.iterator();
        while (it.hasNext()) {
            arrayList.add(new a((String) it.next()));
        }
        com.joaomgcd.taskerm.helper.l.a(this, arrayList, R.string.dt_icon_to_hide, bVar.a(), iVar, afVar.getIconsToHide(), null, false, null, null, 240, null);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        com.joaomgcd.taskerm.helper.l.a(this, R.string.tip_status_bar_icons, 0, 2, (Object) null);
    }
}
