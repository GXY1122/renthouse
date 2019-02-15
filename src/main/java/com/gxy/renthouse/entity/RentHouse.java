package com.gxy.renthouse.entity;/*
 * Created by GXY on 2019/2/15
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @program:renthouse
 * @description:
 * @author:gaoxiangyu
 * @since:2019-02-15 11:52
 **/
public class RentHouse {
    SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );

    private String id = UUID.randomUUID ( ).toString ( );
    private String url;
    private String content;
    private String time = sdf.format ( new Date ( ) );
    private String datafrom;

    public String getUrl ( ) {
        return url;
    }

    public void setUrl ( String url ) {
        this.url = url;
    }

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getContent ( ) {
        return content;
    }

    public void setContent ( String content ) {
        this.content = content;
    }

    public String getTime ( ) {
        return time;
    }

    public void setTime ( String time ) {
        this.time = time;
    }

    public String getDatafrom ( ) {
        return datafrom;
    }

    public void setDatafrom ( String datafrom ) {
        this.datafrom = datafrom;
    }
}
