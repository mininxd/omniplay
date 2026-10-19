package com.joaomgcd.taskerm.action.input;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.service.controls.Control;
import android.service.controls.templates.ControlButton;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.RangeTemplate;
import android.service.controls.templates.StatelessTemplate;
import android.service.controls.templates.ToggleRangeTemplate;
import android.service.controls.templates.ToggleTemplate;
import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ActivitySecondaryApp;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class StoredPowerMenuAction {
    private Boolean active;
    private String commandPrefix;
    private String icon;
    private final String id;
    private Float rangeCurrent;
    private String rangeFormatString;
    private Float rangeMax;
    private Float rangeMin;
    private Float rangeStep;
    private String subtitle;
    private String title;
    private PowerMenuActionType type;

    public StoredPowerMenuAction(String str, PowerMenuActionType powerMenuActionType, String str2, String str3, String str4, String str5, Boolean bool, Float f2, Float f3, Float f4, Float f5, String str6) {
        c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
        c.f.b.k.b(powerMenuActionType, "type");
        c.f.b.k.b(str2, "title");
        this.id = str;
        this.type = powerMenuActionType;
        this.title = str2;
        this.subtitle = str3;
        this.icon = str4;
        this.commandPrefix = str5;
        this.active = bool;
        this.rangeMin = f2;
        this.rangeMax = f3;
        this.rangeCurrent = f4;
        this.rangeStep = f5;
        this.rangeFormatString = str6;
    }

    public final String getId() {
        return this.id;
    }

    public final PowerMenuActionType getType() {
        return this.type;
    }

    public final void setType(PowerMenuActionType powerMenuActionType) {
        c.f.b.k.b(powerMenuActionType, "<set-?>");
        this.type = powerMenuActionType;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.title = str;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final void setSubtitle(String str) {
        this.subtitle = str;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final void setIcon(String str) {
        this.icon = str;
    }

    public final String getCommandPrefix() {
        return this.commandPrefix;
    }

    public final void setCommandPrefix(String str) {
        this.commandPrefix = str;
    }

    public /* synthetic */ StoredPowerMenuAction(String str, PowerMenuActionType powerMenuActionType, String str2, String str3, String str4, String str5, Boolean bool, Float f2, Float f3, Float f4, Float f5, String str6, int i, c.f.b.g gVar) {
        this(str, powerMenuActionType, str2, str3, str4, str5, (i & 64) != 0 ? (Boolean) null : bool, (i & 128) != 0 ? (Float) null : f2, (i & 256) != 0 ? (Float) null : f3, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Float) null : f4, (i & 1024) != 0 ? (Float) null : f5, (i & 2048) != 0 ? (String) null : str6);
    }

    public final Boolean getActive() {
        return this.active;
    }

    public final void setActive(Boolean bool) {
        this.active = bool;
    }

    public final Float getRangeMin() {
        return this.rangeMin;
    }

    public final void setRangeMin(Float f2) {
        this.rangeMin = f2;
    }

    public final Float getRangeMax() {
        return this.rangeMax;
    }

    public final void setRangeMax(Float f2) {
        this.rangeMax = f2;
    }

    public final Float getRangeCurrent() {
        return this.rangeCurrent;
    }

    public final void setRangeCurrent(Float f2) {
        this.rangeCurrent = f2;
    }

    public final Float getRangeStep() {
        return this.rangeStep;
    }

    public final void setRangeStep(Float f2) {
        this.rangeStep = f2;
    }

    public final String getRangeFormatString() {
        return this.rangeFormatString;
    }

    public final void setRangeFormatString(String str) {
        this.rangeFormatString = str;
    }

    public final void changeState(StoredPowerMenuAction storedPowerMenuAction) {
        c.f.b.k.b(storedPowerMenuAction, "storedPowerMenuAction");
        this.type = storedPowerMenuAction.type;
        String str = storedPowerMenuAction.title;
        if (str == null) {
            str = this.title;
        }
        this.title = str;
        String str2 = storedPowerMenuAction.subtitle;
        if (str2 == null) {
            str2 = this.subtitle;
        }
        this.subtitle = str2;
        String str3 = storedPowerMenuAction.icon;
        if (str3 == null) {
            str3 = this.icon;
        }
        this.icon = str3;
        Boolean bool = storedPowerMenuAction.active;
        if (bool == null) {
            bool = this.active;
        }
        this.active = bool;
        Float f2 = storedPowerMenuAction.rangeMin;
        if (f2 == null) {
            f2 = this.rangeMin;
        }
        this.rangeMin = f2;
        Float f3 = storedPowerMenuAction.rangeMax;
        if (f3 == null) {
            f3 = this.rangeMax;
        }
        this.rangeMax = f3;
        Float f4 = storedPowerMenuAction.rangeCurrent;
        if (f4 == null) {
            f4 = this.rangeCurrent;
        }
        this.rangeCurrent = f4;
        Float f5 = storedPowerMenuAction.rangeStep;
        if (f5 == null) {
            f5 = this.rangeStep;
        }
        this.rangeStep = f5;
        String str4 = storedPowerMenuAction.rangeFormatString;
        if (str4 == null) {
            str4 = this.rangeFormatString;
        }
        this.rangeFormatString = str4;
        String str5 = storedPowerMenuAction.commandPrefix;
        if (str5 == null) {
            str5 = this.commandPrefix;
        }
        this.commandPrefix = str5;
    }

    public final int getDeviceType() {
        switch (this.type) {
            case Button:
                return 0;
            case Toggle:
            case Range:
            case ToggleRange:
            case NoAction:
                return -1;
            default:
                throw new c.i();
        }
    }

    public final b.a.l<Icon> getControlIcon(Context context) {
        c.f.b.k.b(context, "context");
        if (this.icon == null) {
            b.a.l<Icon> lVarA = b.a.l.a(Icon.createWithResource(context, R.mipmap.cust_app_main_icon));
            c.f.b.k.a((Object) lVarA, "Single.just(Icon.createW…pmap.cust_app_main_icon))");
            return lVarA;
        }
        b.a.l lVarC = com.joaomgcd.taskerm.m.b.a(this.icon, context, 50, 50).c(b.f5370a);
        c.f.b.k.a((Object) lVarC, "icon.getBitmap(context, …eWithBitmap(it)\n        }");
        return lVarC;
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5370a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Icon apply(Bitmap bitmap) {
            c.f.b.k.b(bitmap, "it");
            return Icon.createWithBitmap(bitmap);
        }
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f5369b;

        a(Context context) {
            this.f5369b = context;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Control apply(Icon icon) {
            c.f.b.k.b(icon, "icon");
            Control.StatefulBuilder statefulBuilder = new Control.StatefulBuilder(StoredPowerMenuAction.this.getId(), PendingIntent.getActivity(this.f5369b, 0, new Intent(this.f5369b, (Class<?>) ActivitySecondaryApp.class), 268435456));
            statefulBuilder.setTitle(StoredPowerMenuAction.this.getTitle());
            String subtitle = StoredPowerMenuAction.this.getSubtitle();
            if (subtitle != null) {
                statefulBuilder.setSubtitle(subtitle);
            }
            statefulBuilder.setDeviceType(StoredPowerMenuAction.this.getDeviceType());
            statefulBuilder.setControlId(StoredPowerMenuAction.this.getId());
            statefulBuilder.setStatus(1);
            statefulBuilder.setCustomIcon(icon);
            statefulBuilder.setControlTemplate(StoredPowerMenuAction.this.getControlTemplate());
            return statefulBuilder.build();
        }
    }

    public final b.a.l<Control> getControl(Context context) {
        c.f.b.k.b(context, "context");
        b.a.l lVarC = getControlIcon(context).c(new a(context));
        c.f.b.k.a((Object) lVarC, "getControlIcon(context).…Builder.build()\n        }");
        return lVarC;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return !(c.f.b.k.a((Object) this.id, (Object) ((StoredPowerMenuAction) obj).id) ^ true);
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.action.input.StoredPowerMenuAction");
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ControlTemplate getControlTemplate() {
        switch (this.type) {
            case Button:
                return new StatelessTemplate(this.id);
            case Toggle:
                return new ToggleTemplate(this.id, getControlButton());
            case Range:
                return getRangeTemplate();
            case ToggleRange:
                return new ToggleRangeTemplate(this.id, getControlButton(), getRangeTemplate());
            case NoAction:
                ControlTemplate noTemplateObject = ControlTemplate.getNoTemplateObject();
                c.f.b.k.a((Object) noTemplateObject, "ControlTemplate.getNoTemplateObject()");
                return noTemplateObject;
            default:
                throw new c.i();
        }
    }

    private final ControlButton getControlButton() {
        Boolean bool = this.active;
        return new ControlButton(bool != null ? bool.booleanValue() : false, net.dinglisch.android.taskerm.be.EXTRA_ID);
    }

    private final RangeTemplate getRangeTemplate() {
        Float f2 = this.rangeMin;
        float fFloatValue = f2 != null ? f2.floatValue() : 0.0f;
        Float f3 = this.rangeMax;
        float fFloatValue2 = f3 != null ? f3.floatValue() : 100.0f;
        Float f4 = this.rangeCurrent;
        float fB = c.i.d.b(c.i.d.a(f4 != null ? f4.floatValue() : 0.0f, fFloatValue), fFloatValue2);
        String str = this.id;
        Float f5 = this.rangeStep;
        return new RangeTemplate(str, fFloatValue, fFloatValue2, fB, f5 != null ? f5.floatValue() : 1.0f, this.rangeFormatString);
    }
}
