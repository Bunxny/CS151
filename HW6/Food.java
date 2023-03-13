public class Food {
        protected int day;
        protected String product;
        protected int amount;
        protected float price;
        
        // constructor takes in 5 strings and puts them equal to respected
        //instances/varables
        public Food(int day, String product, int amount, float price) {
            this.day = day;
            this.product = product;
            this.amount = amount;
            this.price = price;
        }
    
        //Rearranges and prints out values
        @Override
        public String toString() {
            return day + " " + product + " " + amount + " " + price;
        }
}
