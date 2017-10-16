package biblioteka;

/**
 * Created by Pro808 on 15.10.2017.
 */
public class itemBook {

    public String nameBook;
    public String pathBook;

    public String nameUser;

    public itemBook(String nameUser, String nameBook, String pathBook)
    {
        this.nameUser = nameUser;
        this.nameBook = nameBook;
        this.pathBook = pathBook;
    }

    @Override
    public String toString() {
        String[] temp = this.nameBook.split("[.]");
        return temp[0];
    }
}
