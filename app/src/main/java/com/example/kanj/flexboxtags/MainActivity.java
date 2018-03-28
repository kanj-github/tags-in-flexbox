package com.example.kanj.flexboxtags;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private FlexboxLayout flexbox;
    private Switch switchA, switchB, switchC, switchD, switchE, switchF;
    private HashMap<Integer, CardView> switchIdTagMap;

    private float dpToPixelScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flexbox = (FlexboxLayout) findViewById(R.id.flex_box);

        switchA = (Switch) findViewById(R.id.switch_1);
        switchA.setOnCheckedChangeListener(this);

        switchB = (Switch) findViewById(R.id.switch_2);
        switchB.setOnCheckedChangeListener(this);

        switchC = (Switch) findViewById(R.id.switch_3);
        switchC.setOnCheckedChangeListener(this);

        switchD = (Switch) findViewById(R.id.switch_4);
        switchD.setOnCheckedChangeListener(this);

        switchE = (Switch) findViewById(R.id.switch_5);
        switchE.setOnCheckedChangeListener(this);

        switchF = (Switch) findViewById(R.id.switch_6);
        switchF.setOnCheckedChangeListener(this);

        switchIdTagMap = new HashMap<>();
        dpToPixelScale = Resources.getSystem().getDisplayMetrics().density;
    }

    private CardView getTagForSwitch(final int switchViewId) {
        CardView cv = switchIdTagMap.get(switchViewId);

        if (cv == null) {
            Log.v("Kanj", "Creating tag");
            cv = (CardView) getLayoutInflater().inflate(R.layout.view_tag, null);
            ((TextView) cv.findViewById(R.id.tag_text)).setText(getTagTextForSwitch(switchViewId));
            cv.findViewById(R.id.tag_remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tagDeleteClicked(switchViewId);
                }
            });
            switchIdTagMap.put(switchViewId, cv);
        } else {
            Log.v("Kanj", "Not creating tag");
        }

        return cv;
    }

    @StringRes
    private int getTagTextForSwitch(int switchViewId) {
        switch (switchViewId) {
            case R.id.switch_1:
                return R.string.option_1;
            case R.id.switch_2:
                return R.string.option_2;
            case R.id.switch_3:
                return R.string.option_3;
            case R.id.switch_4:
                return R.string.option_4;
            case R.id.switch_5:
                return R.string.option_5;
            case R.id.switch_6:
                return R.string.option_6;
            default:
                return R.string.option_err;
        }
    }

    private void tagDeleteClicked(int switchViewId) {
        switch (switchViewId) {
            case R.id.switch_1:
                switchA.setChecked(false);
                break;
            case R.id.switch_2:
                switchB.setChecked(false);
                break;
            case R.id.switch_3:
                switchC.setChecked(false);
                break;
            case R.id.switch_4:
                switchD.setChecked(false);
                break;
            case R.id.switch_5:
                switchE.setChecked(false);
                break;
            case R.id.switch_6:
                switchF.setChecked(false);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            Log.v("Kanj", "checked");
            FlexboxLayout.LayoutParams layoutParams =
                    new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, convertDpToPx(30));
            layoutParams.setMargins(0 , 0, convertDpToPx(8), convertDpToPx(8));
            flexbox.addView(getTagForSwitch(compoundButton.getId()), layoutParams);
        } else {
            Log.v("Kanj", "unchecked");
            flexbox.removeView(getTagForSwitch(compoundButton.getId()));
        }
    }

    private int convertDpToPx(int dp) {
        return (int) (dp * dpToPixelScale);
    }
}
