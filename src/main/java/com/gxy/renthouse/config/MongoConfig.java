package com.gxy.renthouse.config;/*
 * Created by GXY on 2019/2/15
 */

//package com.dhao.data.config;
///* * Created by GXY on 2018/10/16*/
//
//

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program:data
 * @description:
 * @author:gaoxiangyu
 * @since:2018-10-16 19:51
 **/

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {


    @Value ( "${spring.data.mongodb.host}" )
    private String mongodbHost;

    @Value ( "${spring.data.mongodb.port}" )
    private int mongodbPort;

    @Value ( "${spring.data.mongodb.database}" )
    private String mongodbName;

    @Value ( "${spring.data.mongodb.username}" )
    private String mongodbUser;

    @Value ( "${spring.data.mongodb.password}" )
    private String mongodbpwd;

    @Value ( "${spring.data.mongodb.authentication-database}" )
    private boolean authentification;

    @Bean
    @Override
    public MongoTemplate mongoTemplate ( ) throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate ( mongo ( ) , mongodbName );
        return mongoTemplate;
    }


    @Override
    protected String getDatabaseName ( ) {
        return mongodbName;
    }

    @Override
    public Mongo mongo ( ) throws Exception {
        MongoClient mongoClient;
        MongoCredential credential = MongoCredential.createMongoCRCredential ( mongodbUser , mongodbName , mongodbpwd.toCharArray ( ) );
        MongoClientOptions options = MongoClientOptions.builder ( )
                .connectionsPerHost ( 3000 )
                .threadsAllowedToBlockForConnectionMultiplier ( 10 )
                .readPreference ( ReadPreference.nearest ( ) )
                .build ( );
        List < ServerAddress > addresses = new ArrayList < ServerAddress > ( );
        String[] str = this.mongodbHost.split ( "," );
        for ( String strHost : str ) {
            ServerAddress address = new ServerAddress ( strHost , mongodbPort );
            addresses.add ( address );
        }
        if ( authentification ) {
            mongoClient = new MongoClient ( addresses , Arrays.asList ( credential ) , options );
        } else {
            mongoClient = new MongoClient ( addresses , options );
        }

        return mongoClient;
    }
}
