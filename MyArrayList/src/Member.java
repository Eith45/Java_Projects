/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 10.10.14
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public class Member implements IntKeyed{
    private final int MAX_BOOKS = 3;
    private int memID;
    private String firstName;
    private String lastName;
    private MyArrayList<Book> chekedOut;

    public Member(int memID, String lastName, String firstName){
        if(memID < 0){
            try {
                throw new Exception("Value: " + memID + " is not positive");
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }else{
            setKey(memID);
        }
        try{
            chekedOut = new MyArrayList<Book>(MAX_BOOKS);
        }catch(Exception e){
            e.printStackTrace();
        }
        setFirstName(firstName);
        setLastName(lastName);

    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String toString(){
        return getKey() + " " + getLastName() + " " + getFirstName();
    }

    public boolean checkOut(Book book, MyArrayList<Book> availBooks, MyArrayList<Book> onLoan){
        if(chekedOut.lenghtIs() <= MAX_BOOKS){
            chekedOut.addItem(book);
            onLoan.addItem(book);
            availBooks.removeItem(book);
            return true;
        }
        return true;
    }
    public boolean returnBook(Book book, MyArrayList<Book> availBooks, MyArrayList<Book> onLoan){
        if(onLoan.findItem(book)){
            chekedOut.removeItem(book);
            onLoan.removeItem(book);
            availBooks.addItem(book);
            return true;
        }
        return false;
    }
    public void printChekedOut(){
        for(int i = 0; i < chekedOut.lenghtIs(); i++){
            System.out.println(chekedOut.get(i).toString());
        }
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Member)){return false;}
        if(obj == null){return false;}
        if(obj == this){return true;}
        Member m = (Member) obj;
        return Integer.compare(this.getKey(), m.getKey()) == 0 &&
                this.getFirstName() == m.getFirstName() &&
                this.getLastName() == m.getLastName();
        }


    @Override
    public int getKey() {
        return memID;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean setKey(int key) {
        if(key > 0){
            memID = key;
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
