public class Vicky {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

         */
        print_line();
        System.out.println("Hello! I'm Vicky\nWhat can I do for you?");
        print_line();
        System.out.println("Bye! Hope to see you again soon!");
        print_line();
    }

    public static void print_line() {
        int length = 40;
        System.out.println("_".repeat(length));
    }
}
