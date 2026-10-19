package com.joaomgcd.taskerm.action.net;

import android.app.usage.NetworkStats;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import java.util.ArrayList;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "nd")
public class OutputQueryNetworkDataSummary {
    private final long bytesReceived;
    private final double bytesReceivedG;
    private final double bytesReceivedK;
    private final double bytesReceivedM;
    private final long bytesSent;
    private final double bytesSentG;
    private final double bytesSentK;
    private final double bytesSentM;
    private final long packetsReceived;
    private final long packetsSent;

    public OutputQueryNetworkDataSummary(NetworkStats.Bucket[] bucketArr) {
        c.f.b.k.b(bucketArr, "summaries");
        ArrayList arrayList = new ArrayList(bucketArr.length);
        for (NetworkStats.Bucket bucket : bucketArr) {
            arrayList.add(Long.valueOf(bucket.getRxBytes()));
        }
        this.bytesReceived = c.a.j.q(arrayList);
        ArrayList arrayList2 = new ArrayList(bucketArr.length);
        for (NetworkStats.Bucket bucket2 : bucketArr) {
            arrayList2.add(Long.valueOf(bucket2.getTxBytes()));
        }
        this.bytesSent = c.a.j.q(arrayList2);
        this.bytesReceivedK = convert(this.bytesReceived, 1);
        this.bytesSentK = convert(this.bytesSent, 1);
        this.bytesReceivedM = convert(this.bytesReceived, 2);
        this.bytesSentM = convert(this.bytesSent, 2);
        this.bytesReceivedG = convert(this.bytesReceived, 3);
        this.bytesSentG = convert(this.bytesSent, 3);
        ArrayList arrayList3 = new ArrayList(bucketArr.length);
        for (NetworkStats.Bucket bucket3 : bucketArr) {
            arrayList3.add(Long.valueOf(bucket3.getRxPackets()));
        }
        this.packetsReceived = c.a.j.q(arrayList3);
        ArrayList arrayList4 = new ArrayList(bucketArr.length);
        for (NetworkStats.Bucket bucket4 : bucketArr) {
            arrayList4.add(Long.valueOf(bucket4.getTxPackets()));
        }
        this.packetsSent = c.a.j.q(arrayList4);
    }

    private final double convert(long j, int i) {
        return com.joaomgcd.taskerm.util.aj.a(j / Math.pow(1024.0d, i), 2);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_received_description, labelResId = R.string.network_data_bytes_received, name = "bytes_received")
    public final long getBytesReceived() {
        return this.bytesReceived;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_sent_description, labelResId = R.string.network_data_bytes_sent, name = "bytes_sent")
    public final long getBytesSent() {
        return this.bytesSent;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_received_description, labelResId = R.string.network_data_bytes_received, name = "k_bytes_received")
    public final double getBytesReceivedK() {
        return this.bytesReceivedK;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_sent_description, labelResId = R.string.network_data_bytes_sent, name = "k_bytes_sent")
    public final double getBytesSentK() {
        return this.bytesSentK;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_received_description, labelResId = R.string.network_data_bytes_received, name = "m_bytes_received")
    public final double getBytesReceivedM() {
        return this.bytesReceivedM;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_sent_description, labelResId = R.string.network_data_bytes_sent, name = "m_bytes_sent")
    public final double getBytesSentM() {
        return this.bytesSentM;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_received_description, labelResId = R.string.network_data_bytes_received, name = "g_bytes_received")
    public final double getBytesReceivedG() {
        return this.bytesReceivedG;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_bytes_sent_description, labelResId = R.string.network_data_bytes_sent, name = "g_bytes_sent")
    public final double getBytesSentG() {
        return this.bytesSentG;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_packets_received_description, labelResId = R.string.network_data_packets_received, name = "packets_received")
    public final long getPacketsReceived() {
        return this.packetsReceived;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_data_packets_sent_description, labelResId = R.string.network_data_packets_sent, name = "packets_sent")
    public final long getPacketsSent() {
        return this.packetsSent;
    }
}
