package com.twom.warriorhub;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;

public class HubService extends Service {
    private WindowManager manager;
    private LinearLayout panel;
    private boolean started = false;
    @Override public void onCreate() {
        super.onCreate();
        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(18, 18, 18, 18);
        panel.setBackgroundColor(Color.argb(235, 24, 27, 38));
        TextView status = new TextView(this);
        status.setText("TWOM Hub V2 — Parado");
        status.setTextColor(Color.WHITE);
        status.setTextSize(16);
        panel.addView(status);
        Button start = new Button(this);
        start.setText("Iniciar (demonstração)");
        panel.addView(start);
        start.setOnClickListener(v -> { started = true; status.setText("TWOM Hub V2 — Demonstração ativa"); });
        Button stop = new Button(this);
        stop.setText("Parar");
        panel.addView(stop);
        stop.setOnClickListener(v -> { started = false; status.setText("TWOM Hub V2 — Parado"); });
        Button minimize = new Button(this);
        minimize.setText("Minimizar / Expandir");
        panel.addView(minimize);
        minimize.setOnClickListener(v -> {
            int visibility = start.getVisibility() == android.view.View.VISIBLE
                    ? android.view.View.GONE : android.view.View.VISIBLE;
            start.setVisibility(visibility);
            stop.setVisibility(visibility);
        });
        Button emergency = new Button(this);
        emergency.setText("Fechar / Emergência");
        panel.addView(emergency);
        emergency.setOnClickListener(v -> stopSelf());
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.END;
        params.x = 20;
        params.y = 120;
        manager.addView(panel, params);
    }
    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }
    @Override public void onDestroy() {
        if (panel != null && manager != null) manager.removeView(panel);
        super.onDestroy();
    }
    @Override public IBinder onBind(Intent intent) { return null; }
}
