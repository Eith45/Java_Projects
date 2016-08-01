/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 09.10.14
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */


public class MyArrayList<E extends IntKeyed>{

    /*to do list
    + findItemPos(item : E): Integer
    + findItem(item : E)   : boolean
    + enlarge()
    + removeItem(item : E) : boolean
    + clear()
    + addItem(item : E)
    + isEmpty()            : boolean
    + lenghIs()            : Integer
    + toString()           : String
    -+ equals(MyArrayList<E> otherList) : boolean
    *
    * */

    private final int DEFCAP = 50;
    private int origCap;
    private int numElements = 0;
    private E [] List;

    public MyArrayList(){
        List = (E[])new IntKeyed[DEFCAP];
        origCap = DEFCAP;
    }

    public MyArrayList(int size){
        if(size > 0){
            List = (E[])new IntKeyed[size];
            origCap = size;
        }else if(size <= 0){
            try {
                throw new Exception("the list size is invalid, it has to be larger than zero");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEmpty(){
        if(numElements == 0){
            return true;
        }
        return false;
    }

    public int lenghtIs(){
        return numElements;
    }
    public String toString(){
        String temp = "";
        for(int i = 0; i < numElements; i++){
            temp += List[i].toString() + " \n";
        }
        return temp;
    }

    public void clear(){
        numElements = 0;
        List = (E[]) new IntKeyed[origCap];
    }

    public void addItem(E item){
        if(numElements == origCap){
            enlarge();
        }
        List[numElements++] = item;
    }

    public Object getLastItem(){
        return List[numElements-1];
    }


    public boolean findItem(E item){
        for(Object obj : List){
            if(obj.equals(item)){
                return true;
            }
        }
        return false;
    }

    public int findItemPos(E item){
        for(int i = 0; i < origCap; i++){
            if(List[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    public boolean removeItem(E item){
        int itemPos = findItemPos(item);
        if(findItem(item)){
            Object lastItem = getLastItem();
            List[itemPos] = (E) lastItem;
            numElements--;
            return true;
        }else{
            return false;
        }
    }

    private void enlarge() {
        E  [] larger = (E[]) new IntKeyed[List.length + origCap];
        for(int i = 0; i < List.length; i++){
            larger[i] = List[i];
        }
        List =  larger;
    }
    public boolean equals(MyArrayList<E> anotherList){
        boolean areEqual = true;
        if((anotherList == null) || (List.length != anotherList.List.length)){
            areEqual = false;
        }else if(anotherList == this)
            areEqual = true;
        else{
            int x = 0;
            while(x < List.length && areEqual){
                if(!List[x].equals(anotherList.List[x])){
                    areEqual = false;
                }
                x++;
            }
        }
        return areEqual;
    }
    @SuppressWarnings("unchecked")
    public E get(int i){
        if (i>= numElements || i <0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (E) List[i];

    }

}
