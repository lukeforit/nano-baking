package com.rabbit.green.baking.app.data.source.net;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.RawRes;

import com.rabbit.green.baking.app.R;

import java.io.InputStream;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LocalInterceptor implements Interceptor {

    private final Context context;

    public LocalInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) {
        String json = jsonFileToString(R.raw.test);

        Response.Builder builder = new Response.Builder()
                .addHeader("content-type", "application/json")
                .code(200);
        if (json != null) {
            builder.body(ResponseBody.create(MediaType.parse("application/json"), json));
        } else {
            builder.body(ResponseBody.create(MediaType.parse("text"), "Empty body"));
        }
        return builder.message("Mock response")
                .protocol(Protocol.HTTP_1_0)
                .request(chain.request())
                .build();
    }

    private String jsonFileToString(@RawRes int resId) {
        try {
            Resources res = context.getResources();
            InputStream inputStream = res.openRawResource(resId);

            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            return new String(b);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

