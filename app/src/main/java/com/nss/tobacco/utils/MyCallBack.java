package com.nss.tobacco.utils;

import org.xutils.common.Callback;

public class MyCallBack<ResultType> implements Callback.CommonCallback<ResultType>{

	@Override
	public void onSuccess(ResultType result) {

	}

	@Override
	public void onError(Throwable ex, boolean isOnCallback) {

	}

	@Override
	public void onCancelled(CancelledException cex) {
		
	}

	@Override
	public void onFinished() {
		
	}


}
