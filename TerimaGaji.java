import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import java.util.InputMismatchException;

public class TerimaGaji extends Gaji{
    Integer totalGaji, jmlAbsen; 
    static Connection conn;
	String link = "jdbc:mysql://localhost:3306/db_karyawan"; 

    String namaJabatan;
    String nama;
    Integer potongan;
    Integer gajiPokok;
    Integer hariMasuk;
    Integer hariLibur;
    Integer gajitotal;
    
    Date datenow = new Date();
    SimpleDateFormat tgl = new SimpleDateFormat("E, dd/MM/yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss zzz");

    Scanner gaji = new Scanner(System.in);
    public TerimaGaji(Integer nomorPegawai, String namaPegawai, Integer jabatan, Integer hariMasuk, String namaJabatan){
        super(nomorPegawai,namaPegawai,jabatan);
        jabatan = this.jabatan;
        hariMasuk = this.hariMasuk;
        namaJabatan = this.namaJabatan;
    }
    @Override
    public void GajiPokok(){
    switch(jabatan){
        case 1 :
        System.out.println ("\n Jabatan anda adalah Direktur");
        gajiPokok=20000000;
        namaJabatan = "Direktur";
        namaJabatan = namaJabatan.toUpperCase();
        System.out.println("\nGaji pokok anda :" +gajiPokok);
        break;
        case 2 :
        System.out.println("\nJabatan anda adalah Manager");
        gajiPokok=16000000;
        namaJabatan = "Manager";
        namaJabatan = namaJabatan.toUpperCase();
        System.out.println("\nGaji pokok anda :" +gajiPokok);
        break;
        case 3 :
        System.out.println("\nJabatan anda adalah Pegawai");
        gajiPokok=4000000;
        namaJabatan = "Pegawai";
        namaJabatan = namaJabatan.toUpperCase();
        System.out.println("\nGaji pokok anda :" +gajiPokok);
        break;
        case 4 :
        System.out.println("\nJabatan anda adalah Magang");
        gajiPokok=1000000;
        namaJabatan = "Magang";
        namaJabatan = namaJabatan.toUpperCase();
        System.out.println("\nGaji Pokok anda :" +gajiPokok);
        break;
        case 5 :
        System.out.println("\nJabatan anda adalah Security"); 
        gajiPokok=2900000;
        namaJabatan = "Security";
        namaJabatan = namaJabatan.toUpperCase();
        System.out.println("\nGaji pokok anda :" +gajiPokok);
        break;
        default :
        System.out.println("\nNama Jabatan tidak ditemukan!!");
        }
    }


    @Override
    public void JumlahHariMasuk(){
    if(jabatan>=1 && jabatan <=6){
    System.out.print("\nJumlah hari masuk Kantor (1 bulan = 30 hari kantor) = ");
    hariMasuk = gaji.nextInt();
    if(hariMasuk >= 1 && hariMasuk <= 30){
        hariLibur = 30-hariMasuk;
        }
        else{
        System.out.println("\nInputkan jumlah hadir yang benar!!");
        }
    }
    else{
        System.out.println("                                    ");
    }
    }


    @Override
    public void Potongan(){
     potongan = hariMasuk*80000;
     System.out.println("\nPotongan Gaji karena absen anda  : " +potongan);   
    }

    @Override
    public void TotalGaji(){
    gajitotal = gajiPokok - potongan;
    System.out.println("\ngaji Total Anda Adalah : " +gajiPokok +" - " +potongan +" = " +gajitotal);
    }

    @Override
    public void save() throws SQLException 
    {
        try 
        {
            System.out.println("Masukkan data karyawan");
            NoPegawai();
            NamaPegawai();
            Jabatan();
            GajiPokok();
            JumlahHariMasuk();
            Potongan();
            TotalGaji();
    
            String sql = "INSERT INTO karyawan (nama, nomor, jabatan, jmlHadir, totalGaji) VALUES ('"+nama+"','"+jabatan+"','"+totalGaji+"')";
            conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data!!");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            //System.out.println("Error masukkan nomor jabatan dengan benar");
            System.err.println("Error masukkan nomor jabatan dengan benar");
        }

        catch (IndexOutOfBoundsException e)
        {
            //System.out.println("Masukkan rentang jumlah hadir 1-30");
            System.err.println("Masukkan rentang jumlah hadir 1-30");
        }

        catch (InputMismatchException e)
        {
            System.err.println("Input pilihan dengan angka saja");
        }
        
    }

    @Override
    public void delete() throws SQLException
    {
        view();
        try
        {
            System.out.println("Hapus data karyawan");
            System.out.print("Masukkan nomor pegawai yang akan dihapus : ");
            String noPegawai = input.nextLine();

            String sql = "DELETE FROM karyawan WHERE nomor = "+ noPegawai;

            conn = DriverManager.getConnection(link,"root","");
	        Statement statement = conn.createStatement();

            if(statement.executeUpdate(sql) > 0)
            {
	            System.out.println("Berhasil menghapus data pegawai (Nomor "+noPegawai+")");
	        }
        }

        catch(SQLException e)
        {
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        catch(Exception e)
        {
            System.out.println("masukan data yang benar");
        }
    }

    @Override
    public void update() throws SQLException
    {
        view();
        try
        {
            System.out.print("Masukkan nomor pegawai hendak diubah: ");
            String noPegawai = input.nextLine();

            String sql = "SELECT * FROM karyawan WHERE nomor = " +noPegawai;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next())
            {
                System.out.print("Nama baru ["+result.getString("nama")+"]\t: ");
                String namaPegawai = input.nextLine();

                sql = "UPDATE karyawan SET nama='"+namaPegawai+"' WHERE nomor='"+noPegawai+"'";

                if(statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui data pegawai (Nomor "+noPegawai+")");
                }
            }
            statement.close();
        }

            catch (SQLException e) 
            {
                System.err.println("Terjadi kesalahan dalam mengedit data");
                System.err.println(e.getMessage());
            }
        
    }
        
    

    @Override
    public void search() throws SQLException
    {
        System.out.print("Masukkan Nama Pegawai yang ingin dilihat : ");    
		String keyword = input.nextLine();
		
		String sql = "SELECT * FROM karyawan WHERE nama LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 

        while (result.next())
        {
            System.out.print("\nNama pegawai\t\t: ");
            System.out.print(result.getString("namapegawai"));
            System.out.print("\nNo pegawai\t\t: ");
            System.out.print(result.getString("nopegawai"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("jabatan"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.print(result.getInt("totalgaji"));
            System.out.print("\nJumlah hari masuk\t\t: ");
            System.out.println(result.getInt("jumlahharimasuk"));
            System.out.println("\nGaji Pokok\t\t:");
            System.out.println(result.getInt("gajipokok"));
        }
        
    }

    public void DisplayInfo(){
        System.out.println("                                           ");
        System.out.println("------------------------------------------------------");
        System.out.println("\nMENAMPILKAN DATA KARYAWAN");
        System.out.println("Tanggal \t: " + tgl.format(datenow));
        System.out.println("Waktu \t\t: " + time.format(datenow));
        System.out.println("\n------------------------------------------------------");
        System.out.println("Nomor Karyawan = " +nomorPegawai);
        System.out.println("Nama Karyawan = " +namaPegawai);
        System.out.println("Jabatan karyawan = " +namaJabatan);
        System.out.println("Gaji pokok = " +gajiPokok);
        System.out.println("Jumlah hari masuk kantor = " +hariMasuk);
        System.out.println("potongan gaji sesuai absen = " +potongan);
        System.out.println("gaji Total Anda Adalah = " +gajiPokok +" - " +potongan +" = " +gajitotal);
        System.out.println("\nSilahkan Ambil Gaji anda di administrasi");
        ;System.out.println("------------------------------------------------------");


    }
    

}
