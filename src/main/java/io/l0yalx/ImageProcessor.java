package io.l0yalx;

public class ImageProcessor implements Runnable{
    private String imageUrl;
    private long twitterId;
    
    public ImageProcessor(long twitterId, String imageUrl){
    	this.imageUrl = imageUrl;
    	this.twitterId = twitterId;
    }

	public void run(){
		try{
		String json = ImageAnalyzer.analyse(imageUrl);
		if (json != null){
			boolean res = ImageAnalyzerResponseReader.analyse(json);
			if (res){
				RewardSender.send(twitterId);
			}
		}
		}catch (Exception ex){}
	}
}
