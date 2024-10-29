package persistence;

public class SongDaoImpl extends MySQLDao implements SongDao {
    public SongDaoImpl() {
        super();
    }

    public SongDaoImpl(String propFilename) {
        super(propFilename);
    }

}
