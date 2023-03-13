public class Remove {
    String s;
    // public Remove(String s) {
    //     this.s = s;
    // }
    String removeNchar(int n, String s) {
        if (s==null) { return s; }
        if (n>0 && n<s.length()-1) {
            return s.substring(0,n);//+s.substring(n+1);
        }
        if (n==0)
            return s.substring(1);
        return s.substring(0, s.length()-1);
    }

    public static void main(String[] args) {
       Remove a = new Remove();
       System.out.println(a.removeNchar(4, args[0]));
    }
}
