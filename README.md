# SocialMediaAI
This repo contains Twitter and Image Processing code for Social Media Reward Campaigns

# Idea
Allows to run campaigns in Social Media, such as Twitter. Customer incentives audience to post tweets with a particular text and media content identified by a given hashtag. Both, tweet text and media attachment are analysed to make sure that the post follows the rules set for the campaign, that is posts from a particular location or indeed distributes a photo of a required object. 

# Installation
mvn compile


mvn assembly:single

# Running the code
java -jar target/TweetAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar

# Requirements
ImageAnalyzer.java:
private static final String API_KEY = "Google Vision API Key"; 

TweetAnalyzer.java:

        .setOAuthConsumerKey("*********************")

        .setOAuthConsumerSecret("******************************************")

        .setOAuthAccessToken("**************************************************")

        .setOAuthAccessTokenSecret("******************************************");

UUIDDB:
uuids.add(1l);

# Code

ImageAnalyzer.java - class that contains main image analysis routine; here image is OCRed and image colour analysis is done

ImageAnalyzerResponseReader.java - class that contains main logic of image acceptance for the campaign, its analyse(String) function decides whether the image corresponds to the campaign criteria; at the moment analyse(String) method parses json output of Google Vision to make a decision about the image acceptance 

ImageProcessor.java - thread that runs image processing routines

RewardSender.java - code that connects the system to blockchain via loaylX API 

TweetAnalyzer.java - code that constantly listens to tweets from twitter and selects those that have a hashtag of the campaign and come from the people registered with loyalX

UUIDDB.java - class that substitutes a DB with loyalX user information