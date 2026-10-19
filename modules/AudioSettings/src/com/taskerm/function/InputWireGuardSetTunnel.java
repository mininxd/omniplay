package com.joaomgcd.taskerm.function;

import c.f.b.k;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputWireGuardSetTunnel {
    private final boolean setTunnelUp;
    private final String tunnelName;

    @FunctionInput(explanationResId = R.string.wireguard_tunnel_up, index = 0)
    public static /* synthetic */ void setTunnelUp$annotations() {
    }

    @FunctionInput(explanationResId = R.string.wireguard_tunnel_name, index = 1)
    public static /* synthetic */ void tunnelName$annotations() {
    }

    public InputWireGuardSetTunnel(boolean z, String str) {
        k.b(str, "tunnelName");
        this.setTunnelUp = z;
        this.tunnelName = str;
    }

    public final boolean getSetTunnelUp() {
        return this.setTunnelUp;
    }

    public final String getTunnelName() {
        return this.tunnelName;
    }
}
