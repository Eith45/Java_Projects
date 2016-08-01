/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 10.10.14
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class Book implements IntKeyed {
    private int isbn;
    private String title;
    private String author;


    public Book(String title, String author, int isbn){
        if(isbn < 0){
            try {
                throw new Exception("Value: " + isbn + " is not positive");
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }else{
            setKey(isbn);
        }
        setTitle(title);
        setAuthor(author);

    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }


    public String toString(){
        return getKey() + " " + getTitle() + " " + getAuthor();
    }


    public boolean equals(Object obj){
        if(obj == null){return false;}
        if(obj == this){return true;}
        if(!(obj instanceof Book)){return false;}
        Book b = (Book) obj;
        return Integer.compare(b.getKey(), this.getKey()) == 0 &&
                this.getTitle() == b.getTitle() &&
                this.getAuthor() == b.getAuthor();
    }
    @Override
    public int getKey() {
        return isbn;
    }

    @Override
    public boolean setKey(int key) {
        if(key > 0){
            isbn = key;
            return true;
        }
        return false;
    }


}
