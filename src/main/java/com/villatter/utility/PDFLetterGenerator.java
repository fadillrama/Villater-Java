package com.villatter.utility;

import com.lowagie.text.DocumentException;
import com.villatter.dto.pdf.HardPdfDTO;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PDFLetterGenerator {

    public static byte[] generatorLetterPdf(HardPdfDTO dto) throws Exception {
        var htmlContent = generateHTML(dto);
        var renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);

        var byteArrayOutputStream = new ByteArrayOutputStream();
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream);

        renderer.finishPDF();
        return byteArrayOutputStream.toByteArray();
    }

    public static String generateHTML(HardPdfDTO dto){
        var penduduk = dto.getPenduduk();
        var riwayat = dto.getRiwayat();
        var surat = dto.getRiwayat().getSurat();

        var stringBuilder = new StringBuilder();
        var header = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <title>Document</title>
                    <style>
                        .date{
                            display: flex;
                            flex-direction: row-reverse;
                        }
                        .header, .date{
                            font-size: 12px;
                        }
                        .header{
                            border: solid;
                            margin: 20px;  
                            width: 200px;                   
                        }
                        .header td:first-child{
                            font-weight: bold;
                        }
                        .body{
                            border: solid;
                            margin: 20px;
                        }
                        .body td:first-child{
                            font-weight: bold;
                        }
                        .kop{
                            border-bottom: solid;
                            width: 100%;
                            margin: 20px;
                            padding: 20px;
                        }
                    </style>
                </head>
                <body>""";
        stringBuilder.append(header);

        var kop = """
                <div class="kop">
                    <div>KECAMATAN PANJALU</div>
                    <div>KANTOR URUSAN AGAMA</div>
                    <div>Jl.garahang</div>
                </div>
                """;
        stringBuilder.append(kop);

        var formater = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        var current = formater.format(LocalDate.now());
        var date = String.format("""
                    <div class="date">
                        <table>
                            <tbody>
                                <tr>Ciamis, %s</tr>
                            </tbody>
                        </table>
                    </div>
                """, current);
        stringBuilder.append(date);

        var letterInformation = String.format("""
                                <div class="header">
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>No Surat</td>
                                                <td>:</td>
                                                <td>%s</td>
                                            </tr>
                                            <tr>
                                                <td>Jenis Surat</td>
                                                <td>:</td>
                                                <td>%s</td>
                                            </tr>
                                            <tr>
                                                <td>Keperluan</td>
                                                <td>:</td>
                                                <td>%s</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                        """, riwayat.getNoSurat(),
                surat.getJenisSurat(),
                riwayat.getKeperluan());
        stringBuilder.append(letterInformation);

        var fullName = String.format("%s %s %s", penduduk.getNamaDepan(), penduduk.getNamaTengah(), penduduk.getNamaBelakang());
        var address = String.format("%s, Dusun %s, RT.%d/RW.%d, Desa %s, Kecamatan %s", penduduk.getJalan(),
                penduduk.getDusun(), penduduk.getRt(), penduduk.getRw(), penduduk.getDesa(), penduduk.getKecamatan());
        var birthDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID")).format(penduduk.getTanggalLahir());
        var personInformation = String.format("""
                <div class="body">
                    <table>
                        <tbody>
                            <tr>
                                <td>NIK</td>
                                <td>:</td>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <td>Nama Lengkap</td>
                                <td>:</td>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <td>Tanggal Lahir</td>
                                <td>:</td>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <td>Alamat Lengkap</td>
                                <td>:</td>
                                <td>%s</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            """, penduduk.getNik(), fullName, birthDate, address);
        stringBuilder.append(personInformation);

        stringBuilder.append("</body> </html>");
        return stringBuilder.toString();
    }
}
