package com.yaneryi.permissionutil.safe;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * 获取序列化数时增加保护的Intent
 * Created by clude on 2017/11/30.
 */

public class SafeIntent extends Intent{

    private static final String TAG = "SafeIntent";

    /**
     * [构造简要说明]
     *
     * @param intent 原始intent
     */
    public SafeIntent(Intent intent) {
        super(intent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Parcelable> T getParcelableExtra(String name) {
        try {
            return super.getParcelableExtra(name);
        } catch (Exception e) {
            Log.w(TAG, "getParcelableExtra failed.", e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStringExtra(String name) {
        try {
            return super.getStringExtra(name);
        } catch (Exception e) {
            Log.w(TAG, "getStringExtra failed.", e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getBooleanExtra(String name, boolean defaultValue) {
        try {
            return super.getBooleanExtra(name, defaultValue);
        } catch (Exception e) {
            Log.w(TAG, "getBooleanExtra failed.", e);
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIntExtra(String name, int defaultValue) {
        try {
            return super.getIntExtra(name, defaultValue);
        } catch (Exception e) {
            Log.w(TAG, "getIntExtra failed.", e);
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getByteExtra(String name, byte defaultValue) {
        try {
            return super.getByteExtra(name, defaultValue);
        } catch (Exception e) {
            Log.w(TAG, "getByteExtra failed.", e);
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharSequence getCharSequenceExtra(String name) {
        try {
            return super.getCharSequenceExtra(name);
        } catch (Exception e) {
            Log.w(TAG, "getCharSequenceExtra failed.", e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char getCharExtra(String name, char defaultValue) {
        try {
            return super.getCharExtra(name, defaultValue);
        } catch (Exception e) {
            Log.w(TAG, "getCharExtra failed.", e);
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLongExtra(String name, long defaultValue) {
        try {
            return super.getLongExtra(name, defaultValue);
        } catch (Exception e) {
            Log.w(TAG, "getLongExtra failed.", e);
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloatExtra(String name, float defaultValue) {
        try {
            return super.getFloatExtra(name, defaultValue);
        } catch (Exception e) {
            Log.w(TAG, "getFloatExtra failed.", e);
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDoubleExtra(String name, double defaultValue) {
        try {
            return super.getDoubleExtra(name, defaultValue);
        } catch (Exception e) {
            Log.w(TAG, "getDoubleExtra failed.", e);
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Serializable getSerializableExtra(String name) {
        try {
            return super.getSerializableExtra(name);
        } catch (Exception e) {
            Log.w(TAG, "getSerializableExtra failed.", e);
            return null;
        }
    }
    
}
