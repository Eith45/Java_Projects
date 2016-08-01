import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 10.10.14
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
public class LibraryManager {
    public static void main(String[] args)
    {
        // Declare and instantiate the library lists
        MyArrayList<Book> onLoanBooks; // List of books owned by the library
        try {
            onLoanBooks = new MyArrayList<Book>(5);     // List of books available for checkout
        }
        catch (Exception e) {
            System.out.println ("Size of list is invalid - Creating default list");
            onLoanBooks = new MyArrayList<Book>();
        }

        MyArrayList<Book> availBooks;
        try {
            availBooks = new MyArrayList<Book>(10);     // List of books available for checkout
        }
        catch (Exception e) {
            System.out.println ("Size of list is invalid - Creating default list");
            availBooks = new MyArrayList<Book>();
        }
        MyArrayList<Member> members;
        try {
            members = new MyArrayList<Member>(5);// List of all members of the library
        }
        catch (Exception e) {
            System.out.println ("Size of list is invalid - Creating default list");
            members = new MyArrayList<Member>();
        }

        //Declare input Scanner object for user input
        Scanner input = new Scanner (System.in);

        // initialize the lists with sample values to facilliate testing
        initializeBooks(availBooks);
        initializeMembers(members);



        // Print the list of all books, all books on loan (none) and all members
        System.out.println ("---------------------------------------------------------");
        System.out.println ("All Available Books: \n" + availBooks);
        System.out.println ("---------------------------------------------------------");
        System.out.println ("All Books on Loan: \n" + onLoanBooks);
        System.out.println ("---------------------------------------------------------");
        System.out.println ("All Members: \n\n" + members);
        System.out.println ("---------------------------------------------------------");

        // Create the array of Strings containing the main menu options (Quit - option 0)
        // Create the mainMenu object
        String opts[] = {"Quit","Check Out Book", "Return Book", "List All Available Books",
                "List All Books Checked Out"};
        Menu mainMenu = new Menu(opts);

        // create various temporary variables - Add any other variables you might need
        int isbn = 0, memNum = 0;   // variables to hold user inputs
        Book theBook = null;       // temporary object for a book found in a list
        Member libMem = null;      // temporary object for a member found in the list
        boolean goodInput;  // for use in allowing the user to correct invalid inputs

        // while user wants to continue
        //   run the menu
        //   process the user's requested option
        int opt = 0;
        do {
            opt = mainMenu.runMenu();
            switch (opt) {
                case 1:
                    //check out book
                    // if no books available
                    // inform the user
                    // else
                    //    display all current members
                    //    get a member number from the user
                    //    - allow user to correct input if value input is not an integer
                    // find the member in the member list
                    // display all books available for checkout
                    // read user's choice for book to check out
                    //    - allow user to correct input if value input is not an integer
                    // if the desired book is available
                    //           checkout the book and remove it from the available books
                    //           add the book to the list of books on loan
                    //           if unable to check out the book report an error
                    if(availBooks.lenghtIs() == 0){
                        System.out.println("There is no avalaible books");
                    }else {
                        if (availBooks.lenghtIs() > 0) {
                            for (int i = 0; i < members.lenghtIs(); i++) {
                                System.out.println(members.get(i));
                            }
                            System.out.println("Input Your number, please:");
                            goodInput = false;
                            while (goodInput != true) {
                                String sIn = input.next();
                                if(isStringInt(sIn)){
                                    memNum = Integer.parseInt(sIn);
                                    goodInput = true;
                                }else{
                                    System.out.println("Input integer value, please");
                                }
                            }
                            boolean memberInList = false;
                            for(int i = 0; i < members.lenghtIs(); i++){
                                if(memNum == members.get(i).getKey()){
                                    memberInList = true;
                                    libMem = members.get(i);
                                }
                            }
                            if(!memberInList){
                                System.out.println("There is no such member in list");
                                break;
                            }

                            for(int i = 0; i < availBooks.lenghtIs(); i++){
                                System.out.println(availBooks.get(i));
                            }

                            System.out.println("Input the book's ID, please");
                            goodInput = false;
                            while (goodInput != true) {
                                String sIn = input.next();
                                if(isStringInt(sIn)){
                                    isbn = Integer.parseInt(sIn);
                                    goodInput = true;
                                }else{
                                    System.out.println("Input integer value, please");
                                }
                            }
                            boolean bookInList = false;
                            for(int i = 0; i < availBooks.lenghtIs(); i++){
                                if(isbn == availBooks.get(i).getKey()){
                                    bookInList = true;
                                    theBook = availBooks.get(i);
                                }
                            }
                            if(!bookInList){
                                System.err.println("There is no such book in list");
                                break;
                            }

                            if(libMem.checkOut(theBook, availBooks, onLoanBooks) == false){
                                System.err.println("Unable check out the book");
                            }else{
                                System.out.println("The book is given in the use");
                            }

                        }
                    }
                    break;
                case 2:

                    // return book
                    // display all current members
                    // ask the user for a member number
                    //    - allow user to correct input if value input is not an integer
                    // find the member in the member list
                    // display all books checked out by this member
                    // prompt for and read user's choice for book to return
                    //    - allow user to correct input if value input is not an integer
                    // find the book in the books-on-loan list
                    // if the desired book is found in the books-on-loan list
                    //      return the book
                    //      if unable to return the book report an error
                    System.out.println("Input Your number, please:");
                    for(int i = 0; i < members.lenghtIs(); i++){
                        System.out.println(members.get(i));
                    }
                    goodInput = false;
                    while (goodInput != true) {
                        String sIn = input.next();
                        if(isStringInt(sIn)){
                            memNum = Integer.parseInt(sIn);
                            goodInput = true;
                        }else{
                            System.out.println("Input integer value, please");
                        }
                    }
                    boolean memberInList = false;
                    for(int i = 0; i < members.lenghtIs(); i++){
                        if(memNum == members.get(i).getKey()){
                            memberInList = true;
                            libMem = members.get(i);
                        }
                    }
                    if(!memberInList){
                        System.err.println("There is no such member in list");
                        break;
                    }
                    libMem.printChekedOut();

                    System.out.println("Input the book's ID, please");
                    goodInput = false;
                    while (goodInput != true) {
                        String sIn = input.next();
                        if(isStringInt(sIn)){
                            isbn = Integer.parseInt(sIn);
                            goodInput = true;
                        }else{
                            System.err.println("Input integer value, please");
                        }
                    }

                    boolean bookInList = false;
                    for(int i = 0; i < onLoanBooks.lenghtIs(); i++){
                        if(isbn == onLoanBooks.get(i).getKey()){
                            bookInList = true;
                            theBook = onLoanBooks.get(i);
                        }
                    }
                    if(!bookInList){
                        System.err.println("There is no such book in list");
                        break;
                    }
                    if(libMem.returnBook(theBook, availBooks, onLoanBooks)){
                        System.out.println("The book is returned");
                    }else{
                        System.err.println("Unnable to return the book");
                    }
                    break;
                case 3: //List all available books and the total number of books available
                    for(int i = 0; i < availBooks.lenghtIs(); i++){
                        System.out.println(availBooks.get(i).toString());
                    }
                    break;
                case 4: // List all books checked out and the total number of books on loan
                    for(int i = 0; i < onLoanBooks.lenghtIs(); i++){
                        System.out.println(onLoanBooks.get(i).toString());
                    }
                    break;
                default:
                    System.out.println ("Thank you - Have a nice day!");
            }
        } while (opt > 0);

    }
    // The following two methods are provided to facilliate testing
    // NO CHANGES ARE NEEDED TO THIS METHOD
    // Method: initialzieBooks
    // Method to initialize the books in stock and the books owned
    // Initially all books are in stock for lending
    public static void initializeBooks(MyArrayList<Book> availBooks){
        //Populate books and stock MyArrayLists
        try{
            availBooks.addItem(new Book("Ender's Game", "Card, Orson Scott", 1000));
            availBooks.addItem(new Book("Breakfast of Champions", "Vonnegut, Kurt", 2000));
            availBooks.addItem(new Book("The Alphabet of Manliness", "Maddox", 3000));
            availBooks.addItem(new Book("A Condeferacy of Dunces", "Toole, John Kennedy", 4000));
            availBooks.addItem(new Book("Dune", "Herbert, Frank", 5000));
            availBooks.addItem(new Book("History of Western Philosophy", "Russell, Bertrand", 6000));
            availBooks.addItem(new Book("Choke", "Palahniuk, Chuck", 7000));
            availBooks.addItem(new Book("Me Talk Pretty One Day", "Sedaris, David", 8000));
            availBooks.addItem(new Book("House of Leaves", "Danielewski, Mark", 9000));
            availBooks.addItem(new Book("Eats, Shoots, & Leaves", "Truss, Lynne", 10000));
        }
        catch (Exception e) {
            System.out.println ("Error Creating Book: " + e);
        }
    }
    // NO CHANGES ARE NEEDED TO THIS METHOD
    // Method: initializeMembers (MyArrayList<Member> members)
    // Populate members MyArrayList
    public static void initializeMembers(MyArrayList<Member> members){
        try {
            members.addItem(new Member(11111,"Parker", "Peter"));
            members.addItem(new Member(22222,"Spector", "Marc"));
            members.addItem(new Member(33333,"Curry", "Arthur"));
            members.addItem(new Member(44444,"Stark", "Tony"));
            members.addItem(new Member(55555,"Queen", "Oliver"));

            // Adding this member will cause the 'enlarge' method to be called
            members.addItem(new Member(66666,"Smith", "Mary"));
        }
        catch (Exception e) {
            System.out.println ("Error creating member: " + e);
        }
    }//end main

    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

}
