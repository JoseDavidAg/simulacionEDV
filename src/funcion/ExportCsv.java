/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author ambro
 */
public class ExportCsv {
    public ExportCsv(JTable table) throws IOException{
    JFileChooser newFile = new JFileChooser();
    newFile.setDialogTitle("Guardar como");
    int userSelection=newFile.showSaveDialog(null);
    
    if(userSelection==JFileChooser.APPROVE_OPTION){
        File file = newFile.getSelectedFile();
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file+".csv"))){
            TableModel model =table.getModel();
            
            for(int i=0; i<model.getColumnCount();i++){
                bw.write(model.getColumnName(i)+(i<model.getColumnCount()-1?",":""));
            }
            
            bw.newLine();
            
            for(int i=0;i<model.getRowCount();i++){
                for(int j=0; j<model.getColumnCount();j++){
                    bw.write(model.getValueAt(i,j)+(j<model.getColumnCount()-1?",":""));
                }
                bw.newLine();
            }
            
            JOptionPane.showMessageDialog(null, "Archivo CSV guardado con éxito");
            
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: "+ e.getMessage());
        }
    }
    }
    
}
