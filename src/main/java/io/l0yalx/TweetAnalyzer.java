package io.l0yalx;

import java.io.IOException;

import twitter4j.FilterQuery;
import twitter4j.MediaEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetAnalyzer {
    public static void main (String[] args) throws TwitterException, IOException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey("*********************")
        .setOAuthConsumerSecret("******************************************")
        .setOAuthAccessToken("**************************************************")
        .setOAuthAccessTokenSecret("******************************************");
        //TwitterFactory tf = new TwitterFactory(cb.build());
        //Twitter twitter = tf.getInstance();
        StatusListener listener = new StatusListener(){
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
            @Override
            public void onScrubGeo(long arg0, long arg1) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            @Override
            public void onStallWarning(StallWarning arg0) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            public void onStatus(Status status) {
                long userId = status.getUser().getId();
                String userName = status.getUser().getName();
                String tweet = status.getText();
                
                if (!UUIDDB.getInstance().isKnownUUID(userId)){
                    return;
                }

                System.out.println("[EVENT] " + userId + " " + tweet);
                
                MediaEntity[] media = status.getMediaEntities(); //get the media entities from the status
                if (media == null || media.length == 0){
                    System.out.println("[EVENT] Empty Media");
                }
                for(MediaEntity m : media){ //search trough your entities
                    System.out.println("[EVENT] found attachment " + m.getMediaURL());
                    Thread imageProcessorThread = new Thread(new ImageProcessor(status.getUser().getId(), m.getMediaURL()));
                    imageProcessorThread.start();
                }
            }
        };
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(listener);
        FilterQuery query = new FilterQuery();
        query.track(new String[]{"#LoyalXGreenUAECampaign"});
        twitterStream.filter(query);    
    }
}
