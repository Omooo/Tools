package com.example.ssc.tools.ToolsActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ssc.tools.R;


public class ToolsVideoList extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;

    private String[] videosUrl = new String[]{"http://live-tx-hdl.huomaotv.cn/live/9gFWYZ24081.m3u8",
                        "http://live-tx-hdl.huomaotv.cn/live/4bgtCR24082.m3u8",
                        "http://live-tx-hdl.huomaotv.cn/live/2inGSW24083.m3u8",
                        "http://117.144.248.49/HDhnws.m3u8?authCode=07110409322147352675&amp;stbId=006001FF0018120000060019F0D49A1&amp;Contentid=6837496099179515295&amp;mos=jbjhhzstsl&amp;livemode=1&amp;channel-id=wasusyt",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240127_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240244_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240259_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240005_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240260_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240261_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240009_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240262_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240014_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240015_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240016_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240017_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240126_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240085_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240086_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240011_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240012_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240013_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240004_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240024_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240026_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240050_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240051_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240159_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240006_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240089_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240090_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240091_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240092_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240095_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240276_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240277_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240278_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240279_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240303_1/encoder/0/playlist.m3u8",
                        "http://125.88.39.22/live.aishang.ctlcdn.com/00000110240309_1/encoder/0/playlist.m3u8"};

    private String[] videosTitle = new String[]{"电影频道 1", "电影频道 2", "电影频道 3","湖南卫视",
            "CCTV 1-综合","CCTV 2-财经","CCTV 3-综艺","CCTV 4-中文国际","CCTV 5-体育","CCTV 6-电影",
            "CCTV 7-军事农业","CCTV 8-电视剧","CCTV 9-纪录","CCTV 10-科教","CCTV 11-戏曲","CCTV 12-社会与法",
            "CCTV 13-新闻","CCTV 14-少儿","CCTV 15-音乐","东方卫视","重庆卫视","浙江卫视","深圳卫视","安徽卫视",
            "北京卫视","金鹰卡通","卡酷少儿","嘉佳卡通","IPTV相声小品","IPTV热播剧场","IPTV经典电影","IPTV魅力时尚",
            "IPTV少儿动画","IPTV野外","CNTV喜剧影院","CNTV动作影院","CNTV家庭影院","CNTV星影","金鹰纪实FHD","CNTV美妆"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_video_list);

        mListView = (ListView) findViewById(R.id.lv_video);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.tools_video_item, videosTitle);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "position: " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ToolsVideoList.this, ToolsPlayVideoActivity.class);
        intent.putExtra("url", videosUrl[position]);
        intent.putExtra("title", videosTitle[position]);
        startActivity(intent);
    }
}
