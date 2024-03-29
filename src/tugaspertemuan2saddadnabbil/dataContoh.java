/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugaspertemuan2saddadnabbil;

import java.util.Scanner;

/**
 *
 * @author saddadnabbil
 */
public class dataContoh {
        public static void main (String [] args) {
            Scanner input = new Scanner (System.in);

                String nama, nim, grade;
                Double uts, uas, rata;

                System.out.println("Data: ");
                System.out.print("NIM: ");
                nim = input.next();
                System.out.print("Nama: ");
                nama = input.next();
                System.out.print("Nilai UTS: ");
                uts = input.nextDouble();
                System.out.print("Nilai UAS: ");
                uas = input.nextDouble();

                rata=(uts+uas)/2;
                
                if (rata<50)
                    grade="E";
                else if (rata < 60)
                    grade="D";
                else if (rata < 70)
                    grade="c";
                else if (rata < 80)
                    grade="B";
                else grade="A";
                
                System.out.println("NIM\tNama\tUTS\tUAS\tN.Rata\tGrade");
                System.out.println(nim + "\t" + nama + "\t" + uts + "\t" + uas + "\t" + rata + "\t" + grade);
                System.out.println ("");
                System.out.println ("");
                
        }
}
