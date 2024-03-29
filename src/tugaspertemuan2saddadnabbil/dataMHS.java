/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugaspertemuan2saddadnabbil;

import java.util.Scanner;
import MethodClass.MHS;

/**
 *
 * @author saddadnabbil
 */
public class dataMHS {
    private static double nilakhir;
    private static String grade;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MHS[] mhs = null; // Initialize mhs to null
        int jml, x, pil;

        do {
            System.out.println("PILIH MENU");
            System.out.println("================================");
            System.out.println("1. Input Data");
            System.out.println("2. Tampil Data");
            System.out.println("3. Keluar Program");
            System.out.println("================================");
            System.out.print("Pilih Menu ?");
            pil = input.nextInt();
            switch (pil) {
                case 1:
                    System.out.print("Jumlah Data: ");
                    jml = input.nextInt();
                    mhs = new MHS[jml];
                    for (x = 0; x < jml; x++) {
                        mhs[x] = new MHS();
                        System.out.println("Data : " + (x + 1));
                        System.out.print("NIM: ");
                        mhs[x].setNim(input.next());
                        System.out.print("Nama :");
                        mhs[x].setNama(input.next());
                        System.out.print("Nilai UTS : ");
                        mhs[x].setUts(input.nextFloat());
                        System.out.print("Nilai UAS : ");
                        mhs[x].setUas(input.nextFloat());
                    }
                    break;
                case 2:
                    if (mhs != null) { // Check if mhs is initialized
                        System.out.println("Tampil Data ");
                        x = 0;
                        while (x < mhs.length) {
                            System.out.println("Data : " + (x + 1));
                            System.out.println("NIM: " + mhs[x].getNim());
                            System.out.println("Nama: " + mhs[x].getNama());
                            System.out.println("Nilai UTS: " + mhs[x].getUts());
                            System.out.println("Nilai UAS: " + mhs[x].getUas());
                            System.out.println("Nilai Nil Akhir: " + mhs[x].getNilAkhir());
                            System.out.println("Nilai Grade: " + mhs[x].getGrade());
                            x++;
                        }
                    } else {
                        System.out.println("Belum ada data yang dimasukkan.");
                    }
                    break;
                case 3:
                    System.out.println("Selesai");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        } while (pil != 3);
    }

}
