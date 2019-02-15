package com.gxy.renthouse.service;/*
 * Created by GXY on 2019/2/15
 */

import com.gxy.renthouse.entity.Page;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @program:renthouse
 * @description:
 * @author:gaoxiangyu
 * @since:2019-02-15 14:16
 **/
public class RequestAndResponseService {
    public static Page sendRequestAndGetResponse ( String url ) {
        Page page = null;
        HttpClient httpClient = new HttpClient ( );
        httpClient.getHttpConnectionManager ( ).getParams ( ).setConnectionTimeout ( 5000 );
        GetMethod getMethod = new GetMethod ( url );
        getMethod.getParams ( ).setParameter ( HttpMethodParams.SO_TIMEOUT , 5000 );
        getMethod.getParams ( ).setParameter ( HttpMethodParams.RETRY_HANDLER , new DefaultHttpMethodRetryHandler ( ) );
        try {
            int statusCode = httpClient.executeMethod ( getMethod );
            if ( statusCode != HttpStatus.SC_OK ) {
                System.err.println ( "Method failed:" + getMethod.getStatusLine ( ) );
            }
            byte[] responseBody = getMethod.getResponseBody ( );
            String contentType = getMethod.getResponseHeader ( "Content-Type" ).getValue ( );
            page = new Page ( responseBody , url , contentType );
        } catch ( Exception e ) {
            System.out.println ( "Please check your provided http address" );
            e.printStackTrace ( );
        } finally {
            getMethod.releaseConnection ( );
        }
        return page;
    }


}
