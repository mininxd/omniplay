package com.joaomgcd.taskerm.action.audio;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.notification.al;
import cyanogenmod.alarmclock.ClockContract;
import cyanogenmod.app.ProfileManager;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "nc")
@TargetApi(26)
public final class NotificationChannelInfoForAction {
    static final /* synthetic */ c.j.g[] $$delegatedProperties = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(NotificationChannelInfoForAction.class), "channel", "getChannel()Landroid/app/NotificationChannel;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(NotificationChannelInfoForAction.class), "group", "getGroup()Landroid/app/NotificationChannelGroup;"))};
    private final c.e channel$delegate;
    private final String channelId;
    private final Context context;
    private final c.e group$delegate;

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationChannel getChannel() {
        c.e eVar = this.channel$delegate;
        c.j.g gVar = $$delegatedProperties[0];
        return (NotificationChannel) eVar.b();
    }

    private final NotificationChannelGroup getGroup() {
        c.e eVar = this.group$delegate;
        c.j.g gVar = $$delegatedProperties[1];
        return (NotificationChannelGroup) eVar.b();
    }

    public NotificationChannelInfoForAction(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "channelId");
        this.context = context;
        this.channelId = str;
        this.channel$delegate = c.f.a(new a());
        this.group$delegate = c.f.a(new b());
    }

    public final Context getContext() {
        return this.context;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_id_description, labelResId = R.string.word_id, name = be.EXTRA_ID)
    public final String getChannelId() {
        return this.channelId;
    }

    static final class a extends c.f.b.l implements c.f.a.a<NotificationChannel> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final NotificationChannel invoke() {
            return al.b(NotificationChannelInfoForAction.this.getContext(), NotificationChannelInfoForAction.this.getChannelId());
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<NotificationChannelGroup> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final NotificationChannelGroup invoke() {
            return al.a(NotificationChannelInfoForAction.this.getChannel(), NotificationChannelInfoForAction.this.getContext());
        }
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_id_description, labelResId = R.string.pl_name, name = ProfileManager.EXTRA_PROFILE_NAME)
    public final String getName() {
        CharSequence name;
        NotificationChannel channel = getChannel();
        if (channel == null || (name = channel.getName()) == null) {
            return null;
        }
        return name.toString();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_description_description, labelResId = R.string.pl_description, name = "description")
    public final String getDescription() {
        NotificationChannel channel = getChannel();
        if (channel != null) {
            return channel.getDescription();
        }
        return null;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_enabled_description, labelResId = R.string.word_enabled, name = ClockContract.AlarmsColumns.ENABLED)
    public final boolean getEnabled() {
        return al.a(getChannel());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_importance_description, labelResId = R.string.word_importance, name = "importance")
    public final Integer getImportance() {
        NotificationChannel channel = getChannel();
        if (channel != null) {
            return Integer.valueOf(channel.getImportance());
        }
        return null;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_group_description, labelResId = R.string.notification_category_info_group, name = "group_id")
    public final String getGroupId() {
        NotificationChannelGroup group = getGroup();
        if (group != null) {
            return group.getId();
        }
        return null;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_group_name_description, labelResId = R.string.notification_category_info_group_name, name = "group_name")
    public final CharSequence getGroupName() {
        NotificationChannelGroup group = getGroup();
        if (group != null) {
            return group.getName();
        }
        return null;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.notification_category_info_group_enabled_description, labelResId = R.string.notification_category_info_group_enabled, minApi = 28, name = "group_enabled")
    @TargetApi(28)
    public final boolean getGroupEnabled() {
        NotificationChannelGroup group;
        return (!com.joaomgcd.taskerm.util.f.f9323b.f() || (group = getGroup()) == null || group.isBlocked()) ? false : true;
    }
}
