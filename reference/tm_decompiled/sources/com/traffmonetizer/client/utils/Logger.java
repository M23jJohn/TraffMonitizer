package com.traffmonetizer.client.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import com.traffmonetizer.sdk.model.DebugMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import timber.log.Timber;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t¨\u0006\u0017"}, d2 = {"Lcom/traffmonetizer/client/utils/Logger;", "", "<init>", "()V", "setLogsEnabled", "", "file", "", "context", "Landroid/content/Context;", "console", "logSdk", "message", "Lcom/traffmonetizer/sdk/model/DebugMessage;", "shareLogFile", "archiveLogs", "Ljava/io/File;", "addFileToArchive", "filePath", "", "archiveOutputStream", "Ljava/util/zip/ZipOutputStream;", "cleanupLogs", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Logger {
    public static final int $stable = 0;
    public static final Logger INSTANCE = new Logger();

    private Logger() {
    }

    public static /* synthetic */ void setLogsEnabled$default(Logger logger, boolean z, Context context, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        logger.setLogsEnabled(z, context, z2);
    }

    public final void setLogsEnabled(boolean file, Context context, boolean console) {
        Intrinsics.checkNotNullParameter(context, "context");
        Timber.INSTANCE.uprootAll();
        if (console) {
            Timber.INSTANCE.plant(new Timber.DebugTree());
        }
        if (file) {
            Timber.INSTANCE.plant(new FileLoggingTree(context));
        }
    }

    public final void logSdk(DebugMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        String format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS", Locale.ENGLISH).format(Long.valueOf(message.getTimestamp()));
        Timber.INSTANCE.tag("SDK").d(format + ": " + message.getMessage(), new Object[0]);
    }

    public final void shareLogFile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            Uri uriForFile = FileProvider.getUriForFile(context, "com.traffmonetizer.client.provider", INSTANCE.archiveLogs(context));
            intent.putExtra("android.intent.extra.EMAIL", new String[]{"admin@traffmonetizer.com"});
            intent.putExtra("android.intent.extra.SUBJECT", "Traffmonetizer-v2.0.1");
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.setType("text/plain");
            intent.addFlags(1);
            context.startActivity(Intent.createChooser(intent, "Share logs  using..."));
        } catch (Throwable th) {
            Timber.INSTANCE.d(th);
        }
    }

    private final File archiveLogs(Context context) {
        String format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis()));
        File file = new File(FileLoggingTree.INSTANCE.getLogsDir(context).getAbsolutePath() + "/send");
        if (file.exists()) {
            FilesKt.deleteRecursively(file);
        }
        file.mkdir();
        File file2 = new File(file.getAbsolutePath() + "/traffmonetizer-" + format + ".zip");
        File file3 = FileLoggingTree.INSTANCE.getFile(context);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
        try {
            ZipOutputStream zipOutputStream2 = zipOutputStream;
            Logger logger = INSTANCE;
            String absolutePath = file3.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            logger.addFileToArchive(absolutePath, zipOutputStream2);
            zipOutputStream2.finish();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipOutputStream, null);
            return file2;
        } finally {
        }
    }

    private final void addFileToArchive(String filePath, ZipOutputStream archiveOutputStream) {
        byte[] bArr = new byte[1024];
        try {
            archiveOutputStream.putNextEntry(new ZipEntry(StringsKt.substringAfterLast$default(filePath, "/", (String) null, 2, (Object) null)));
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                archiveOutputStream.write(bArr, 0, read);
            }
            archiveOutputStream.closeEntry();
        } catch (Throwable th) {
            Timber.INSTANCE.e(th, "Error while zipping", new Object[0]);
        }
    }

    public final void cleanupLogs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            File logsDir = FileLoggingTree.INSTANCE.getLogsDir(context);
            if (logsDir.exists()) {
                FilesKt.deleteRecursively(logsDir);
            }
        } catch (Throwable th) {
            Timber.INSTANCE.d(th);
        }
    }
}
