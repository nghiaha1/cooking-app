package t2010a.cookpad_clone.event;


import t2010a.cookpad_clone.model.client_model.Content;
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
        private Content post;

        public PostEvent(Content post) {
            this.post = post;
        }

        public Content getPost() {
            return post;
        }

        public void setPost(Content post) {
            this.post = post;
        }
    }

}
