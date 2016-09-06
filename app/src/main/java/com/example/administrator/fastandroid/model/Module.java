package com.example.administrator.fastandroid.model;

import com.example.administrator.fastandroid.base.BaseActivity;

/**
 * Created by zhatong
 * on 2016/9/1 15:58
 */
public class Module {
    public String moduleName;
    public String moduleIcon;
    public Class<? extends BaseActivity> moduleTargetClass;

    public Module(String moduleName, Class<? extends BaseActivity> moduleTargetClass){
        this.moduleName=moduleName;
        this.moduleTargetClass=moduleTargetClass;
    }




}
