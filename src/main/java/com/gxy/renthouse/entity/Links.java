package com.gxy.renthouse.entity;/*
 * Created by GXY on 2019/2/15
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @program:renthouse
 * @description:
 * @author:gaoxiangyu
 * @since:2019-02-15 14:00
 **/
public class Links {
    private static Set visitedUrl = new HashSet ( );

    private static LinkedList enVisitedUrl = new LinkedList ( );

    public static int getVisitedNum ( ) {
        return visitedUrl.size ( );
    }

    public static void addVisitedNum ( String url ) {
        visitedUrl.add ( url );
    }

    public static void removeVisitedUrl ( String url ) {
        visitedUrl.remove ( url );
    }

    public static LinkedList getEnVisitedUrl ( ) {
        return enVisitedUrl;
    }

    public static void addEnvisitedUrl ( String url ) {
        if ( url != null && ! url.trim ( ).equals ( "" ) && ! visitedUrl.contains ( url ) && ! enVisitedUrl.contains ( url ) ) {
            enVisitedUrl.add ( url );
        }
    }

    public static Object removeHeadOfEnvistedUrl ( ) {
        return enVisitedUrl.removeFirst ( );
    }

    public static boolean envisitedUrlIsEmpty ( ) {
        return enVisitedUrl.isEmpty ( );
    }
}
