package com.twom.warriorhub;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int OVERLAY_REQUEST = 100;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 60, 40, 40);
        layout.setGravity(Gravity.CENTER);
        TextView title = new TextView(this);
        title.setText("TWOM Warrior Hub V2\n\nProtótipo da interface. Ainda não controla o jogo.");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);
        Button open = new Button(this);
        open.setText("Abrir Hub flutuante");
        layout.addView(open);
        open.setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_REQUEST);
            } else {
                startService(new Intent(this, HubService.class));
            }
        });
        Button stop = new Button(this);
        stop.setText("Fechar Hub");
        layout.addView(stop);
        stop.setOnClickListener(v -> stopService(new Intent(this, HubService.class)));
        setContentView(layout);
    }
    @Override protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == OVERLAY_REQUEST && Settings.canDrawOverlays(this)) {
            startService(new Intent(this, HubService.class));
        }
    }
}
