package com.joaomgcd.taskerm.function;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import c.f.b.k;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class WireGuardSetTunnel extends FunctionBase<InputWireGuardSetTunnel, OutputWireGuardSetTunnel> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.wireguard_set_tunnel;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputWireGuardSetTunnel> getInputClass() {
        return InputWireGuardSetTunnel.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputWireGuardSetTunnel> getOutputClass() {
        return OutputWireGuardSetTunnel.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public String[] getPermissions(Context context, InputWireGuardSetTunnel inputWireGuardSetTunnel) {
        k.b(context, "context");
        k.b(inputWireGuardSetTunnel, "input");
        return new String[]{"com.wireguard.android.permission.CONTROL_TUNNELS"};
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputWireGuardSetTunnel doIt(Context context, InputWireGuardSetTunnel inputWireGuardSetTunnel) {
        k.b(context, "context");
        k.b(inputWireGuardSetTunnel, "input");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.wireguard.android", "com.wireguard.android.model.TunnelManager$IntentReceiver"));
        intent.setAction(inputWireGuardSetTunnel.getSetTunnelUp() ? "com.wireguard.android.action.SET_TUNNEL_UP" : "com.wireguard.android.action.SET_TUNNEL_DOWN");
        intent.putExtra("tunnel", inputWireGuardSetTunnel.getTunnelName());
        context.sendBroadcast(intent);
        return new OutputWireGuardSetTunnel();
    }
}
