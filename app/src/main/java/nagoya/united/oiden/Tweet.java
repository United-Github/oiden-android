package nagoya.united.oiden;


import android.graphics.Bitmap;

public class Tweet {
        long id;
        String name;
        String tweet;
        Bitmap icon;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTweet() {
            return tweet;
        }

        public void setTweet(String tweet) {
            this.tweet = tweet;
        }

        public Bitmap getIcon(){
            return icon;
        }

        public void setIcon(Bitmap icon){
            this.icon=icon;
        }
    }