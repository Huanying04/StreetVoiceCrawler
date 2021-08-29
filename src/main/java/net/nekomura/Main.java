package net.nekomura;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("缺少參數：樂曲ID");
            return;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder rb = new Request.Builder().url("https://streetvoice.com/api/v4/song/"+args[0]+"/hls/file/");

        //inside cookies
        //rb.addHeader("x-csrftoken", args[1]);
        //rb.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36");

        RequestBody body = RequestBody.create("{}", MediaType.parse("application/json; charset=utf-8"));

        rb.method("POST", body);
        Response res = okHttpClient.newCall(rb.build()).execute();

        //a json with a value of m3u8 path in key which named "file"
        JSONObject fileJson = new JSONObject(res.body().string());

        res.close();

        System.out.println(fileJson.getString("file"));
    }
}
