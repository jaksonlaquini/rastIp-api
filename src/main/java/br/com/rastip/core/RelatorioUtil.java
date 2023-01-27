/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rastip.core;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;

/**
 *
 * @author Diogo
 */
public class RelatorioUtil {

    public static byte[] gerarRelatorio(JasperReport relatorio, List<?> rs, Map parametros) throws JRException, SQLException {
        //gerando o jasper design
        /*JasperDesign desenho = layout;

        //compila o relatório
        JasperReport relatorio = JasperCompileManager.compileReport(desenho);*/
        
        JRBeanCollectionDataSource jrCDS = new JRBeanCollectionDataSource(rs);

        //executa o relatório
        JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrCDS);

        //retorna o resultado        
        return JasperExportManager.exportReportToPdf(impressao);
    }
    
    public static JasperDesign getPedidoJasperDesfign(){
        JasperDesign jasperDesign = new JasperDesign();
        
        
        
        return jasperDesign;
    }

    public static JasperDesign getJasperDesign(String titulo, List<JRDesignField> campos, List<JRDesignStaticText> colunas,
            List<JRDesignTextField> valores) throws JRException {

        // -------------------   PÁGINA  -------------------  
        JasperDesign jasperDesign = new JasperDesign();

        jasperDesign.setName("RelatorioDinamico");

        jasperDesign.setPageWidth(595);

        jasperDesign.setPageHeight(842);

        jasperDesign.setColumnCount(1);

        jasperDesign.setColumnWidth(555);

        jasperDesign.setColumnSpacing(0);

        jasperDesign.setLeftMargin(20);

        jasperDesign.setRightMargin(20);

        jasperDesign.setTopMargin(20);

        jasperDesign.setBottomMargin(20);

        jasperDesign.setOrientation(OrientationEnum.PORTRAIT);

        // -------------------   FIELDS  -------------------  
        for (JRDesignField field : campos) {
            jasperDesign.addField(field);
        }

        // -------------------   TITLE  -------------------  
        JRDesignBand band = new JRDesignBand();

        band.setHeight(150);

//        JRDesignImage imagem = new JRDesignImage(jasperDesign);
//        JRDesignExpression expression = new JRDesignExpression();
//        String url = RelatorioUtil.class.getClassLoader().getResource("logo_new.png").toString();
//        expression.setText("\"" + url + "\"");
//        imagem.setExpression(expression);
//        imagem.setScaleImage(ScaleImageEnum.RETAIN_SHAPE);
//        imagem.setX(236);
//        imagem.setY(10);
//        imagem.setWidth(83);
//        imagem.setHeight(29);
//        band.addElement(imagem);

        JRDesignStaticText staticText = new JRDesignStaticText();

        staticText.setX(0);

        staticText.setY(10);

        staticText.setWidth(555);

        staticText.setHeight(32);

        staticText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);

        staticText.setFontName("Tahoma");

        staticText.setPdfFontName("Helvetica-Bold");

        staticText.setFontSize(24f);

        staticText.setBold(true);

        staticText.setText("OFFICECEL");
        
        band.addElement(staticText);
        
        staticText = new JRDesignStaticText();

        staticText.setX(0);

        staticText.setY(96);

        staticText.setWidth(555);

        staticText.setHeight(32);

        staticText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);

        staticText.setFontName("Tahoma");

        staticText.setPdfFontName("Helvetica-Bold");

        staticText.setFontSize(24f);

        staticText.setBold(true);

        staticText.setText(titulo);

        staticText.setStretchType(StretchTypeEnum.RELATIVE_TO_BAND_HEIGHT);

        staticText.getLineBox().getPen().setLineColor(Color.BLACK);
        staticText.getLineBox().getPen().setLineWidth(0.5f);

        band.addElement(staticText);

        jasperDesign.setTitle(band);

        // -------------------   PAGE HEADER  -------------------  
        band = new JRDesignBand();

        band.setHeight(15);

        jasperDesign.setPageHeader(band);

        // -------------------   COLUMN HEADER  -------------------  
        band = new JRDesignBand();

        band.setHeight(20);

        jasperDesign.setColumnHeader(band);

        for (JRDesignStaticText stext : colunas) {
            band.addElement(stext);
        }

        // -------------------   DETAIL  -------------------  
        band = new JRDesignBand();

        band.setHeight(20);

        for (JRDesignTextField textField : valores) {
            band.addElement(textField);
        }

        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);

        // -------------------   COLUMN FOOTER  -------------------  
        band = new JRDesignBand();

        band.setHeight(5);

        jasperDesign.setColumnFooter(band);

        // -------------------   PAGE FOOTER  -------------------  
        band = new JRDesignBand();

        band.setHeight(5);

        jasperDesign.setPageFooter(band);

        // -------------------   SUMARY  -------------------  
        band = new JRDesignBand();

        band.setHeight(5);

        jasperDesign.setSummary(band);

        return jasperDesign;
    }

    public static JRDesignField gerarField(Class<?> o, String campo) {
        JRDesignField field = new JRDesignField();

        field.setName(campo);

        field.setValueClass(o);
        return field;
    }

}
