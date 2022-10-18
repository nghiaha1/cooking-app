package t2010a.cookpad_clone.event;


import t2010a.cookpad_clone.model.home_client.Post;

public class MessageEvent {

    public static class MovieEvent {
        private Post post;

        public MovieEvent(Post post) {
            this.post = post;
        }

        public Post getMovie() {
            return post;
        }

        public void setMovie(Post post) {
            this.post = post;
        }
    }

}
