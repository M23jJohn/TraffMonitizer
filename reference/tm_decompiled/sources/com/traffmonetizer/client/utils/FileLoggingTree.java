package com.traffmonetizer.client.utils;

import android.content.Context;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/traffmonetizer/client/utils/FileLoggingTree;", "Ltimber/log/Timber$DebugTree;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "log", "", "priority", "", "tag", "", "message", "t", "", "Companion", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FileLoggingTree extends Timber.DebugTree {
    private static final String CURRENT_FILE_NAME = "AppLogFile";
    private static final String LOGS_DIR = "app_logs";
    private final Context context;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public FileLoggingTree(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    /* compiled from: Logger.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/traffmonetizer/client/utils/FileLoggingTree$Companion;", "", "<init>", "()V", "CURRENT_FILE_NAME", "", "LOGS_DIR", "getLogsDir", "Ljava/io/File;", "context", "Landroid/content/Context;", "getFile", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final File getLogsDir(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            StringBuilder sb = new StringBuilder();
            File externalFilesDir = context.getExternalFilesDir(null);
            Intrinsics.checkNotNull(externalFilesDir);
            sb.append(externalFilesDir.getAbsolutePath());
            sb.append("/app_logs");
            File file = new File(sb.toString());
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        }

        public final File getFile(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new File(getLogsDir(context).getAbsolutePath() + "/AppLogFile.txt");
        }
    }

    @Override // timber.log.Timber.DebugTree, timber.log.Timber.Tree
    protected void log(int priority, String tag, String message, Throwable t) {
        String str;
        Intrinsics.checkNotNullParameter(message, "message");
        if (!Intrinsics.areEqual(tag, "SDK")) {
            str = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())) + "  " + tag + ':' + message + '\n';
        } else {
            str = message + '\n';
        }
        try {
            FileWriter fileWriter = new FileWriter(INSTANCE.getFile(this.context), true);
            try {
                FileWriter fileWriter2 = fileWriter;
                fileWriter2.write(str);
                fileWriter2.flush();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileWriter, null);
            } finally {
            }
        } catch (IOException unused) {
        }
    }
}
