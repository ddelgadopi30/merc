/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import java.util.HashMap;
import java.util.Map;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.geom.PageSize;
import static com.itextpdf.kernel.pdf.PdfName.Border;
import static com.itextpdf.kernel.pdf.PdfName.r;
import com.itextpdf.layout.border.Border;
import static com.itextpdf.layout.border.Border.NO_BORDER;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import static java.lang.Integer.parseInt;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.FirebaseConnection;
import static model.FirebaseConnection.firebaseDatabase;
import model.ReadData;
import static viewModel.ProductoDAO.listp;

public class FacturaDAO {

    public static int h = 1;
    public static int j = 0;
    public static DefaultTableModel modelo = new DefaultTableModel(new String[]{"Código", "Nombre", "Cantidad", "Precio de proveedor", "Precio de venta"}, 30);
    public static ArrayList<Object> listaf = new ArrayList<Object>();
    public static HashMap<Integer, String> listaproductosf = new HashMap<Integer, String>();
    public static ArrayList<String> listafacturapre = new ArrayList<String>();
    public static int orden = -1;
    public static void crear() throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String path = "C:\\Users\\edson\\Desktop\\factura.pdf";
        PdfWriter pdf = new PdfWriter(path);
        PdfDocument pdfd = new PdfDocument(pdf);
        Document d = new Document(pdfd);
        pdfd.setDefaultPageSize(PageSize.A4);
        float col = 280f;
        float columnWidth[] = {col, col};
        LocalDateTime hora = LocalDateTime.now();
        Table table = new Table(columnWidth);
        table.setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE);
        table.addCell(new Cell().add("INVOICE")
                .setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(NO_BORDER));
        table.addCell(new Cell().add("Merc\n#0001\n" + hora)
                .setBorder(NO_BORDER));
        float colWidth[] = {80, 300, 100, 80};
        Table customerI = new Table(colWidth);
        customerI.addCell(new Cell(0, 4).add("Customer Information").setBold().setBorder(NO_BORDER));
        customerI.addCell(new Cell().add("Nombre").setBold().setBorder(NO_BORDER));
        customerI.addCell(new Cell().add(Cliente.clientes.get(0)).setBorder(NO_BORDER));
        customerI.addCell(new Cell().add("Documento de identidad").setBold().setBorder(NO_BORDER));
        customerI.addCell(new Cell().add(Cliente.clientes.get(1)).setBorder(NO_BORDER));
        customerI.addCell(new Cell().add("Teléfono").setBold().setBorder(NO_BORDER));
        customerI.addCell(new Cell().add(Cliente.clientes.get(2)).setBorder(NO_BORDER));
        customerI.addCell(new Cell().add("Dirección").setBold().setBorder(NO_BORDER));
        customerI.addCell(new Cell().add(Cliente.clientes.get(3)).setBorder(NO_BORDER));

        float Iteminfo[] = {140, 140, 140, 140};
        Table item = new Table(Iteminfo);
        item.addCell(new Cell().add("Código").setBold());
        item.addCell(new Cell().add("Nombre").setBold());
        item.addCell(new Cell().add("Cantidad").setBold());
        item.addCell(new Cell().add("Precio").setBold());

        for (int i = 0; i < listaf.size(); i++) {
            ProductoDTO producto = (ProductoDTO) listaf.get(i);

            item.addCell(new Cell().add(producto.getCode()).setBold().setBorder(NO_BORDER));
            item.addCell(new Cell().add(producto.getNombre()).setBold().setBorder(NO_BORDER));
            item.addCell(new Cell().add(String.valueOf(producto.cantidad)).setBold().setBorder(NO_BORDER));
            item.addCell(new Cell().add(String.valueOf(producto.getPrecio())).setBold().setBorder(NO_BORDER));
            j = j + producto.getPrecio();
        }
        float Itemtotal[] = {280,280};
        Table total = new Table(Itemtotal);
        item.addCell(new Cell().add("Total"));
        ;
        item.addCell(new Cell().add(String.valueOf(j)).setBold());
        d.add(table);
        d.add(new Paragraph("\n"));
        d.add(customerI);
        d.add(item);
        d.close();
        javax.swing.JOptionPane.showMessageDialog(null, "PDF Created");
    }

    public static void buscar(String dato, JTable tabla, int cantidad) {
        tabla.setModel(modelo);

        System.out.println(ProductoDAO.listp.toString());
        for (int i = 0; i < ProductoDAO.listp.size(); i++) {
            ProductoDTO p = (ProductoDTO) ProductoDAO.listp.get(i);

            ProductoDTO p2 = new ProductoDTO();
            p2.nombre = dato;
            if (cantidad < p.cantidad) {
                System.out.println(p.nombre + " " + p.code);
                if (p.nombre.contains(dato)) {
                    TableModel modelodatos = tabla.getModel();
                    p.setCantidad(cantidad);
                    modelodatos.setValueAt(p.getCode(), h - 1, 0);
                    modelodatos.setValueAt(p.getNombre(), h - 1, 1);
                    modelodatos.setValueAt(p.getCantidad(), h - 1, 2);
                    modelodatos.setValueAt(p.getPreciop(), h - 1, 3);
                    modelodatos.setValueAt(p.getPrecio(), h - 1, 4);
                    listaf.add(p);
                    h = h + 1;
                    javax.swing.JOptionPane.showMessageDialog(null, "Producto añadido" + h);
                }
            }

        }
    }


    
    public static void facmobil1() {
        String total = null;
        String direccion = null;
        String cedula = null;
        String telefono = null;
        String nombrec = null;
        String nombrepro = null;
        int cantidad =0;
        int lastIdx = ReadData.listfac.size() - 1;
        System.out.println(ReadData.listfac.toString());
                String x = ReadData.listfac.get(lastIdx);
                String lista[] = x.split(", ");
                lista[6] = lista[6].replace("\"", "");
                if(lista[0].contains("total")){
                    total = lista[0].replace("{","").replace("total=", "");
                }else if(lista[0].contains("cedula")){
                    cedula = lista[0].replace("{","").replace("cedula=", "");
                }else if(lista[0].contains("direccion")){
                    direccion = lista[0].replace("{","").replace("direccion=", "");
                }else if(lista[0].contains("telefono")){
                    telefono = lista[0].replace("{","").replace("telefono=", "");
                }else if(lista[0].contains("nombre")){
                    nombrec = lista[0].replace("{","").replace("nombre=", "");
                }else{
                    lista[0] = lista[0].replace("productosVendidos{", "");
                    lista[0] = lista[0].replace("}", "");
                    String nomcan[] = lista[0].split("=");
                    nombrepro = nomcan[0];
                    cantidad = parseInt(nomcan[1].replace("\"", ""));
                }
                
                if(lista[1].contains("total")){
                    total = lista[1].replace("{","").replace("total=", "");
                }else if(lista[1].contains("cedula")){
                    cedula = lista[1].replace("{","").replace("cedula=", "");
                }else if(lista[1].contains("direccion")){
                    direccion = lista[1].replace("{","").replace("direccion=", "");
                }else if(lista[1].contains("telefono")){
                    telefono = lista[1].replace("{","").replace("telefono=", "");
                }else if(lista[1].contains("nombre")){
                    nombrec = lista[1].replace("{","").replace("nombre=", "");
                }else{
                    lista[1] = lista[1].replace("productosVendidos{", "");
                    lista[1] = lista[1].replace("}", "");
                    String nomcan[] = lista[1].split("=");
                    nombrepro = nomcan[0];
                    cantidad = parseInt(nomcan[1].replace("\"", ""));
                }
                
                if(lista[2].contains("total")){
                    total = lista[2].replace("{","").replace("total=", "");
                }else if(lista[2].contains("cedula")){
                    cedula = lista[2].replace("{","").replace("cedula=", "");
                }else if(lista[2].contains("direccion")){
                    direccion = lista[2].replace("{","").replace("direccion=", "");
                }else if(lista[2].contains("telefono")){
                    telefono = lista[2].replace("{","").replace("telefono=", "");
                }else if(lista[2].contains("nombre")){
                    nombrec = lista[2].replace("{","").replace("nombre=", "");
                }else{
                    lista[2] = lista[2].replace("productosVendidos={", "");
                    lista[2] = lista[2].replace("}", "");
                    String nomcan[] = lista[2].split("=");
                    nombrepro = nomcan[0];
                    cantidad = parseInt(nomcan[1].replace("\"", ""));
                }
                if(lista[3].contains("total")){
                    total = lista[3].replace("{","").replace("total=", "");
                }else if(lista[3].contains("cedula")){
                    cedula = lista[3].replace("{","").replace("cedula=", "");
                }else if(lista[3].contains("direccion")){
                    direccion = lista[3].replace("{","").replace("direccion=", "");
                }else if(lista[3].contains("telefono")){
                    telefono = lista[3].replace("{","").replace("telefono=", "");
                }else if(lista[3].contains("nombre")){
                    nombrec = lista[3].replace("{","").replace("nombre=", "");
                }else{
                    lista[3] = lista[3].replace("productosVendidos={", "");
                    
                    lista[3] = lista[3].replace("}", "");
                    String nomcan[] = lista[3].split("=");
                    nombrepro = nomcan[0];
                    cantidad = parseInt(nomcan[1].replace("\"", ""));
                }
                if(lista[4].contains("total")){
                    total = lista[4].replace("{","").replace("total=", "");
                }else if(lista[4].contains("cedula")){
                    cedula = lista[4].replace("{","").replace("cedula=", "");
                }else if(lista[4].contains("direccion")){
                    direccion = lista[4].replace("{","").replace("direccion=", "");
                }else if(lista[4].contains("telefono")){
                    telefono = lista[4].replace("{","").replace("telefono=", "");
                }else if(lista[4].contains("nombre")){
                    nombrec = lista[4].replace("{","").replace("nombre=", "");
                }else{
                    lista[4] = lista[4].replace("productosVendidos={", "");
                    lista[4] = lista[4].replace("}", "");
                    String nomcan[] = lista[4].split("=");
                    nombrepro = nomcan[0];
                    cantidad = parseInt(nomcan[1].replace("\"", ""));
                } 
                if(lista[5].contains("total")){
                    total = lista[5].replace("{","").replace("total=", "");
                }else if(lista[5].contains("cedula")){
                    cedula = lista[5].replace("{","").replace("cedula=", "");
                }else if(lista[5].contains("direccion")){
                    direccion = lista[5].replace("{","").replace("direccion=", "");
                }else if(lista[5].contains("telefono")){
                    telefono = lista[5].replace("{","").replace("telefono=", "");
                }else if(lista[5].contains("nombre")){
                    nombrec = lista[5].replace("{","").replace("nombre=", "");
                }else{
                    lista[5] = lista[5].replace("productosVendidos={", "");
                    
                    lista[5] = lista[5].replace("}", "");
                    String nomcan[] = lista[5].split("=");
                    nombrepro = nomcan[0];
                    cantidad = parseInt(nomcan[1].replace("\"", ""));
                } 
                int xd = parseInt(lista[6].replace("\"", ""));
                
                orden = parseInt(lista[6]);
                System.out.println(orden);
                if(!Cliente.clientes.isEmpty() || listafacturapre.isEmpty())
                    Cliente.añadir(nombrec, cedula, telefono, direccion);
                    listafacturapre.add(nombrepro+"== "+cantidad);
            }
    
    

    public static void exportar(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Código", "Nombre", "Cantidad", "Precio de proveedor", "Precio de venta"}, 30);
        tabla.setModel(modelo);
        TableModel modelodatos = tabla.getModel();
        System.out.println(listafacturapre.toString());
        String x = (String) listafacturapre.get(0);
        String m[] = x.split("== ");
         for(int j = 0; j < listp.size();j++){
            ProductoDTO p = (ProductoDTO) listp.get(j);
            if (p.nombre.equals(m[0])) {
                tabla.setModel(modelo);
                modelodatos.setValueAt(p.getCode(), 0, 0);
                modelodatos.setValueAt(p.getNombre(), 0, 1);
                modelodatos.setValueAt(m[1], 0, 2);
                modelodatos.setValueAt(p.getPreciop(), 0, 3);
                modelodatos.setValueAt(p.getPrecio(), 0, 4);
                listaf.add(p);
            }else{
                
            }
        }
    }
}

