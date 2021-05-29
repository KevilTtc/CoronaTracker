package com.example.mcad_proj;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

public class HideStatusBar {
    public static void hideStatusBar(Context context) {
        ((Activity)context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
