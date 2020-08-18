public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_name="db4";
    public static final String Table_name="tb5";
    public static final String Col1="ID";
    public static final String Col2="Customer_Name";
    public static final String Col3="Phone_Number";
    public static final String Col4="Sabji_Name";
    public static final String Col5="Sabji_Cost";
    public static final String Col6="Number_Roti";
    public static final String Col7="Cost_Roti";
    public static final String Col8="Extra_Cost";
    public static final String Table2_name="tb6";
    public static final String regCol1=" ID ";
    public static final String regCol2="username";
    public static final String regCol3="password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String customer_table="CREATE TABLE "+Table2_name+"( ID INTEGER PRIMARY KEY AUTOINCREMENT,"+"username TEXT NOT NULL,password TEXT NOT NULL)";
        String createtable="CREATE TABLE "+Table_name+"( ID INTEGER PRIMARY KEY AUTOINCREMENT,"+"Customer_Name TEXT, Phone_Number INTEGER, Sabji_Name TEXT, Sabji_Cost INTEGER, Number_Roti INTEGER,Cost_Roti INTEGER,Extra_Cost INTEGER)";
        db.execSQL(customer_table);
        db.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP  TABLE IF EXISTS "+Table_name);
        db.execSQL("DROP TABLE IF EXISTS "+ Table2_name);
        onCreate(db);
    }
    public long addUser(String user,String password){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(regCol2,user);
        contentValues.put(regCol3,password);
        long res=database.insert(Table2_name,null,contentValues);
       return res;
    }
    public boolean updateDat(String ID,String Customer_Name,String Phone_Number,String Sabji_Name,String Sabji_Cost,String Number_Roti,String Cost_Roti,String Extra_Cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1, ID);
        contentValues.put(Col2, Customer_Name);
        contentValues.put(Col3, Phone_Number);
        contentValues.put(Col4, Sabji_Name);
        contentValues.put(Col5, Sabji_Cost);
        contentValues.put(Col6, Number_Roti);
        contentValues.put(Col7, Cost_Roti);
        contentValues.put(Col8, Extra_Cost);
        db.update(Table_name, contentValues, "ID = ?", new String[]{ID});
        return true;

    }
    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(Table_name,"ID = ?",new String[] {id});
    }
    public boolean checkUser(String username,String password)
    {
        String[] columns={regCol1};
        SQLiteDatabase database=getReadableDatabase();
        String selection=regCol2 + "=?" + " and " + regCol3 + "=?";
        String[] selectionargs={username,password};
        Cursor cursor=database.query(Table2_name,columns,selection,selectionargs,null,null,null);
        int count=cursor.getCount();
        cursor.close();
        database.close();
        if(count > 0)
        {
            return  true;
        }else {
            return false;
        }

    }
    public boolean insertData(String Customer_Name, String Phone_Number, String Sabji_Name, String Sabji_Cost, String Number_Roti, String Cost_Roti, String Extra_Cost){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,Customer_Name);
        contentValues.put(Col3,Phone_Number);
        contentValues.put(Col4,Sabji_Name);
        contentValues.put(Col5,Sabji_Cost);
        contentValues.put(Col6,Number_Roti);
        contentValues.put(Col7,Cost_Roti);
        contentValues.put(Col8,Extra_Cost);

        long result=database.insert(Table_name,null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor showData(){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor data=database.rawQuery("Select * from "+ Table_name ,null);
        return data;
    }
}
