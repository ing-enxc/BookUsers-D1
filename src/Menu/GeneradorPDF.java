package Menu;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class GeneradorPDF {

    public static void exportarFichaUsuario(Usuario u, Component parent) {
        if (u == null) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Selecciona un usuario de la lista para generar su ficha.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar ficha PDF");
        fileChooser.setSelectedFile(new File("Ficha_" + u.getUsername() + ".pdf"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf"));

        if (fileChooser.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivoDestino = fileChooser.getSelectedFile();
        if (!archivoDestino.getName().toLowerCase().endsWith(".pdf")) {
            archivoDestino = new File(archivoDestino.getAbsolutePath() + ".pdf");
        }

        Document documento = new Document(PageSize.A4, 45, 45, 40, 40);

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(archivoDestino));
            documento.open();

            // 1. Título superior
            Font fuenteTitulo = new Font(Font.HELVETICA, 18, Font.BOLD, new Color(30, 58, 138));
            Paragraph titulo = new Paragraph("FICHA DE REGISTRO DE USUARIO", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(15);
            documento.add(titulo);

            // 2. Foto del usuario de primera (centrada)
            String rutaFoto = u.getRutafoto();
            if (rutaFoto != null && !rutaFoto.trim().isEmpty() && new File(rutaFoto).exists()) {
                Image foto = Image.getInstance(rutaFoto);
                foto.scaleToFit(130, 130);
                foto.setAlignment(Element.ALIGN_CENTER);
                foto.setSpacingAfter(18);
                documento.add(foto);
            } else {
                Font fuenteAviso = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY);
                Paragraph sinFoto = new Paragraph("[Sin fotografía disponible]", fuenteAviso);
                sinFoto.setAlignment(Element.ALIGN_CENTER);
                sinFoto.setSpacingAfter(18);
                documento.add(sinFoto);
            }

            // 3. Tabla de ficha (2 columnas: Campo | Valor)
            PdfPTable tablaFicha = new PdfPTable(2);
            tablaFicha.setWidthPercentage(85);
            tablaFicha.setWidths(new float[]{3.2f, 5.8f});

            Font fuenteCampo = new Font(Font.HELVETICA, 10, Font.BOLD, new Color(30, 58, 138));
            Font fuenteValor = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);

            agregarFila(tablaFicha, "ID de Usuario:", u.getId(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Nombre de Usuario:", u.getUsername(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Tipo de Documento:", u.getTipodid(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Número de Documento:", u.getNdid(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Primer Nombre:", u.getPnombre(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Segundo Nombre:", u.getSnombre(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Primer Apellido:", u.getPapellido(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Segundo Apellido:", u.getSapellido(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Teléfono:", u.getTelefono(), fuenteCampo, fuenteValor);
            agregarFila(tablaFicha, "Correo Electrónico:", u.getCorreo(), fuenteCampo, fuenteValor);

            documento.add(tablaFicha);
            documento.close();

            JOptionPane.showMessageDialog(
                    parent,
                    "Ficha PDF generada correctamente en:\n" + archivoDestino.getAbsolutePath()
            );

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivoDestino);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Error al generar la ficha PDF: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void agregarFila(PdfPTable tabla, String etiqueta, String valor, Font fCampo, Font fValor) {
        PdfPCell celdaEtiqueta = new PdfPCell(new Phrase(etiqueta, fCampo));
        celdaEtiqueta.setBackgroundColor(new Color(241, 245, 249));
        celdaEtiqueta.setPadding(8);
        celdaEtiqueta.setBorderColor(new Color(203, 213, 225));

        PdfPCell celdaValor = new PdfPCell(new Phrase((valor != null && !valor.isEmpty()) ? valor : "-", fValor));
        celdaValor.setPadding(8);
        celdaValor.setBorderColor(new Color(203, 213, 225));

        tabla.addCell(celdaEtiqueta);
        tabla.addCell(celdaValor);
    }
}