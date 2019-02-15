package com.gxy.renthouse.entity;/*
 * Created by GXY on 2019/2/15
 */

import com.gxy.renthouse.utils.CharsetDetector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @program:renthouse
 * @description:
 * @author:gaoxiangyu
 * @since:2019-02-15 13:45
 **/
public class Page {

    private byte[] content;
    private String html;
    private Document doc;
    private String charset;
    private String url;
    private String contentType;

    public Page ( byte[] content , String url , String contentType ) {
        this.content = content;
        this.url = url;
        this.contentType = contentType;
    }

    public byte[] getContent ( ) {
        return content;
    }

    public void setContent ( byte[] content ) {
        this.content = content;
    }

    public String getHtml ( ) {
        if ( html != null ) {
            return html;
        }
        if ( content == null ) {
            return null;
        }
        if ( charset == null ) {
            charset = CharsetDetector.guessEncoding ( content );
        }
        try {
            this.html = new String ( content , charset );
            return html;
        } catch ( Exception e ) {
            e.printStackTrace ( );
            return null;
        }
    }

    public void setHtml ( String html ) {
        this.html = html;
    }

    public Document getDoc ( ) {
        if ( doc != null ) {
            return doc;
        }
        try {
            this.doc = Jsoup.parse ( getHtml ( ) , url );
            return doc;
        } catch ( Exception e ) {
            e.printStackTrace ( );
            return null;
        }
    }

    public void setDoc ( Document doc ) {
        this.doc = doc;
    }

    public String getCharset ( ) {
        return charset;
    }

    public void setCharset ( String charset ) {
        this.charset = charset;
    }

    public String getUrl ( ) {
        return url;
    }

    public void setUrl ( String url ) {
        this.url = url;
    }

    public String getContentType ( ) {
        return contentType;
    }

    public void setContentType ( String contentType ) {
        this.contentType = contentType;
    }
}
