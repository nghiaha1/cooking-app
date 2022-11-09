package t2010a.cookpad_clone.event;


import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.shop.Product;

public class MessageEvent {

    public static class ProductEvent {
        private Product product;

        public ProductEvent(Product product) {
            this.product = product;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }
    }

    public static class PostEvent {
        private Post post;

        public PostEvent(Post post) {
            this.post = post;
        }

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }
    }

}
