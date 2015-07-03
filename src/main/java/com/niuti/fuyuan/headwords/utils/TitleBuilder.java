package com.niuti.fuyuan.headwords.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.niuti.fuyuan.headwords.R;

/**
 * Created by fuyuan on 2015/6/30.
 */
public class TitleBuilder {

    private View viewTitle;
    private TextView tvTitle;

    public TitleBuilder(Activity context) {
        viewTitle = context.findViewById(R.id.rl_titlebar);
        tvTitle = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
    }

    public TitleBuilder(View context) {
        viewTitle = context.findViewById(R.id.rl_titlebar);
        tvTitle = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
    }

    // title

    public TitleBuilder setTitleBgRes(int resid) {
        viewTitle.setBackgroundResource(resid);
        return this;
    }

    public TitleBuilder setTitleText(String text) {
        tvTitle.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        tvTitle.setText(text);
        return this;
    }

    public View build() {
        return viewTitle;
    }
}
