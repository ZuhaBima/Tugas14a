import java.text.SimpleDateFormat;
import java.util.Date;

public class Program {
    public static void main(String[] args) throws Exception {
       try {
        String salamSapa = "Selamat Datang, Selamat menggunakan aplikasi";
        String sapa = salamSapa.replace("Selamat Datang", "Silahkan gunakan aplikasi ini dengan benar!"); 

        System.out.println(sapa.toLowerCase());

        Date datenow = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("E, dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss zzz");
        TerimaGaji karyawan =new TerimaGaji(0 , null, null, 0, null);
        System.out.println("Input Data Pekerja!");
        System.out.println("Tanggal \t: " + tgl.format(datenow));
        System.out.println("Waktu \t\t: " + time.format(datenow));
        karyawan.NoPegawai();
        karyawan.NamaPegawai();
        karyawan.Jabatan();
        karyawan.GajiPokok();
        karyawan.JumlahHariMasuk();
        karyawan.Potongan();
        karyawan.TotalGaji();
        karyawan.DisplayInfo();
       } catch (Exception nullpException) {
        System.err.println("\nMasukan Inputan dengan benar!");
        System.err.println("Program tidak dapat dilanjutkan!");
       }
       finally{
        System.out.println("\nProgram Selesai");
       }
        
    }
}
