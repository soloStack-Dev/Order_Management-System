void main() {
    var input = new Scanner(System.in);

    IO.println("Enter a integer:");
    var num = input.nextInt();

    var r = new ReverseInteger();
    IO.println(r.reverse(num));

}

class ReverseInteger{
    int reverse(int x){
        var rev = 0;

        while(x != 0){
            var d = x % 10;
            x /= 10;

            var next = rev * 10 + d;
            if((next - d)/10 != rev) return 0;

            rev = next;
        }
        return  rev;
    }
}