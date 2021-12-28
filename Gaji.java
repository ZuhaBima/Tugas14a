import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;

public class Gaji implements PTABC, Database
{
    static Connection conn;
	String link = "jdbc:mysql://localhost:3306/db_karyawan";

    Integer nomorPegawai;
    String namaPegawai;
    Integer gajiPokok;
    Integer jabatan;
    
    Scanner input = new Scanner(System.in);
    public Gaji (Integer nomorPegawai, String namaPegawai,Integer jabatan){
        this.nomorPegawai = nomorPegawai;
        this.namaPegawai = namaPegawai;
        this.jabatan = jabatan;
    }
    Scanner pegawai = new Scanner(System.in);

    @Override
    public void NoPegawai(){
    System.out.print("\n No pegawai = ");
    nomorPegawai = pegawai.nextInt();
    }
    
    @Override
    public void NamaPegawai(){
    System.out.print("\nNama pegawai = " );
    namaPegawai = pegawai.next();  
    namaPegawai = namaPegawai.toUpperCase();  
    namaPegawai = namaPegawai.trim();
    }

    @Override
    public void Jabatan()
    {
        System.out.println("__________________________________________");
        System.out.println("\nINPUTKAN JABATAN ANDA MENGGUNAKAN ANGKA");
        System.out.println("__________________________________________");
        System.out.println("1. Direktur");
        System.out.println("2. Manager");
        System.out.println("3. Pegawai");
        System.out.println("4. Magang");
        System.out.println("5. Security");
        System.out.println("__________________________________________");
        System.out.print("\n Input Jabatan = ");
        
         jabatan = pegawai.nextInt();
    }

    public void GajiPokok(){
    }

    public void JumlahHariMasuk(){

    }

    public void Potongan(){

    }

    public void TotalGaji(){

    }

    @Override
    public void view()throws SQLException 
    {
        System.out.println("Daftar seluruh pegawai");
        String sql = "SELECT * FROM karyawan";
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
        
        while (result.next())
        {
            System.out.print("\nNo pegawai\t\t: ");
            System.out.print(result.getString("nomor"));
            System.out.print("\nNama pegawai\t\t: ");
            System.out.print(result.getString("nama"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("jabatan"));
            System.out.print("\nKehadiran\t\t: ");
            System.out.print(result.getInt("jmlHadir"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.println(result.getInt("totalGaji"));
        }
    }

    public void update() throws SQLException 
    {
        
    }

    public void delete() throws SQLException
    {
        
    }

    
    public void save() throws SQLException
    {
        
    }

    public void search() throws SQLException
    {
        
    }

}
