package com.nekoscape.android.lib.view.alpha;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;

import com.nekoscape.android.lib.view.alpha.R;

public class AlphaDialogPreference extends DialogPreference implements
		SeekBar.OnSeekBarChangeListener {
	private final SharedPreferences sp;
	private SeekBar barA;
	private int defaultAlpha = 255;

	public AlphaDialogPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		sp = PreferenceManager.getDefaultSharedPreferences(context);
		// �_�C�A���O�̃��C�A�E�g���\�[�X�w��
		setDialogLayoutResource(R.layout.pref_dialog_alpha);
	}

	@Override
	protected void onBindDialogView(View v) {
		super.onBindDialogView(v);
		
		barA = (SeekBar)v.findViewById(R.id.seekbar);
		barA.setOnSeekBarChangeListener(this);
		
		barA.setProgress(getVal());
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		if (positiveResult) {
			setVal(barA.getProgress());
		}
	}
	
	private int getVal() {
		return sp.getInt(getKey(), defaultAlpha );
	}


	public void setVal(int value) {
		Editor editor = sp.edit();
		editor.putInt(getKey(), value);
		editor.commit();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
