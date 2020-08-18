public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button button1,button2,button3,button4;
    EditText eCustomer_name,ePhone_Number,eSabji_Name,eSabji_Cost,eNumber_Roti,eCost_Roti,eExtra_Cost,eid;
    TextView eTotal_Cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this);
        eCustomer_name=(EditText)findViewById(R.id.customer_name);
        ePhone_Number=(EditText)findViewById(R.id.phone);
        eSabji_Name=(EditText)findViewById(R.id.sabji);
        eSabji_Cost=(EditText)findViewById(R.id.costsabji);
        eNumber_Roti=(EditText)findViewById(R.id.num_roti);
        eCost_Roti=(EditText)findViewById(R.id.costroti);
        eExtra_Cost=(EditText)findViewById(R.id.extracost);
        eTotal_Cost=(TextView) findViewById(R.id.total_cost);
        eid=(EditText)findViewById(R.id.idfor);
        button1=(Button)findViewById(R.id.add_data);
        button2=(Button)findViewById(R.id.show_data);
        button3=(Button)findViewById(R.id.update);
        button4=(Button)findViewById(R.id.delete);
        AddData();
        ViewData();
        updateData();
        DeleteData();
    }

    private void updateData() {
    button3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
int temp=eid.getText().toString().length();
if(temp > 0)
{
boolean update=databaseHelper.updateDat(eid.getText().toString(),eCustomer_name.getText().toString(),ePhone_Number.getText().toString(),eSabji_Name.getText().toString(),eSabji_Cost.getText().toString(),eNumber_Roti.getText().toString(),eCost_Roti.getText().toString(),eExtra_Cost.getText().toString());
if (update==true)
{
    Toast.makeText(MainActivity.this,"Updated the Records",Toast.LENGTH_LONG).show();
}
else{
    Toast.makeText(MainActivity.this,"Something went Wrong",Toast.LENGTH_LONG).show();
}
}else{
    Toast.makeText(MainActivity.this,"Must Enter ID To update",Toast.LENGTH_LONG).show();
}
        }
    });
    }
private void DeleteData(){
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=eid.getText().toString().length();
                if(temp > 0){
Integer deleteRow=databaseHelper.deleteData(eid.getText().toString());
if (deleteRow > 0){
    Toast.makeText(MainActivity.this,"Successfully deleted ",Toast.LENGTH_LONG).show();
}
                }
                else{
                    Toast.makeText(MainActivity.this,"You must enter the id to delete ",Toast.LENGTH_LONG).show();
                }

            }
        });
}

    public void AddData(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long n1=0,n2=0,n3=0;
if(eSabji_Cost.length()==0||eCost_Roti.length()==0||eExtra_Cost.length()==0){
    Toast.makeText(MainActivity.this,"You must write every cost to continue ",Toast.LENGTH_LONG).show();

}else{

                String Customer_Name=eCustomer_name.getText().toString();
                String Phone_Number=ePhone_Number.getText().toString();
                String Sabji_Name=eSabji_Name.getText().toString();
                String Sabji_Cost=eSabji_Cost.getText().toString();
                String Number_Roti=eNumber_Roti.getText().toString();
                String Cost_Roti=eCost_Roti.getText().toString();
                String Extra_Cost=eExtra_Cost.getText().toString();
                n1=Integer.parseInt(eSabji_Cost.getText().toString());
                n2=Integer.parseInt(eCost_Roti.getText().toString());
                n3=Integer.parseInt(eExtra_Cost.getText().toString());

                long sum=n1+n2+n3;
                eTotal_Cost.setText(String.valueOf(sum));
                boolean insertData=databaseHelper.insertData(Customer_Name,Phone_Number,Sabji_Name,Sabji_Cost,Number_Roti,Cost_Roti,Extra_Cost);
                if (insertData==true){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Something Wrong",Toast.LENGTH_LONG).show();
                }}
            }
        });
    }
    public void ViewData() {
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent=new Intent(MainActivity.this,View_List.class);
                                           startActivity(intent);
                                       }
                                   }
        );
    }

}
